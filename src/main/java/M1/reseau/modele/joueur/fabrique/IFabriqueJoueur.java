package M1.reseau.modele.joueur.fabrique;

import M1.reseau.modele.joueur.IJoueur;

public interface IFabriqueJoueur {
    IJoueur creerJoueur(String _pseudo);
}
