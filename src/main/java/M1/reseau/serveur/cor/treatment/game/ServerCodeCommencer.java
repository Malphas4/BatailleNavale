package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.salon.ISalon;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

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
        } catch (IPartieException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        return _message.contains("commencer");
    }
}
