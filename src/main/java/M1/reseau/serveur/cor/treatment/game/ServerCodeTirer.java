package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.bot.Bot;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorEstTire;
import M1.reseau.model.player.visitor.VisitorTirer;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.state.CaseTouche;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeTirer extends ServerCOR {

    public ServerCodeTirer() {
    }

    /**
     * Receive : tirer;[salon id];[joueur tireur];[joueur victime];[x];[y]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");

        VisitorTirer _tire = new VisitorTirer();
        VisitorEstTire _tireSubit = new VisitorEstTire();

        try {
            JoueurNormal _jc = (JoueurNormal) _gameService.get_partie().getJoueurCourant();
            if (_gameService.get_partie().getJoueurCourant().get_pseudo().equals(_sp[2]))
                throw new IPartieException("ServerCodeTirer : Error player turn.");

            JoueurNormal _ja = (JoueurNormal) _gameService.get_partie().getJoueurAdverse();

            _tire.set_grille(_ja.get_grilleJoueur());
            ICase _case = _ja
                    .get_grilleJoueur()
                    .get_caseParCoord(
                            Integer.parseInt(_sp[4]),
                            Integer.parseInt(_sp[5])

                    );
            _tire.set_case(_case);
            _jc.accepte(_tire);

            _tireSubit.set_grille(_ja.get_grilleJoueur());
            _tireSubit.set_case(_case);
            _ja.accepte(_tireSubit);

            /* Sending update to all player */
            if (!(_ja instanceof Bot)) {
                // toucher;[salon id];[tireur];[victime];[x];[y];[statut case]
                String msg = "toucher;"
                        + _salon.get_nom()
                        + ";" + _jc.get_pseudo()
                        + ";" + _ja.get_pseudo()
                        + ";" + _sp[4]
                        + ";" + _sp[5]
                        + ";" + ((_case instanceof CaseTouche) ? "true": "false");
                _salon.get_j1().message(msg);
                _salon.get_j2().message(msg);
            }

            if (_salon.get_j1().get_pseudo().equals(_jc.get_pseudo())) {
                _salon.get_j1().set_monTour(false);
                _salon.get_j2().set_monTour(true);
                _salon.get_j1().sendFinTour();
                _salon.get_j2().sendDebutTour();
            } else if (_salon.get_j2().get_pseudo().equals(_jc.get_pseudo())) {
                _salon.get_j1().set_monTour(true);
                _salon.get_j2().set_monTour(false);
                _salon.get_j1().sendDebutTour();
                _salon.get_j2().sendFinTour();
            }

            boolean status = _gameService.get_partie().gagnant();
            if (status) {
                _gameService.get_partie().fin();
                String _msg = "gagner;"
                        + _salon.get_id()
                        + ";" + ((_gameService.get_partie().is_aGagner1()) ?
                                                            _gameService.get_partie().get_joueur1().get_pseudo()
                                                            : _gameService.get_partie().get_joueur2().get_pseudo());
                _salon.get_j1().message(_msg);
                _salon.get_j2().message(_msg);
            }
            else _gameService.get_partie().tourSuivant();

        } catch (IPartieException | IGrilleException | IJoueurException | IOException e) {
            System.err.println("ServerCodeTirer : Erreur.");
        }

        /* If player is bot, we play as the bot */
        try {
            if (_gameService.get_partie().getJoueurCourant() instanceof Bot) {
                Bot _bot = (Bot) _gameService.get_partie().getJoueurCourant();
                JoueurNormal _ja = (JoueurNormal) _gameService.get_partie().getJoueurAdverse();

                _tire.set_grille(_ja.get_grilleJoueur());
                _bot.accepte(_tire);

                _tireSubit.set_grille(_ja.get_grilleJoueur());
                _tireSubit.set_case(_tire.get_case());
                _ja.accepte(_tireSubit);

                String msg = "toucher;"
                        + _salon.get_nom()
                        + ";" + _bot.get_pseudo()
                        + ";" + _ja.get_pseudo()
                        + ";" + _tire.get_case().get_x()
                        + ";" + _tire.get_case().get_y()
                        + ";" + ((_tire.get_case() instanceof CaseTouche) ? "true": "false");
                _salon.get_j1().message(msg);

                boolean status = _gameService.get_partie().gagnant();
                if (status) {
                    _gameService.get_partie().fin();
                }
                else _gameService.get_partie().tourSuivant();
                _gameService.get_partie().tourSuivant();

            }
        } catch (IPartieException | IJoueurException e) {
            System.err.println("ServerCodeTirer : Error.");
        } catch (IOException e) {
            System.err.println("ServerCodeTirer : Bot can't send tirer.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equals("tirer");
    }
}
