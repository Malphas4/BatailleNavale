package M1.reseau.modele.monde.grille;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.monde.element.ICase;

public interface IGrille {
    void ajouterCase(ICase _case);
    void ajouterBateau(IBateau _bateau);
    void supprimerCase(ICase _case);
    void supprimerBateau(IBateau _bateau);
}
