package M1.reseau.modele.joueur.type;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.Joueur;
import M1.reseau.modele.monde.grille.Grille;

import java.util.Map;

public class JoueurNormal extends Joueur {

    private Grille _grilleJoueur; /* Grille du joueur */
    private Map<JoueurNormal, Grille> _grilleTouche; /* Grille de touche */

    public JoueurNormal(String _pseudo, Grille _grilleJoueur) {
        super(_pseudo);
        this._grilleJoueur = _grilleJoueur;
    }

    public Grille get_grilleJoueur() {
        return _grilleJoueur;
    }

    public void set_grilleJoueur(Grille _grilleJoueur) {
        if (_grilleJoueur == null) throw new IllegalArgumentException("JoueurNormal : La grille du joueur ne peut pas être null.");
        this._grilleJoueur = _grilleJoueur;
    }

    public Map<JoueurNormal, Grille> get_grilleTouche() {
        return _grilleTouche;
    }

    public void set_grilleTouche(Map<JoueurNormal, Grille> _grilleTouche) {
        if (_grilleTouche == null) throw new IllegalArgumentException("JoueurNormal : La grille de touche ne peut pas être null.");
        this._grilleTouche = _grilleTouche;
    }

    public void add_grilleTouche(JoueurNormal _joueur) throws IJoueurException {
        if (_joueur.get_pseudo() == null) throw new IllegalArgumentException("JoueurNormal : Le pseudo ne peut pas être null.");
        if (_joueur.get_grilleJoueur() == null) throw new IllegalArgumentException("JoueurNormal : La grille ne peut pas être null.");
        if (get_grilleJoueur().get_largeur() != _joueur.get_grilleJoueur().get_largeur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même largeur.");
        if (get_grilleJoueur().get_longueur() != _joueur.get_grilleJoueur().get_longueur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même longueur.");
        if (get_grilleTouche().containsKey(_joueur)) throw new IJoueurException("JoueurNormal : Le joueur existe déjà dans la map.");
        /* Ajout dans la map */
        get_grilleTouche().put(_joueur, _joueur.get_grilleJoueur());
    }

}
