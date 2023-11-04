package M1.reseau.modele.compteur;

import M1.reseau.modele.exception.ICompteurException;

public class Compteur implements ICompteur {

    private int _compteur;
    private int _max;
    private int _min;

    public Compteur() {
        set_compteur(0);
    }

    public Compteur(int _compteur) {
        set_compteur(_compteur);
    }

    public int get_compteur() {
        return _compteur;
    }

    public void set_compteur(int _compteur) {
        this._compteur = _compteur;
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
        set_compteur(get_compteur() + i);
    }

    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() - i < get_min()) throw new ICompteurException("Compteur : Le compteur doit être supérieur ou égale au minimum défini.");
        set_compteur(get_compteur() - i);
    }
}
