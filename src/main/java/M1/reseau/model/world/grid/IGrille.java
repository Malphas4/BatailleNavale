package M1.reseau.model.world.grid;

import M1.reseau.model.ship.IBateau;
import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.world.element.ICase;

import java.util.List;

public interface IGrille {
    int get_longueur();
    int get_largeur();

    List<ICase> get_listeCase();
    void ajouterCase(ICase _case) throws IGrilleException;
    void supprimerCase(ICase _case) throws IGrilleException;
    ICase get_caseParCoord(int _x, int _y) throws IGrilleException;

    List<IBateau> get_listeBateau();
    void ajouterBateau(IBateau _bateau) throws IGrilleException;
    void supprimerBateau(IBateau _bateau) throws IGrilleException;

    boolean isDansLaGrille(ICase _case);
    boolean isDansLaGrille(IBateau _bateau);
}
