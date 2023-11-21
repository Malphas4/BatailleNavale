package M1.reseau.serveur.cor.treatment.room;

import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.salon.ISalon;

public class ServerCodeSalon extends ServerCOR {

    public ServerCodeSalon() {

    }

    /**
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, ISalon _salon) {

    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        return _message.contains("salon");
    }
}
