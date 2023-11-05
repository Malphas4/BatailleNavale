package M1.reseau.modele.joueur.visitor;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.Joueur;
import M1.reseau.modele.joueur.type.JoueurNormal;

public interface IVisitorJoueur {

    void visite(JoueurNormal _joueur) throws IJoueurException;
}
