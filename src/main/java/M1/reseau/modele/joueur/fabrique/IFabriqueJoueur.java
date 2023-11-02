package M1.reseau.modele.joueur.fabrique;

import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.monde.grille.IGrille;

public interface IFabriqueJoueur {
    IJoueur creerJoueur(String _pseudo, IGrille _grille);
}
