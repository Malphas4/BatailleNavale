package M1.reseau.client2.cor.treatment;

import M1.reseau.client2.GameClient;
import M1.reseau.client2.cor.ClientCOR;
import M1.reseau.model.exception.IPartieException;

public class ClientCodeCommencer extends ClientCOR {
    /**
     * commencer;[salon id];[joueur qui commence]
     * @param _message
     * @param _game
     */
    @Override
    public void execute(String _message, GameClient _game) {
        String[] _sp = _message.split(";");
        String _idSalon = _sp[1];
        String _joueurCommence = _sp[2];

        try {
            if (_game.get_partie().getJoueurCourant().get_pseudo().equalsIgnoreCase(_joueurCommence)) {
                _game.get_partie().set_tourJoueur(true);
                System.out.println("C'est à votre tour " + _joueurCommence + " !");
            }
        } catch (IPartieException e) {
            System.err.println("Le joueur " + _joueurCommence + " commence.");
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("commencer");
    }
}
