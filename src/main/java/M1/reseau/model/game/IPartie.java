package M1.reseau.model.game;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.IJoueur;

public interface IPartie {
    void commencer() throws IPartieException;
    void tourSuivant() throws IPartieException;
    IJoueur getJoueurCourant() throws IPartieException;
    IJoueur getJoueurAdverse() throws IPartieException;
    void ajouterJoueur(String _pseudo) throws IPartieException;
    void fin() throws IPartieException;
    boolean gagnant();
}
