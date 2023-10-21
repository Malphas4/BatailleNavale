package M1.reseau.modele.monde.element.visitor.tirer;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    private Grille _grille;

    public VisitorTirerCroix(Grille _grille) {
        set_grille(_grille);
    }

    public Grille get_grille() {
        return _grille;
    }

    public void set_grille(Grille _grille) {
        this._grille = _grille;
    }

    @Override
    public void visite(ICase _case) {
        super.visite(_case);
    }
}
