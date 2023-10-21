package M1.reseau.modele.bateau;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.Case;
import M1.reseau.modele.monde.grille.Grille;

public abstract class Bateau implements IBateau {

    private int _orientation;
    private Case _case;

    public Bateau(int _orientation) {
        set_orientation(_orientation);
    }

    public Bateau(Grille _grille, int _orientation, Case _case) throws IBateauException {
        set_orientation(_orientation);
        set_case(_grille, _case);
    }

    public int get_orientation() {
        return _orientation;
    }

    public void set_orientation(int _orientation) {
        if (_orientation != 0 || _orientation != 1) throw new IllegalArgumentException("Bateau : L'orientation du bateau doit être 0 (vertical) ou 1 (horizontal).");
        this._orientation = _orientation;
    }

    public Case get_case() {
        return _case;
    }

    public void set_case(Grille _grille, Case _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau : La case d'ancrage ne peut pas être vide");
        if (!isSurLaGrille(_grille)) throw new IBateauException("Bateau : La case doit être dans la grille.");
        this._case = _case;
    }
}
