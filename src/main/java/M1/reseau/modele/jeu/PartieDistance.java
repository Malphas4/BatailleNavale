package M1.reseau.modele.jeu;

import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.type.JoueurNormal;
import M1.reseau.modele.monde.grille.IGrille;

public class PartieDistance extends Partie {

    private JoueurNormal _joueur;

    public PartieDistance(int _nbjoueur) {
        super(_nbjoueur);
    }

    public PartieDistance(String _pseudo, IGrille _grille) {
        super();
        set_joueur(new JoueurNormal(_pseudo, _grille));
    }

    public JoueurNormal get_joueur() {
        return _joueur;
    }

    public void set_joueur(JoueurNormal _joueur) {
        this._joueur = _joueur;
    }

    @Override
    public void commencer() {

    }

    @Override
    public void tourSuivant() {

    }

    @Override
    public IJoueur getJoueurCourant() {
        return null;
    }

    @Override
    public void fin() {

    }
}
