package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

public class Joueur implements IJoueur {

    private String _pseudo;

    public Joueur() {
        this._pseudo = "Joueur";
    }

    public Joueur(String _pseudo) {
        if (_pseudo.trim().isEmpty()) throw new IllegalArgumentException("Joueur : pseudo est vide.");
        this._pseudo = _pseudo;
    }

    public String get_pseudo() {
        return _pseudo;
    }

    public void set_pseudo(String _pseudo) {
        this._pseudo = _pseudo;
    }

    @Override
    public void accepte(IVisitorJoueur _ivj) throws IJoueurException {
        _ivj.visite(this);
    }
}
