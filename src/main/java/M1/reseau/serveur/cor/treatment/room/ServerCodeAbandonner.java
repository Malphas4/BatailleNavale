package M1.reseau.serveur.cor.treatment.room;

import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeAbandonner extends ServerCOR {
    /**
     * Receive : abandonner;[salon id];[joueur id]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {

        String [] _sp = _message.split(";");

        if (_salon.get_j1().get_pseudo().equals(_sp[2])) {
            try {
                _salon.get_j2().message(_message);
            } catch (IOException e) {
                System.err.println("ServerCodeAbandonner : Error.");
            }
        } else if (_salon.get_j2().get_pseudo().equals(_sp[2])) {
            try {
                _salon.get_j2().message(_message);
            } catch (IOException e) {
                System.err.println("ServerCodeAbandonner : Error.");
            }
        } else System.err.println("ServerCodeAbandonner : Error.");
        _salon.interrupt();
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[1].equals("abandonner");
    }
}
