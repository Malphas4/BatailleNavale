package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorEstTire;
import M1.reseau.model.player.visitor.VisitorTirer;
import M1.reseau.model.world.element.ICase;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

public class ServerCodeTirer extends ServerCOR {

    public ServerCodeTirer() {
    }

    /**
     * Receive : tirer;[joueur tireur];[joueur victime];[x];[y]
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
            if (_gameService.get_partie().getJoueurCourant().get_pseudo().equals(_sp[1]))
                throw new IPartieException("ServerCodeTirer : Error player turn.");

            JoueurNormal _ja = (JoueurNormal) _gameService.get_partie().getJoueurAdverse();

            _tire.set_grille(_ja.get_grilleJoueur());
            ICase _case = _ja
                    .get_grilleJoueur()
                    .get_caseParCoord(
                            Integer.parseInt(_sp[3]),
                            Integer.parseInt(_sp[4])

                    );
            _tire.set_case(_case);
            _jc.accepte(_tire);

            _tireSubit.set_grille(_ja.get_grilleJoueur());
            _tireSubit.set_case(_case);
            _ja.accepte(_tireSubit);

            boolean status = _gameService.get_partie().gagnant();
            if (status) {
                _gameService.get_partie().fin();
            }
            else _gameService.get_partie().tourSuivant();
        } catch (IPartieException | IGrilleException | IJoueurException e) {
            System.err.println("ServerCodeTirer : Erreur.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        return _message.contains("tirer");
    }
}
