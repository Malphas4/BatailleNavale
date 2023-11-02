package M1.reseau.modele.joueur.type;

import M1.reseau.modele.joueur.Joueur;
import M1.reseau.modele.monde.grille.Grille;
import M1.reseau.modele.monde.grille.IGrille;

import java.util.Map;

public class JoueurNormal extends Joueur {

    private IGrille _grilleJoueur; /* Grille du joueur */
    private Map<Joueur, IGrille> _grilleTouche; /* Grille de touche */

    public JoueurNormal(String _pseudo, IGrille _grilleJoueur) {
        super(_pseudo);
        this._grilleJoueur = _grilleJoueur;
    }

    public IGrille get_grilleJoueur() {
        return _grilleJoueur;
    }

    public void set_grilleJoueur(Grille _grilleJoueur) {
        if (_grilleJoueur == null) throw new IllegalArgumentException("JoueurNormal : La grille du joueur ne peut pas être null.");
        this._grilleJoueur = _grilleJoueur;
    }

    public Map<Joueur, IGrille> get_grilleTouche() {
        return _grilleTouche;
    }

    public void set_grilleTouche(Map<Joueur, IGrille> _grilleTouche) {
        if (_grilleTouche == null) throw new IllegalArgumentException("JoueurNormal : La grille de touche ne peut pas être null.");
        this._grilleTouche = _grilleTouche;
    }

    public void add_grilleTouche(String _pseudo, IGrille _grille) {
        get_grilleTouche().put(new Joueur(_pseudo), _grille);
    }

}
