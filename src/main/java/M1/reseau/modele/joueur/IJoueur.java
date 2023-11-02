package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

public interface IJoueur {
    String get_pseudo();
    void set_pseudo(String _pseudo);
    void accepte(IVisitorJoueur _ivj) throws IJoueurException;
}
