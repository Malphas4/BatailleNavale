package M1.reseau.modele.joueur.visitor.tirer;

import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    public VisitorTirerCroix(Grille _grille, ICase _case) {
        super(_grille, _case);
    }

    @Override
    public void visite(IJoueur _joueur) {

    }
}
