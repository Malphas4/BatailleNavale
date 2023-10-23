package M1.reseau.modele.monde.grille;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.monde.element.ICase;

public interface IGrille {
    void ajouterCase(ICase _case) throws IGrilleException;
    void ajouterBateau(IBateau _bateau) throws IGrilleException;
    void supprimerCase(ICase _case) throws IGrilleException;
    void supprimerBateau(IBateau _bateau) throws IGrilleException;
}
