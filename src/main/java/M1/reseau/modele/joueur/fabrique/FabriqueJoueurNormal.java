package M1.reseau.modele.joueur.fabrique;

import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.type.JoueurNormal;
import M1.reseau.modele.monde.grille.IGrille;

public class FabriqueJoueurNormal implements IFabriqueJoueur {
    @Override
    public IJoueur creerJoueur(String _pseudo, IGrille _grille) {
        return new JoueurNormal(_pseudo, _grille);
    }
}
