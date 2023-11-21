package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.element.classic.CaseNormal;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

public class ServerCodeInitBateau extends ServerCOR {
    /**
     * Receive : init bateau;[salon id];[joueur];[x];[y]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");

        int x = Integer.parseInt(_sp[3]);
        int y = Integer.parseInt(_sp[4]);

        CaseBateau _ncase = new CaseBateau(
                x,
                y
        );

        if (_gameService.get_partie().get_joueur1().get_pseudo().equals(_sp[2])) {
            try {
                _gameService.get_partie().get_joueur1().get_grilleJoueur().supprimerCase(new CaseNormal(x, y));
                _gameService.get_partie().get_joueur1().get_grilleJoueur().ajouterCase(_ncase);
            } catch (IGrilleException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                _gameService.get_partie().get_joueur2().get_grilleJoueur().supprimerCase(new CaseNormal(x, y));
                _gameService.get_partie().get_joueur2().get_grilleJoueur().ajouterCase(_ncase);
            } catch (IGrilleException e) {
                System.out.println("ServerCodeInitBateau : Error.");
            }
        }


    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        return _message.contains("init bateau");
    }
}
