package M1.reseau.model.counter.classic;

import M1.reseau.model.counter.Compteur;
import M1.reseau.model.exception.ICompteurException;

public class CompteurMax extends Compteur {

    /***************************************
     * Déclaration des variables
     ***************************************/

    private int _max; /* Maximum du compteur */

    /***************************************
     * Implémentation des constructeurs
     ***************************************/

    /**
     * Constructeur par défaut
     * Maximum mis à 1024
     */
    public CompteurMax() {
        super();
        set_max(1024);
    }

    /**
     * Constructeur personnalisé
     * @param _compteur Départ du compteur
     * @param _max Maximum autorisé
     */
    public CompteurMax(int _compteur, int _max) {
        super(_compteur);
        set_max(_max);
    }

    /***************************************
     * Déclaration des getters et setters
     ***************************************/

    /**
     * Getter du max
     * @return maximum du compteur
     */
    public int get_max() {
        return _max;
    }

    /**
     * Setter du max
     * @param _max Maximum du compteur
     */
    public void set_max(int _max) {
        this._max = _max;
    }

    /***************************************
     * Implémentation des méthodes de ICompteur
     ***************************************/

    /**
     * Méthode d'incrémentation du compteur
     * @param i Paramètre de d'incrémentation (>0)
     * @throws ICompteurException Le compteur est supérieur au maximum
     */
    @Override
    public void incremente(int i) throws ICompteurException {
        if (i <= 0) throw new IllegalArgumentException("Compteur : i doit être supérieur à 0.");
        if (get_compteur() + i > get_max()) throw new ICompteurException("Compteur : Le compteur doit être inférieur ou égale au maximum défini.");
        set_compteur(get_compteur() + i);
    }

    /***************************************
     * Override des méthodes de base
     ***************************************/

    /**
     * Méthode de transformation en String
     * @return CompteurMax en String
     */
    @Override
    public String toString() {
        return "CompteurMax{" +
                "_max=" + _max +
                ", " + super.toString() +
                "}";
    }
}
