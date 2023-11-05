package M1.reseau.modele.joueur.visitor.tirer;

import M1.reseau.modele.joueur.type.JoueurNormal;
import M1.reseau.modele.joueur.visitor.VisitorTirer;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    public VisitorTirerCroix(Grille _grille, ICase _case) {
        super(_grille, _case);
    }

    @Override
    public void visite(JoueurNormal _joueur) {

    }
}
