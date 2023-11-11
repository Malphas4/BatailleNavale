package M1.reseau.model.player.visitor;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.type.JoueurNormal;

public interface IVisitorJoueur {

    void visite(JoueurNormal _joueur) throws IJoueurException;
}
