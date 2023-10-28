package M1.reseau.modele.joueur.visitor;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.monde.element.ICase;

public interface IVisitorJoueur {
    void visite(IJoueur _joueur) throws IJoueurException;
}
