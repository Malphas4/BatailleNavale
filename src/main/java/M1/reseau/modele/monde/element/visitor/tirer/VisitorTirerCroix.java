package M1.reseau.modele.monde.element.visitor.tirer;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    public VisitorTirerCroix(Grille _grille) {
        super(_grille);
    }

    @Override
    public void visite(ICase _case) {
        super.visite(_case);
    }
}
