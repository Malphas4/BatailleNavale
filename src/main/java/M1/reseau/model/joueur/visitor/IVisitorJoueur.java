package M1.reseau.model.joueur.visitor;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.joueur.type.JoueurNormal;

public interface IVisitorJoueur {

    void visite(JoueurNormal _joueur) throws IJoueurException;
}
