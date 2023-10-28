package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;
import M1.reseau.modele.monde.element.Case;
import M1.reseau.modele.monde.grille.Grille;

public interface IJoueur {
    void accepte(IVisitorJoueur _ivj) throws IJoueurException;
}
