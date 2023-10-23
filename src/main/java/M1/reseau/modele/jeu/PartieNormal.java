package M1.reseau.modele.jeu;

import M1.reseau.modele.joueur.IJoueur;

public class PartieNormal extends Partie {
    public PartieNormal(String pseudo) {
        super(pseudo);
    }

    public PartieNormal(int _nbjoueur) {
        super(_nbjoueur);
    }

    @Override
    public void commencer() {

    }

    @Override
    public void tourSuivant() {

    }

    @Override
    public IJoueur getJoueurCourant() {
        return null;
    }

    @Override
    public void fin() {

    }
}
