package M1.reseau.modele.joueur;

public class Joueur {
    private String pseudo;

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
}
