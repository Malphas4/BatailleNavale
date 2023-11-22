package M1.reseau.serveur.cor.treatment.chat;

import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeChat extends ServerCOR {



    /**
     * Receive : chat;[salon id];[joueur];[message]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        String[] _sp = _message.split(";");
        try {
            if (_salon.get_j1().get_pseudo().equals(_sp[2]))
                _salon.get_j2().sendMessage(_sp[3]);
            else
                _salon.get_j1().sendMessage(_sp[3]);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        return _message.contains("chat");
    }
}
