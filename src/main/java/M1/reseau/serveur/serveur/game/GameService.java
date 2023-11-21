package M1.reseau.serveur.serveur.game;

import M1.reseau.model.game.local.PartieServeur;

/**
 *
 */
public class GameService {

    /*******************************
     * Variables
     *******************************/
    /* Initialisation de la partie */
    private PartieServeur _partie;

    /* Stockage des sockets des clients */
    private ClientHandler c1 = null;
    private ClientHandler c2 = null;

    public GameService() {
        _partie = new PartieServeur(8, 8);
    }

    public PartieServeur get_partie() {
        return _partie;
    }

    public void set_partie(PartieServeur _partie) {
        if (_partie == null) throw new IllegalArgumentException("GameService : La partie ne peut pas être null.");
        this._partie = _partie;
    }

}
