package M1.reseau.modele.joueur;

import M1.reseau.modele.monde.grille.IGrille;

import java.util.Map;

public abstract class Joueur implements IJoueur {
    private String pseudo;
    private IGrille _jgrille; /* Grille du joueur */
    private Map<Joueur, IGrille> _tgrille; /* Grille de touche */

    public Joueur() {
        this.pseudo = "Joueur";
    }

    public Joueur(String pseudo) {
        if (pseudo.trim().isEmpty()) throw new IllegalArgumentException("Joueur : pseudo est vide.");
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public IGrille get_jgrille() {
        return _jgrille;
    }

    public void set_jgrille(IGrille _jgrille) {
        this._jgrille = _jgrille;
    }

    public Map<Joueur, IGrille> get_tgrille() {
        return _tgrille;
    }

    public void set_tgrille(Map<Joueur, IGrille> _tgrille) {
        this._tgrille = _tgrille;
    }
}
