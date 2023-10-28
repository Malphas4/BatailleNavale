package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;
import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class Bateau2Case extends Bateau {

    public Bateau2Case(int _orientation) {
        super(_orientation);
    }

    @Override
    public int get_maxCase() {
        return 2;
    }

}
