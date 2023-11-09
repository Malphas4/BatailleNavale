package M1.reseau.model.partie;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.joueur.IJoueur;

public interface IPartie {
    void commencer();
    void tourSuivant() throws IPartieException;
    IJoueur getJoueurCourant();
    void fin();
}
