package M1.reseau.modele.joueur.visitor.tirer;

import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.visitor.IVisitorJoueur;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseBateau;
import M1.reseau.modele.monde.element.type.CaseRate;
import M1.reseau.modele.monde.element.type.CaseTouche;
import M1.reseau.modele.monde.grille.IGrille;

public class VisitorTirer implements IVisitorJoueur {

    private IGrille _grille;
    private ICase _case;

    public VisitorTirer(IGrille _grille, ICase _case) {
        set_grille(_grille);
        set_case(_case);
    }

    public IGrille get_grille() {
        return _grille;
    }

    public void set_grille(IGrille _grille) {
        if (_grille == null) throw new IllegalArgumentException("VisitorTirer : La grille ne peut pas être null.");
        this._grille = _grille;
    }

    public ICase get_case() {
        return _case;
    }

    public void set_case(ICase _case) {
        if (_case == null) throw new IllegalArgumentException("VisitorTirer : La case ne peut pas être null.");
        this._case = _case;
    }

    @Override
    public void visite(IJoueur _joueur) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorTirer : La grille ne peut pas être null.");
        if (get_case() == null) throw new IllegalArgumentException("VisitorTirer : La case ne peut pas être null.");

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorTirer : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isPlacable()) throw new IJoueurException("VisitorTirer : La case n'est pas valide.");

        /* Definition de la nouvelle case en fonction de la case tirer */
        ICase _newcase;

        if (get_case() instanceof CaseBateau) {
            _newcase = new CaseTouche(get_case().get_x(), get_case().get_y());
        } else {
            _newcase = new CaseRate(get_case().get_x(), get_case().get_y());
        }

        /* On supprime l'ancienne case de la liste dans la grille */
        try {
            get_grille().supprimerCase(get_case());
            get_grille().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorTirer : La case n'a pas pu être supprimé de la grille");
        }
    }
}
