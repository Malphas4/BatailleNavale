package M1.reseau.client2.cor.treatment;

import M1.reseau.client2.GameClient;
import M1.reseau.client2.cor.ClientCOR;
import M1.reseau.utilities.InformationsUtilisateur;

public class ClientCodeJoin extends ClientCOR {
    /**
     * @param _message
     * @param _game
     */
    @Override
    public void execute(String _message, GameClient _game) {
        String [] _sp = _message.split(";");
        InformationsUtilisateur.getInstance().set_adversaire(_sp[2]);
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("join");
    }
}
