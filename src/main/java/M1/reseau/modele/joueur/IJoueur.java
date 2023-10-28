package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

public interface IJoueur {
    void accepte(IVisitorJoueur _ivj) throws IJoueurException;
}
