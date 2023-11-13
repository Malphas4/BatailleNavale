package M1.reseau.model.counter.classic;

import M1.reseau.model.counter.Compteur;
import M1.reseau.model.exception.ICompteurException;

public class CompteurMinMax extends Compteur {

    /***************************************
     * Déclaration des variables
     ***************************************/

    private int _max;
    private int _min;

    public CompteurMinMax() {
        super();
        set_min(0);
        set_max(1024);
    }

    public CompteurMinMax(int _compteur, int _min, int _max) {
        super(_compteur);
        set_min(_min);
        set_max(_max);
    }

    public int get_max() {
        return _max;
    }

    public void set_max(int _max) {
        this._max = _max;
    }

    public int get_min() {
        return _min;
    }

    public void set_min(int _min) {
        this._min = _min;
    }

    @Override
    public void incremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() + i > get_max()) throw new ICompteurException("Compteur : Le compteur doit être inférieur ou égale au maximum défini.");
        set_compteur(get_compteur() - i);
    }

    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() - i < get_min()) throw new ICompteurException("Compteur : Le compteur doit être supérieur ou égale au minimum défini.");
        set_compteur(get_compteur() - i);
    }

    @Override
    public String toString() {
        return "CompteurMinMax{" +
                "_max=" + _max +
                ", _min=" + _min +
                ", " + super.toString() +
                "}";
    }
}
