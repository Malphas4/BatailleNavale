package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeTourSuivant extends ServerCOR {

    public ServerCodeTourSuivant() {

    }

    /**
     * Receive : tour suivant;[salon id];[joueur]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        System.out.println("\t> ServerCodeTourSuivant : Execute");
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");

        try {
            String _jc = _gameService.get_partie().getJoueurCourant().get_pseudo();
            if (_jc.equalsIgnoreCase(_sp[2])) {
                _gameService.get_partie().tourSuivant();
                _jc = _gameService.get_partie().getJoueurCourant().get_pseudo();
                System.out.println("\t\t> ServerCodeTourSuivant - envoyer : ");
                System.out.println("\t\t> " + "tour suivant;" + _salon.get_id() + _jc);
                _salon.get_j1().message("tour suivant;" + _salon.get_id() + _jc);
                _salon.get_j2().message("tour suivant;" + _salon.get_id() + _jc);
                _salon.get_chrono().set_time(30);
            }
        } catch (IPartieException | IOException e) {
            System.err.println("ServerCodeCommencer : La partie ne peut pas commencer.");
        }
        System.out.println("\t> ServerCodeTourSuivant : Fin Execute");
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        System.out.println("> ServerCodeTourSuivant : " + _sp[0] + " - " + _sp[0].equalsIgnoreCase("tour suivant"));
        return _sp[0].equalsIgnoreCase("tour suivant");
    }
}
