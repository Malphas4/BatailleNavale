package M1.reseau.model.player.visitor.tirer;

import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorTirer;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.grid.Grille;

public class VisitorTirerCroix extends VisitorTirer {

    public VisitorTirerCroix(Grille _grille, ICase _case) {
        super(_grille, _case);
    }

    @Override
    public void visite(JoueurNormal _joueur) {

    }
}
