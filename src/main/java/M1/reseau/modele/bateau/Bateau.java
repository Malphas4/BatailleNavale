package M1.reseau.modele.bateau;

import M1.reseau.modele.monde.grille.Grille;

public abstract class Bateau implements IBateau {

    private int _orientation;

    public Bateau(int _orientation) {
        set_orientation(_orientation);
    }

    public int get_orientation() {
        return _orientation;
    }

    public void set_orientation(int _orientation) {
        if (_orientation != 0 || _orientation != 1) throw new IllegalArgumentException("Bateau : L'orientation du bateau doit être 0 (vertical) ou 1 (horizontal).");
        this._orientation = _orientation;
    }

}
