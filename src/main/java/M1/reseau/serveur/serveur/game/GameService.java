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

    private boolean threadStatus; /* Statut des threads */

    public GameService() {
        _partie = new PartieServeur(8, 8);
    }

    public PartieServeur get_partie() {
        return _partie;
    }

    public void set_partie(PartieServeur _partie) {
        this._partie = _partie;
    }

    public boolean isThreadStatus() {
        return threadStatus;
    }

    public void setThreadStatus(boolean threadStatus) {
        this.threadStatus = threadStatus;
    }

}
