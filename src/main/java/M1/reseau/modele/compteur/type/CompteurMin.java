package M1.reseau.modele.compteur.type;

import M1.reseau.modele.compteur.Compteur;
import M1.reseau.modele.exception.ICompteurException;

public class CompteurMin extends Compteur {

    private int _min;

    public CompteurMin() {
        super();
        set_min(0);
    }

    public CompteurMin(int _compteur, int _min) {
        super(_compteur);
        set_min(_min);
    }

    public int get_min() {
        return _min;
    }

    public void set_min(int _min) {
        this._min = _min;
    }

    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() - i < get_min()) throw new ICompteurException("Compteur : Le compteur doit être supérieur ou égale au minimum défini.");
        set_compteur(get_compteur() - i);
    }
}
