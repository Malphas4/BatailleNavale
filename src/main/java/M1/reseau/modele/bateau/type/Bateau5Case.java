package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;

public class Bateau5Case extends Bateau {

    public Bateau5Case(int _orientation) {
        super(_orientation);
    }

    @Override
    public int get_maxCase() {
        return 5;
    }

}
