package M1.reseau.modele.monde.grille.fabrique;

import M1.reseau.modele.monde.grille.IGrille;
import M1.reseau.modele.monde.grille.type.Grille32x32;

public class FabriqueGrille32x32 implements IFabriqueGrille {
    @Override
    public IGrille creerGrille() {
        return new Grille32x32();
    }
}
