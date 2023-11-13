package M1.reseau.model.counter.classic;

import M1.reseau.model.counter.Compteur;
import M1.reseau.model.exception.ICompteurException;

public class CompteurMin extends Compteur {

    /***************************************
     * Déclaration des variables
     ***************************************/

    private int _min; /* Minimum du compteur */

    /***************************************
     * Déclaration des constructeurs
     ***************************************/

    /**
     * Constructeur par défaut
     * Minimum : 0
     */
    public CompteurMin() {
        super();
        set_min(0);
    }

    /**
     * Constructeur personnalisé
     * @param _compteur Départ du compteur
     * @param _min Minimum du compteur
     */
    public CompteurMin(int _compteur, int _min) {
        super(_compteur);
        set_min(_min);
    }

    /***************************************
     * Override des méthodes de base
     ***************************************/

    /**
     * Getter du minimum
     * @return Minimum du compteur
     */
    public int get_min() {
        return _min;
    }

    /**
     * Setter du minimum
     * @param _min Minimum du compteur
     */
    public void set_min(int _min) {
        this._min = _min;
    }

    /***************************************
     * Implémentation des méthodes de ICompteur
     ***************************************/

    /**
     * Méthode de décramentation du compteur
     * @param i Paramètre de décrémentation (>0)
     * @throws ICompteurException Le compteur est inférieur au minimum
     */
    @Override
    public void decremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() - i < get_min()) throw new ICompteurException("Compteur : Le compteur doit être supérieur ou égale au minimum défini.");
        set_compteur(get_compteur() - i);
    }

    /***************************************
     * Override des méthodes de base
     ***************************************/

    /**
     * Méthode de transformation en String
     * @return CompteurMin en String
     */
    @Override
    public String toString() {
        return "CompteurMin{" +
                "_min=" + _min +
                ", " + super.toString() +
                "}";
    }
}
