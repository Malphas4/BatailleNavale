package M1.reseau.model.joueur;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.joueur.visitor.IVisitorJoueur;

public interface IJoueur {
    String get_pseudo();
    void set_pseudo(String _pseudo);
    void accepte(IVisitorJoueur _ivj) throws IJoueurException;
}
