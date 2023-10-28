package M1.reseau.modele.joueur.fabrique;

import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.type.JoueurNormal;

public class FabriqueJoueurNormal implements IFabriqueJoueur {
    @Override
    public IJoueur creerJoueur(String _pseudo) {
        return new JoueurNormal(_pseudo);
    }
}
