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
     * Receive : commencer;[salon id];[joueur]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        GameService _gameService = _salon.get_gameService();
        try {
            _gameService.get_partie().commencer();
            _salon.get_j1().message("commencer;" + _salon.get_id());
            _salon.get_j2().message("commencer;" + _salon.get_id());
        } catch (IPartieException | IOException e) {
            System.err.println("ServerCodeCommencer : Error.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[1].equals("commencer");
    }
}
