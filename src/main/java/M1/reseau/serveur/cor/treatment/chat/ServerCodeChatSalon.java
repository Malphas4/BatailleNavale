package M1.reseau.serveur.cor.treatment.chat;

import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeChatSalon extends ServerCOR {



    /**
     * Receive : chat salon;[salon id];[joueur];[message]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        String[] _sp = _message.split(";");
        try {
            if (_salon.get_j1().get_pseudo().equals(_sp[2]))
                _salon.get_j2().message(_message);
            else
                _salon.get_j1().message(_message);
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
        String[] _sp = _message.split(";");
        return _sp[1].equals("chat salon");
    }
}
