package M1.reseau.model.player.visitor;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.bot.EasyBot;
import M1.reseau.model.player.bot.HardBot;
import M1.reseau.model.player.bot.MediumBot;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.element.state.CaseRate;
import M1.reseau.model.world.element.state.CaseTouche;
import M1.reseau.model.world.grid.IGrille;

public class VisitorEstTire implements IVisitorJoueur {

    private IGrille _grille;
    private ICase _case;

    public VisitorEstTire() {

    }

    public VisitorEstTire(IGrille _grille, ICase _case) {
        set_grille(_grille);
        set_case(_case);
    }

    public IGrille get_grille() {
        return _grille;
    }

    public void set_grille(IGrille _grille) {
        if (_grille == null) throw new IllegalArgumentException("VisitorEstTire : La grille ne peut pas être null.");
        this._grille = _grille;
    }

    public ICase get_case() {
        return _case;
    }

    public void set_case(ICase _case) {
        if (_case == null) throw new IllegalArgumentException("VisitorEstTire : La case ne peut pas être null.");
        this._case = _case;
    }

    @Override
    public void visite(JoueurNormal _joueur) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorEstTire : La grille ne peut pas être null.");
        if (get_case() == null) throw new IllegalArgumentException("VisitorEstTire : La case ne peut pas être null.");

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorEstTire : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isTirable()) throw new IJoueurException("VisitorEstTire : La case n'est pas valide.");

        ICase _newcase = setupCaseEtat();

        /* On supprime l'ancienne case de la liste dans la grille  du joueur*/
        try {
            ICase _supCase = _joueur.get_grilleJoueur().get_caseParCoord(get_case().get_x(), get_case().get_y());
            _joueur.get_grilleJoueur().supprimerCase(_supCase);
            _joueur.get_grilleJoueur().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorEstTire : La case n'a pas pu être supprimé de la grille");
        }
    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(EasyBot _bot) throws IJoueurException {
        /* Verification arguments non null */
        if (get_grille() == null) throw new IllegalArgumentException("VisitorEstTire : La grille ne peut pas être null.");
        if (get_case() == null) throw new IllegalArgumentException("VisitorEstTire : La case ne peut pas être null.");

        /* Verification que la grille contient la case */
        if (!get_grille().isDansLaGrille(get_case())) throw new IJoueurException("VisitorEstTire : La case n'est pas dans la grille.");

        /* Si la case est valide */
        if (!get_case().isTirable()) throw new IJoueurException("VisitorEstTire : La case n'est pas valide.");

        ICase _newcase = setupCaseEtat();

        /* On supprime l'ancienne case de la liste dans la grille du joueur*/
        try {
            ICase _supCase = _bot.get_grilleBot().get_caseParCoord(get_case().get_x(), get_case().get_y());
            _bot.get_grilleBot().supprimerCase(_supCase);
            _bot.get_grilleBot().ajouterCase(_newcase);
        } catch (IGrilleException e) {
            System.err.println("VisitorEstTire : La case n'a pas pu être supprimé de la grille");
        }
    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(MediumBot _bot) throws IJoueurException {

    }

    /**
     * @param _bot
     * @throws IJoueurException
     */
    @Override
    public void visite(HardBot _bot) throws IJoueurException {

    }

    /**
     * Définit l'état de la case qui est touché
     * @return CaseEtat
     */
    private ICase setupCaseEtat() {
        /* Definition de la nouvelle case en fonction de la case tirer */
        ICase _newcase;
        // On ajoute soit une case touchée ou soit une case ratée
        if (get_case() instanceof CaseBateau) {
            _newcase = new CaseTouche(get_case().get_x(), get_case().get_y());
        } else {
            _newcase = new CaseRate(get_case().get_x(), get_case().get_y());
        }
        return _newcase;
    }

    @Override
    public String toString() {
        return "VisitorEstTire{" +
                "_grille=" + _grille +
                ", _case=" + _case +
                '}';
    }
}
