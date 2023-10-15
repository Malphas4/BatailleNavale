package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;
import M1.reseau.modele.monde.grille.Grille;

public class Bateau5Case extends Bateau {

    public Bateau5Case(int _orientation) {
        super(_orientation);
    }

    @Override
    public boolean isSurLaGrille(Grille grille) {
        return false;
    }
}
