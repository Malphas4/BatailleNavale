package M1.reseau.model.player.visitor;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.bot.EasyBot;
import M1.reseau.model.player.bot.HardBot;
import M1.reseau.model.player.bot.MediumBot;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.element.CaseEtat;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.element.state.CaseRate;
import M1.reseau.model.world.element.state.CaseTouche;
import M1.reseau.model.world.grid.IGrille;

public class VisitorTirer implements IVisitorJoueur {

    private IGrille _grille;
    private ICase _case;

    public VisitorTirer() {

    }

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
    public void visite(JoueurNormal _joueur) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorTirer : La grille ne peut pas être null.");
        if (get_case() == null) throw new IllegalArgumentException("VisitorTirer : La case ne peut pas être null.");

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorTirer : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isTirable()) throw new IJoueurException("VisitorTirer : La case n'est pas valide.");

        /* Definition de la nouvelle case en fonction de la case tirer */
        ICase _newcase;
        // On ajoute soit une case touchée ou soit une case ratée
        if (get_case() instanceof CaseBateau) {
            _newcase = new CaseTouche(get_case().get_x(), get_case().get_y());
        } else {
            _newcase = new CaseRate(get_case().get_x(), get_case().get_y());
        }

        /* On supprime l'ancienne case de la liste dans la grille */
        try {
            ICase _supCase = _joueur.get_grilleTouche().get_caseParCoord(get_case().get_x(), get_case().get_y());
            _joueur.get_grilleTouche().supprimerCase(_supCase);
            _joueur.get_grilleTouche().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorTirer : La case n'a pas pu être supprimé de la grille");
        }
    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(EasyBot _bot) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorTirer : La grille ne peut pas être null.");

        /* Selection d'une case random */
        ICase _case = null;
        do {
            int x = 1 + (int) (Math.random() * ((get_grille().get_longueur() - 1) + 1));
            int y = 1 + (int) (Math.random() * ((get_grille().get_largeur() - 1) + 1));
            try {
                _case = get_grille().get_caseParCoord(x, y);
            } catch (IGrilleException e) {
                throw new RuntimeException(e);
            }
        } while (!(_case instanceof CaseEtat));

        /* Assignation de la case à tirer */
        set_case(_case);

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorTirer : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isTirable()) throw new IJoueurException("VisitorTirer : La case n'est pas valide.");

        /* Definition de la nouvelle case en fonction de la case tirer */
        ICase _newcase;
        // On ajoute soit une case touchée ou soit une case ratée
        if (get_case() instanceof CaseBateau) {
            _newcase = new CaseTouche(get_case().get_x(), get_case().get_y());
        } else {
            _newcase = new CaseRate(get_case().get_x(), get_case().get_y());
        }

        /* On supprime l'ancienne case de la liste dans la grille */
        try {
            ICase _supCase = _bot.get_grilleTouche().get_caseParCoord(get_case().get_x(), get_case().get_y());
            _bot.get_grilleTouche().supprimerCase(_supCase);
            _bot.get_grilleTouche().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorTirer : La case n'a pas pu être supprimé de la grille");
        }
    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(MediumBot _bot) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorTirer : La grille ne peut pas être null.");

        /* Selection d'une case random */
        ICase _case = null;
        do {
            int x = 1 + (int) (Math.random() * ((get_grille().get_longueur() - 1) + 1));
            int y = 1 + (int) (Math.random() * ((get_grille().get_largeur() - 1) + 1));
            try {
                _case = get_grille().get_caseParCoord(x, y);
                if (_case instanceof CaseEtat)
                    _case = get_grille().get_caseParCoord(x + 1, y);
                if (_case instanceof CaseEtat)
                    _case = get_grille().get_caseParCoord(x, y - 1);
                if (_case instanceof CaseEtat)
                    _case = get_grille().get_caseParCoord(x - 1, y);
                if (_case instanceof CaseEtat)
                    _case = get_grille().get_caseParCoord(x, y + 1);
            } catch (IGrilleException e) {
                throw new RuntimeException(e);
            }
        } while (!(_case instanceof CaseEtat));

        /* Assignation de la case à tirer */
        set_case(_case);

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorTirer : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isTirable()) throw new IJoueurException("VisitorTirer : La case n'est pas valide.");

        /* Definition de la nouvelle case en fonction de la case tirer */
        ICase _newcase;
        // On ajoute soit une case touchée ou soit une case ratée
        if (get_case() instanceof CaseBateau) {
            _newcase = new CaseTouche(get_case().get_x(), get_case().get_y());
        } else {
            _newcase = new CaseRate(get_case().get_x(), get_case().get_y());
        }

        /* On supprime l'ancienne case de la liste dans la grille */
        try {
            ICase _supCase = _bot.get_grilleTouche().get_caseParCoord(get_case().get_x(), get_case().get_y());
            _bot.get_grilleTouche().supprimerCase(_supCase);
            _bot.get_grilleTouche().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorTirer : La case n'a pas pu être supprimé de la grille");
        }
    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(HardBot _bot) throws IJoueurException {

    }

    @Override
    public String toString() {
        return "VisitorTirer{" +
                "_grille=" + _grille +
                ", _case=" + _case +
                '}';
    }
}
