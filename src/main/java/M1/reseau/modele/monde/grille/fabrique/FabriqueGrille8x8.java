package M1.reseau.modele.monde.grille.fabrique;

import M1.reseau.modele.monde.grille.IGrille;
import M1.reseau.modele.monde.grille.type.Grille8x8;

public class FabriqueGrille8x8 implements IFabriqueGrille {
    @Override
    public IGrille creerGrille() {
        return new Grille8x8();
    }
}
