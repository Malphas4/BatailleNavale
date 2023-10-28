package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

public abstract class Joueur implements IJoueur {

    private String _pseudo;

    public Joueur() {
        this._pseudo = "Joueur";
    }

    public Joueur(String _pseudo) {
        if (_pseudo.trim().isEmpty()) throw new IllegalArgumentException("Joueur : pseudo est vide.");
        this._pseudo = _pseudo;
    }

    public String getPseudo() {
        return _pseudo;
    }

    public void setPseudo(String _pseudo) {
        this._pseudo = _pseudo;
    }

    @Override
    public void accepte(IVisitorJoueur _ivj) throws IJoueurException {
        _ivj.visite(this);
    }
}
