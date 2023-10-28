package M1.reseau.modele.bateau;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.IGrille;

public interface IBateau {
    boolean isSurLaGrille(IGrille grille);
    void ajouterCase(ICase _case) throws IBateauException;
}
