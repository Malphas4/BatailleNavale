package M1.reseau.modele.jeu;

import M1.reseau.modele.exception.IPartieException;
import M1.reseau.modele.joueur.IJoueur;

public interface IPartie {
    void commencer();
    void tourSuivant() throws IPartieException;
    IJoueur getJoueurCourant();
    void fin();
}
