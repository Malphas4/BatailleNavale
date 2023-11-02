package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;

public class Bateau4Case extends Bateau {

    public Bateau4Case(int _orientation) {
        super(_orientation);
    }

    @Override
    public int get_maxCase() {
        return 4;
    }

}
