package M1.reseau.modele.compteur;

import M1.reseau.modele.exception.ICompteurException;

public class Compteur implements ICompteur {

    private int _compteur;

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

    @Override
    public void incremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        set_compteur(get_compteur() + i);
    }

    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        set_compteur(get_compteur() - i);
    }
}
