package M1.reseau.modele.monde.grille.fabrique;

import M1.reseau.modele.monde.grille.IGrille;
import M1.reseau.modele.monde.grille.type.Grille16x16;

public class FabriqueGrille16x16 implements IFabriqueGrille {
    @Override
    public IGrille creerGrille() {
        return new Grille16x16();
    }
}
