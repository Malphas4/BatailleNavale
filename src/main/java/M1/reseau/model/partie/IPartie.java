package M1.reseau.model.partie;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.joueur.IJoueur;

public interface IPartie {
    void commencer() throws IPartieException;
    void tourSuivant() throws IPartieException;
    IJoueur getJoueurCourant() throws IPartieException;
    void ajouterJoueur(String _pseudo) throws IPartieException;
    void fin() throws IPartieException;
}
