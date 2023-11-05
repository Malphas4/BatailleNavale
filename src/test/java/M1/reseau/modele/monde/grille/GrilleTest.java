package M1.reseau.modele.monde.grille;

import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.monde.element.type.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {

    private Grille _grille;
    private Grille _grille4x4;
    private Grille _grille8x8;

    @BeforeEach
    void setUp() {
        this._grille = new Grille(8, 8);
        this._grille4x4 = new Grille(4, 4);
        this._grille8x8 = new Grille(8, 8);
    }

    @Test
    void constructeurTest() {
        /* On créée une grille de longueur 1 et largeur 2 */
        Grille _grille = new Grille(1, 2);
        
        /* On vérifie que _grille n'est pas null */
        assertNotNull(_grille);

        /* On vérifie que les attributs de _grille sont bonnes */
        assertEquals(1, _grille.get_longueur());
        assertEquals(2, _grille.get_largeur());
        
        /* On vérifie que les listes dans grille ne sont pas null */
        assertNotNull(_grille.get_listeCase());
        assertNotNull(_grille.get_listeBateau());

        /* On vérifie que ces listes ne contiennent aucun élément */
        assertEquals(0, _grille.get_listeCase().size());
        assertEquals(0, _grille.get_listeBateau().size());
    }

    @Test
    void egaliteGrilleTest() {
        /* On vérifie que _grille n'est pas null */
        assertNotNull(_grille);
        assertNotNull(_grille8x8);

        /* Verification attributs */
        assertEquals(_grille.get_longueur(), _grille8x8.get_longueur());
        assertEquals(_grille.get_largeur(), _grille8x8.get_largeur());

        /* Vérification égalité */
        assertEquals(_grille, _grille8x8);
    }

    @Test
    void nonEgaliteGrilleTest() {
        /* On vérifie que _grille n'est pas null */
        assertNotNull(_grille);
        assertNotNull(_grille4x4);

        /* Verification attributs */
        assertNotEquals(_grille.get_longueur(), _grille4x4.get_longueur());
        assertNotEquals(_grille.get_largeur(), _grille4x4.get_largeur());

        /* Vérification égalité */
        assertNotEquals(_grille, _grille4x4);
    }

    @Test
    void ajouterUneCaseNormalDansLaGrilleTest() {
        /* Création de la case normal */
        CaseNormal _case = new CaseNormal(1, 1);

        /* On vérifie que la grille n'est pas null */
        assertNotNull(_grille);

        /* On ajoute la case */
        try {
            _grille.ajouterCase(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* On vérifie la taille de la liste de case de la grille */
        assertEquals(1, _grille.get_listeCase().size());

        /* On vérifie que l'objet dans la liste est celui qu'on a ajouté */
        assertTrue(_grille.get_listeCase().contains(_case));
    }

    @Test
    void ajouterUneCaseBateauDansLaGrilleTest() {
        /* Création de la case bateau */
        CaseBateau _case = new CaseBateau(1, 1);

        /* On vérifie que la grille n'est pas null */
        assertNotNull(_grille);

        /* On ajoute la case */
        try {
            _grille.ajouterCase(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* On vérifie la taille de la liste de case de la grille */
        assertEquals(1, _grille.get_listeCase().size());

        /* On vérifie que l'objet dans la liste est celui qu'on a ajouté */
        assertTrue(_grille.get_listeCase().contains(_case));
    }

    @Test
    void ajouterUneCaseObstacleDansLaGrilleTest() {
        /* Création de la case Obstacle */
        CaseObstacle _case = new CaseObstacle(1, 1);

        /* On vérifie que la grille n'est pas null */
        assertNotNull(_grille);

        /* On ajoute la case */
        try {
            _grille.ajouterCase(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* On vérifie la taille de la liste de case de la grille */
        assertEquals(1, _grille.get_listeCase().size());

        /* On vérifie que l'objet dans la liste est celui qu'on a ajouté */
        assertTrue(_grille.get_listeCase().contains(_case));
    }

    @Test
    void ajouterUneCaseRateDansLaGrilleTest() {
        /* Création de la case raté */
        CaseRate _case = new CaseRate(1, 1);

        /* On vérifie que la grille n'est pas null */
        assertNotNull(_grille);

        /* On ajoute la case */
        try {
            _grille.ajouterCase(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* On vérifie la taille de la liste de case de la grille */
        assertEquals(1, _grille.get_listeCase().size());

        /* On vérifie que l'objet dans la liste est celui qu'on a ajouté */
        assertTrue(_grille.get_listeCase().contains(_case));
    }

    @Test
    void ajouterUneCaseToucheDansLaGrilleTest() {
        /* Création de la case raté */
        CaseTouche _case = new CaseTouche(1, 1);

        /* On vérifie que la grille n'est pas null */
        assertNotNull(_grille);

        /* On ajoute la case */
        try {
            _grille.ajouterCase(_case);
        } catch (IGrilleException e) {
            fail();
        }

        /* On vérifie la taille de la liste de case de la grille */
        assertEquals(1, _grille.get_listeCase().size());

        /* On vérifie que l'objet dans la liste est celui qu'on a ajouté */
        assertTrue(_grille.get_listeCase().contains(_case));
    }

}