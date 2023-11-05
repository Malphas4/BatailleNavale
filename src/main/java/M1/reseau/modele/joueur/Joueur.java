package M1.reseau.modele.joueur;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

import java.util.Objects;

public abstract class Joueur implements IJoueur {

    private String _pseudo;

    protected Joueur() {
        this._pseudo = "Joueur";
    }

    protected Joueur(String _pseudo) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(get_pseudo(), joueur.get_pseudo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_pseudo());
    }
}
