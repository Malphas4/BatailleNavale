package M1.reseau.serveur.cor.treatment.room;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

public class ServerCodeJoin extends ServerCOR {

    public ServerCodeJoin() {

    }

    /**
     * Receive : join;[salon id];[joueur id]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");

        try {
            // Define player in salon
            if (_salon.get_j1() == null) _salon.set_j1(_sp[2]);
            else if (_salon.get_j2() == null) _salon.set_j2(_sp[2]);
            else throw new IPartieException("ServerCodeJoin : Error");

            // Add player in the game
            _gameService.get_partie().ajouterJoueur(_sp[2]);

        } catch (IPartieException e) {
            System.err.println("ServerCodeJoin : Error.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[1].equals("join");
    }
}
