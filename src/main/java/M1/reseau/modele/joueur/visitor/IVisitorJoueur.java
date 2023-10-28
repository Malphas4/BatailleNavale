package M1.reseau.modele.joueur.visitor;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.IJoueur;

public interface IVisitorJoueur {
    void visite(IJoueur _joueur) throws IJoueurException;
}
