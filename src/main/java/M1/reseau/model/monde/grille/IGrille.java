package M1.reseau.model.monde.grille;

import M1.reseau.model.bateau.IBateau;
import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.monde.element.ICase;

import java.util.List;

public interface IGrille {
    List<ICase> get_listeCase();
    void ajouterCase(ICase _case) throws IGrilleException;
    void supprimerCase(ICase _case) throws IGrilleException;

    List<IBateau> get_listeBateau();
    void ajouterBateau(IBateau _bateau) throws IGrilleException;
    void supprimerBateau(IBateau _bateau) throws IGrilleException;

    boolean isDansLaGrille(ICase _case);
    boolean isDansLaGrille(IBateau _bateau);
}
