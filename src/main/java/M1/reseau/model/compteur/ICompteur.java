package M1.reseau.model.compteur;

import M1.reseau.model.exception.ICompteurException;

public interface ICompteur {
    /**
     * Methode pour incrémenter un paramètre par i
     * @param i
     */
    void incremente(int i) throws ICompteurException;

    /**
     * Méthode pour décrémenter un paramètre par i
     * @param i
     */
    void decremente(int i) throws ICompteurException;
}
