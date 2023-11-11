package M1.reseau.model.counter;

import M1.reseau.model.exception.ICompteurException;

public class Compteur implements ICompteur {

    /***************************************
     * Déclaration des variables
     ***************************************/
    private int _compteur;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    /**
     * Constructeur de Compteur par défaut
     */
    public Compteur() {
        set_compteur(0);
    }

    /**
     * Constructeur de Compteur avec compteur non conventionnel
     * @param _compteur
     */
    public Compteur(int _compteur) {
        set_compteur(_compteur);
    }

    /***************************************
     * Déclaration des getters et setters
     ***************************************/
    /**
     * Getter de compteur
     * @return
     */
    public int get_compteur() {
        return _compteur;
    }

    /**
     * Setter du compteur
     * @param _compteur
     */
    public void set_compteur(int _compteur) {
        this._compteur = _compteur;
    }

    /***************************************
     * Implémentation des méthodes de ICompteur
     ***************************************/
    /**
     * Méthode d'incrémentation de Compteur
     * @param i Paramètre de d'incrémentation (>0)
     */
    @Override
    public void incremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        set_compteur(get_compteur() + i);
    }

    /**
     * Méthode de décrémentation de Compteur
     * @param i Paramètre de décrémentation (>0)
     */
    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        set_compteur(get_compteur() - i);
    }
}
