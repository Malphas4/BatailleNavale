package M1.reseau.client2.cor.treatment;

import M1.reseau.client2.GameClient;
import M1.reseau.client2.cor.ClientCOR;
import M1.reseau.utilities.InformationsUtilisateur;

public class ClientCodeGagner extends ClientCOR {
    /**
     * gagner;[salon id];[joueur gagnant]
     * @param _message
     * @param _game
     */
    @Override
    public void execute(String _message, GameClient _game) {
        String [] _sp = _message.split(";");
        if (_game.get_partie().get_joueur().get_pseudo().equalsIgnoreCase(_sp[2]))
            _game.get_partie().set_aGagner(true);
        _game.get_partie().gagnant();
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("gagner");
    }
}
