package M1.reseau.serveur.cor.treatment.chat;

import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.ServeurGlobale;
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
        System.out.println("|\tInput message : Message global");
        ServeurGlobale.sv.sendAll(_message);
        System.out.println("|\tInput message : End");
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        System.out.println("> chat salon : " + _sp[0] + " - " + _sp[0].equalsIgnoreCase("chat"));
        return _sp[0].equalsIgnoreCase("chat");
    }
}
