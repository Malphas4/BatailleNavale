package M1.reseau.model.player.bot;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.Joueur;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.grid.Grille;

public abstract class Bot extends JoueurNormal {

    public Bot(String _pseudo, Grille _grilleBot, Grille _grilleTouche) throws IJoueurException {
        super(_pseudo, _grilleBot, _grilleTouche);
    }

}
