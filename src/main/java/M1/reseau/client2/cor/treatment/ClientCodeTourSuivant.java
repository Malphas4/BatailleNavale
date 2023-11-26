package M1.reseau.client2.cor.treatment;

import M1.reseau.client2.GameClient;
import M1.reseau.client2.cor.ClientCOR;
import M1.reseau.model.exception.IPartieException;

public class ClientCodeTourSuivant extends ClientCOR {
    /**
     * @param _message
     * @param _game
     */
    @Override
    public void execute(String _message, GameClient _game) {
        try {
            _game.get_partie().tourSuivant();
        } catch (IPartieException e) {
            System.err.println("Vous ne pouvez pas passez le tour.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("tour suivant");
    }
}
