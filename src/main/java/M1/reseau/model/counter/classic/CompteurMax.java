package M1.reseau.model.counter.classic;

import M1.reseau.model.counter.Compteur;
import M1.reseau.model.exception.ICompteurException;

public class CompteurMax extends Compteur {

    private int _max;

    public CompteurMax() {
        super();
        set_max(1024);
    }

    public CompteurMax(int _compteur, int _max) {
        super(_compteur);
        set_max(_max);
    }

    public int get_max() {
        return _max;
    }

    public void set_max(int _max) {
        this._max = _max;
    }

    @Override
    public void incremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() + i > get_max()) throw new ICompteurException("Compteur : Le compteur doit être inférieur ou égale au maximum défini.");
        set_compteur(get_compteur() + i);
    }

    @Override
    public String toString() {
        return "CompteurMax{" +
                "_max=" + _max +
                ", " + super.toString() +
                "}";
    }
}
