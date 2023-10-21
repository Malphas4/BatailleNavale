package M1.reseau.modele.jeu;

import M1.reseau.modele.joueur.IJoueur;

public interface IPartie {
    void commencer();
    void tourSuivant();
    IJoueur getJoueurCourant();
    void fin();
}
