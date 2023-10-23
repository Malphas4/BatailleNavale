package M1.reseau.modele.monde.grille.type;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;

public class Grille8x8 extends Grille {

    private final int LARGEUR = 8;
    private final int LONGUEUR = 8;

    @Override
    public void ajouterCase(ICase _case) throws IGrilleException {
        /* Verification case n'est pas null */
        if (_case == null) throw new IllegalArgumentException("Grille8x8 : La case a ajouté ne peut pas être null.");

        /* Verification case est dans la grille */
        if (_case.get_x() <= 0 && _case.get_x() > LONGUEUR) throw new IGrilleException("Grille8x8 : La coordonnée x de la case ne sont pas dans la grille");
        if (_case.get_y() <= 0 && _case.get_y() > LARGEUR) throw new IGrilleException("Grille8x8 : La coordonnée x de la case ne sont pas dans la grille");

        /* On ajoute la case dans la liste de la grille */
        this.getCases().add(_case);
    }

    @Override
    public void ajouterBateau(IBateau _bateau) throws IGrilleException {
        /* Verification bateau n'est pas null */
        if (_bateau == null) throw new IllegalArgumentException("Grille8x8 : Le bateau a ajouté ne peut pas être null.");

        /* Verication bateau est sur la grille */
        if (!_bateau.isSurLaGrille(this)) throw new IGrilleException("Grille8x8 : Le bateau doit être sur la grille.");

        /* On ajoute le bateau dans la liste de la grille */
        this.getBateaux().add(_bateau);
    }

    @Override
    public void supprimerCase(ICase _case) throws IGrilleException {
        /* Verification case n'est pas null */
        if (_case == null) throw new IllegalArgumentException("Grille8x8 : La case a ajouté ne peut pas être null.");

        /* Verification case est dans la liste de la grille */
        if (!this.getCases().contains(_case)) throw new IGrilleException("Grille8x8 : La case n'est pas dans la liste de la grille.");

        /* On supprime la case dans la liste de la grille */
        this.getCases().remove(_case);
    }

    @Override
    public void supprimerBateau(IBateau _bateau) throws IGrilleException {
        /* Verification bateau n'est pas null */
        if (_bateau == null) throw new IllegalArgumentException("Grille8x8 : La case a ajouté ne peut pas être null.");

        /* Verification bateau est dans la liste de la grille */
        if (!this.getCases().contains(_bateau)) throw new IGrilleException("Grille8x8 : Le bateau n'est pas dans la liste de la grille.");

        /* On supprime la case dans la liste de la grille */
        this.getCases().remove(_bateau);

    }
}
