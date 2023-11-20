package M1.reseau.model.player.bot;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.Joueur;
import M1.reseau.model.world.grid.Grille;

public abstract class Bot extends Joueur {
    private Grille _grilleBot; /* Grille du bot */
    private Grille _grilleTouche; /* Grille de touche */

    public Bot(String _pseudo, Grille _grilleBot, Grille _grilleTouche) throws IJoueurException {
        super(_pseudo);
        set_grilleBot(_grilleBot);
        set_grilleTouche(_grilleTouche);
    }

    public Grille get_grilleBot() {
        return _grilleBot;
    }

    public void set_grilleBot(Grille _grilleBot) {
        if (_grilleBot == null) throw new IllegalArgumentException("JoueurNormal : La grille du joueur ne peut pas être null.");
        this._grilleBot = _grilleBot;
    }

    public Grille get_grilleTouche() {
        return _grilleTouche;
    }

    public void set_grilleTouche(Grille _grilleTouche) throws IJoueurException {
        if (_grilleTouche == null) throw new IllegalArgumentException("JoueurNormal : La grille de touche ne peut pas être null.");
        if (get_grilleBot().get_largeur() != _grilleTouche.get_largeur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même largeur.");
        if (get_grilleBot().get_longueur() != _grilleTouche.get_longueur()) throw new IJoueurException("JoueurNormal : La grille n'a pas la même longueur.");

        this._grilleTouche = _grilleTouche;
    }
}
