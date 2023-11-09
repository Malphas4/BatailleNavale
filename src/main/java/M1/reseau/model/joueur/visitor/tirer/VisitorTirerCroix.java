package M1.reseau.model.joueur.visitor.tirer;

import M1.reseau.model.joueur.type.JoueurNormal;
import M1.reseau.model.joueur.visitor.VisitorTirer;
import M1.reseau.model.monde.element.ICase;
import M1.reseau.model.monde.grille.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    public VisitorTirerCroix(Grille _grille, ICase _case) {
        super(_grille, _case);
    }

    @Override
    public void visite(JoueurNormal _joueur) {

    }
}
