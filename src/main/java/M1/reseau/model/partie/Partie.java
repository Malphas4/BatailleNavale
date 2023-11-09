package M1.reseau.model.partie;

public abstract class Partie implements IPartie {

    /***************************************
     * Déclaration des variables
     ***************************************/
    private int _nbTour;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    public Partie() {
        set_nbTour(0);
    }

    public Partie(int _nbjoueur) {
        set_nbTour(0);
    }

    /***************************************
     * Déclaration des méthodes
     ***************************************/
    public int get_nbTour() {
        return _nbTour;
    }

    public void set_nbTour(int _nbTour) {
        if (_nbTour < 0) throw new IllegalArgumentException("Partie : Le nombre de tour doit être supérieur ou égal à 0.");
        this._nbTour = _nbTour;
    }

}
