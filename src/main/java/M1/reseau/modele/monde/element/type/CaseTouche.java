package M1.reseau.modele.monde.element.type;

import M1.reseau.modele.monde.element.CaseEtat;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;

public class CaseTouche extends CaseEtat {
    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    public CaseTouche(int _x, int _y) {
        super(_x, _y);
    }
}
