package M1.reseau.model.player.bot;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.visitor.IVisitorJoueur;
import M1.reseau.model.world.grid.Grille;

public class MediumBot extends Bot {


    public MediumBot(String _pseudo, Grille _grilleBot, Grille _grilleTouche) throws IJoueurException {
        super(_pseudo, _grilleBot, _grilleTouche);
    }

    /**
     * @param _ivj
     * @throws IJoueurException
     */
    @Override
    public void accepte(IVisitorJoueur _ivj) throws IJoueurException {
        _ivj.visite(this);
    }
}
