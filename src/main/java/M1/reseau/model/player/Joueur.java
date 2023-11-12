package M1.reseau.model.player;

import java.util.Objects;
import java.util.UUID;

public abstract class Joueur implements IJoueur {

    /***************************************
     * Déclaration des variables de classe
     ***************************************/
    private UUID _uuid = UUID.randomUUID();
    private String _pseudo;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    protected Joueur() {
        this._pseudo = "Joueur";
    }

    protected Joueur(String _pseudo) {
        if (_pseudo.trim().isEmpty()) throw new IllegalArgumentException("Joueur : pseudo est vide.");
        this._pseudo = _pseudo;
    }

    public UUID get_uuid() {
        return _uuid;
    }

    public void set_uuid(UUID _uuid) {
        this._uuid = _uuid;
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

    @Override
    public String toString() {
        return "Joueur{" +
                "_uuid=" + _uuid +
                ", _pseudo='" + _pseudo + '\'' +
                '}';
    }
}
