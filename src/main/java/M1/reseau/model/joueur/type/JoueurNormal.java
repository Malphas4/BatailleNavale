package M1.reseau.model.joueur.type;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.joueur.Joueur;
import M1.reseau.model.joueur.visitor.IVisitorJoueur;
import M1.reseau.model.monde.grille.Grille;

public class JoueurNormal extends Joueur {

    private Grille _grilleJoueur; /* Grille du joueur */
    private Grille _grilleTouche; /* Grille de touche */

    public JoueurNormal(String _pseudo, Grille _grilleJoueur, Grille _grilleTouche) throws IJoueurException {
        super(_pseudo);
        set_grilleJoueur(_grilleJoueur);
        set_grilleTouche(_grilleTouche);
    }

    public Grille get_grilleJoueur() {
        return _grilleJoueur;
    }

    public void set_grilleJoueur(Grille _grilleJoueur) {
        if (_grilleJoueur == null) throw new IllegalArgumentException("JoueurNormal : La grille du joueur ne peut pas être null.");
        this._grilleJoueur = _grilleJoueur;
    }

    public Grille get_grilleTouche() {
        return _grilleTouche;
    }

    public void set_grilleTouche(Grille _grilleTouche) throws IJoueurException {
        if (_grilleTouche == null) throw new IllegalArgumentException("JoueurNormal : La grille de touche ne peut pas être null.");
        if (get_grilleJoueur().get_largeur() != _grilleTouche.get_largeur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même largeur.");
        if (get_grilleJoueur().get_longueur() != _grilleTouche.get_longueur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même longueur.");

        this._grilleTouche = _grilleTouche;
    }

    @Override
    public void accepte(IVisitorJoueur _ivj) throws IJoueurException {
        _ivj.visite(this);
    }

}
