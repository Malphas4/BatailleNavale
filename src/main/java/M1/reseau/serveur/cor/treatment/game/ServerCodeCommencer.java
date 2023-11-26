package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeCommencer extends ServerCOR {

    public ServerCodeCommencer() {

    }

    /**
     * Receive : commencer;[salon id]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        GameService _gameService = _salon.get_gameService();
        try {
            _gameService.get_partie().commencer();
            String _jc = _gameService.get_partie().getJoueurCourant().get_pseudo();
            _salon.get_j1().message("commencer;" + _salon.get_id() + _jc);
            _salon.get_j2().message("commencer;" + _salon.get_id() + _jc);
            _salon.get_chrono().set_time(30);
        } catch (IPartieException | IOException e) {
            System.err.println("ServerCodeCommencer : La partie ne peut pas commencer.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("commencer");
    }
}
