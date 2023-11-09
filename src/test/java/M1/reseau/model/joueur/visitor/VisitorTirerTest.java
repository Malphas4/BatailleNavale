package M1.reseau.model.joueur.visitor;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.joueur.type.JoueurNormal;
import M1.reseau.model.monde.element.ICase;
import M1.reseau.model.monde.element.type.*;
import M1.reseau.model.monde.grille.Grille;
import M1.reseau.model.monde.grille.fabrique.FabriqueGrille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTirerTest {

    private VisitorTirer _visitorVide;
    private VisitorTirer _visitorPlein;

    private JoueurNormal _joueur1;
    private JoueurNormal _joueur2;

    private VisitorTirer _visitor1;
    private VisitorTirer _visitor2;

    @BeforeEach
    void setUp() {
        FabriqueGrille _fabrique = new FabriqueGrille(8, 8);

        _visitorVide = new VisitorTirer();
        _visitorPlein = new VisitorTirer(_fabrique.creerGrille(), new CaseNormal(1, 1));

        try {
            _joueur1 = new JoueurNormal("Joueur 1", (Grille) _fabrique.creerGrille(), (Grille) _fabrique.creerGrille());
            _joueur2 = new JoueurNormal("Joueur 2", (Grille) _fabrique.creerGrille(), (Grille) _fabrique.creerGrille());
        } catch (IJoueurException e) {
            throw new RuntimeException(e);
        }

        _visitor1 = new VisitorTirer();
        _visitor2 = new VisitorTirer();
    }

    @Test
    void constructeurVideTest() {
        /* Vérification visitor non null */
        assertNotNull(_visitorVide);

        /* Vérification attribut */
        assertNull(_visitorVide.get_grille());
        assertNull(_visitorVide.get_case());
    }

    @Test
    void constructeurTest() {
        /* Vérification visitor non null */
        assertNotNull(_visitorPlein);

        /* Vérification attribut */
        assertNotNull(_visitorPlein.get_grille());
        assertNotNull(_visitorPlein.get_case());

        /* Vérification valeur attribut */
        assertEquals(new Grille(8, 8), _visitorPlein.get_grille());
        assertEquals(new CaseNormal(1,1), _visitorPlein.get_case());
    }

    @Test
    void set_grilleTest() {
        /* Création d'une grille 7x7 */
        Grille _grille = new Grille(7, 7);

        /* Vérification de la méthode */
        _visitorVide.set_grille(_grille);
        assertNotNull(_visitorVide.get_grille());
        assertEquals(_grille, _visitorVide.get_grille());
    }

    @Test
    void set_grilleErreurNullTest() {
        /* Création d'une grille 7x7 */
        Grille _grille = null;

        /* Vérification de la méthode */
        try {
            _visitorVide.set_grille(_grille);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(_visitorVide.get_grille());
        }
    }

    @Test
    void set_case() {
        /* Création d'une grille 7x7 */
        CaseNormal _case = new CaseNormal(1,1);

        /* Vérification de la méthode */
        _visitorVide.set_case(_case);
        assertNotNull(_visitorVide.get_case());
        assertEquals(_case, _visitorVide.get_case());
    }

    @Test
    void set_caseErreurNullTest() {
        /* Création d'une grille 7x7 */
        CaseNormal _case = null;

        /* Vérification de la méthode */
        try {
            _visitorVide.set_case(_case);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(_visitorVide.get_case());
        }
    }

    /**
     * Le joueur 1 tire la case (4, 4) du joueur 2
     */
    @Test
    void visiteTirRateTest() {
        /* On récupère la case de la grille */
        ICase _case = null;
        try {
            _case = _joueur2.get_grilleJoueur().get_caseParCoord(4, 4);
            assertNotNull(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* Initialisation du visitor */
        _visitor1.set_grille(_joueur2.get_grilleJoueur());
        _visitor1.set_case(_case);

        /* Vérification de la méthode */
        try {
            _joueur1.accepte(_visitor1);
            ICase _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
            assertNotNull(_ncase);
            assertInstanceOf(CaseRate.class, _ncase);
            assertEquals(4, _ncase.get_x());
            assertEquals(4, _ncase.get_y());
        } catch (IJoueurException e) {
            fail();
        } catch (IGrilleException e) {
            fail();
        }
    }

    /**
     * Le joueur 1 tire la case bateau (4, 4) du joueur 2
     */
    @Test
    void visiteTirToucheTest() {
        /* On récupère la case de la grille */
        // On initialise
        ICase _case = null;
        ICase _bcase = new CaseBateau(4, 4);
        try {
            // On remplace la case normal par une case bateau du joueur 2
            _joueur2.get_grilleJoueur().supprimerCase(new CaseNormal(4, 4));
            _joueur2.get_grilleJoueur().ajouterCase(_bcase);

            // On récupère dans _case la case bateau
            _case = _joueur2.get_grilleJoueur().get_caseParCoord(4, 4);
            assertInstanceOf(CaseBateau.class, _case);
            assertNotNull(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* Initialisation du visitor */
        // On met la grille du joueur 2
        _visitor1.set_grille(_joueur2.get_grilleJoueur());
        // On met la case à toucher de la grille du joueur 2
        _visitor1.set_case(_case);

        /* Vérification de la méthode */
        ICase _ncase = null;
        try {
            _joueur1.accepte(_visitor1);
            _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
        } catch (IJoueurException | IGrilleException e) {
            fail(e);
        }

        assertNotNull(_ncase);
        assertInstanceOf(CaseTouche.class, _ncase);
        assertEquals(4, _ncase.get_x());
        assertEquals(4, _ncase.get_y());
    }

    /**
     * Le joueur 1 tir sur le joueur 2 mais la grille n'est pas définit
     * dans le visiteur.
     */
    @Test
    void visiteErreurGrilleNullTest() {
        /**
         *  On utilise _visiteVide pour avoir la grille vide
         *  On vérifie directement la méthode
         **/
        /* On récupère la case de la grille */
        ICase _case = null;
        try {
            _case = _joueur2.get_grilleJoueur().get_caseParCoord(4, 4);
            assertNotNull(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* Initialisation du visitor sans la grille */
        _visitor1.set_case(_case);

        /* Vérification de la méthode */
        ICase _ncase = null;
        try {
            _joueur1.accepte(_visitor1);
            fail();
        } catch (IJoueurException e) {
            fail();
        } catch (IllegalArgumentException e) {
            try {
                _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
            } catch (IGrilleException ex) {
                fail(ex);
            }
            assertNotNull(_ncase);
            assertInstanceOf(CaseNormal.class, _ncase);
        }
    }

    @Test
    void visiteErreurCaseNullTest() {
        /**
         *  On utilise _visiteVide pour avoir la case vide
         *  On vérifie directement la méthode
         **/
        /* Initialisation du visitor sans la grille */
        // On met la grille du joueur 2
        _visitor1.set_grille(_joueur2.get_grilleJoueur());

        /* Vérification de la méthode */
        ICase _ncase = null;
        try {
            _joueur1.accepte(_visitor1);
            fail();
        } catch (IJoueurException e) {
            fail();
        } catch (IllegalArgumentException e) {
            try {
                _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
            } catch (IGrilleException ex) {
                fail(ex);
            }
            assertNotNull(_ncase);
            assertInstanceOf(CaseNormal.class, _ncase);
        }
    }

    @Test
    void visiteErreurPasDansLaGrilleTest() {
        /**
         *  On utilise _visiteVide pour avoir la case vide
         *  On vérifie directement la méthode
         **/
        /* Initialisation du visitor sans la grille */
        // On met la grille du joueur 2
        _visitor1.set_grille(_joueur2.get_grilleJoueur());
        _visitor1.set_case(new CaseNormal(10, 10));

        /* Vérification de la méthode */
        ICase _ncase = null;
        try {
            _joueur1.accepte(_visitor1);
            fail();
        } catch (IJoueurException e) {
            try {
                _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
            } catch (IGrilleException ex) {
                fail(ex);
            }
            assertNotNull(_ncase);
            assertInstanceOf(CaseNormal.class, _ncase);
        }
    }

    @Test
    void visiteErreurTireSurCaseNonTirableTest() {
        /* On récupère la case de la grille */
        // On initialise
        ICase _case = null;
        ICase _bcase = new CaseObstacle(4, 4);
        try {
            // On remplace la case normal par une case bateau du joueur 2
            _joueur2.get_grilleJoueur().supprimerCase(new CaseNormal(4, 4));
            _joueur2.get_grilleJoueur().ajouterCase(_bcase);

            // On récupère dans _case la case bateau
            _case = _joueur2.get_grilleJoueur().get_caseParCoord(4, 4);
            assertInstanceOf(CaseObstacle.class, _case);
            assertNotNull(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* Initialisation du visitor */
        // On met la grille du joueur 2
        _visitor1.set_grille(_joueur2.get_grilleJoueur());
        // On met la case à toucher de la grille du joueur 2
        _visitor1.set_case(_case);

        /* Vérification de la méthode */
        ICase _ncase = null;
        try {
            _joueur1.accepte(_visitor1);
            fail();
        } catch (IJoueurException e) {
            try {
                _ncase = _joueur1.get_grilleTouche().get_caseParCoord(4, 4);
            } catch (IGrilleException ex) {
                fail(ex);
            }
            assertNotNull(_ncase);
            assertInstanceOf(CaseNormal.class, _ncase);
        }
    }
}