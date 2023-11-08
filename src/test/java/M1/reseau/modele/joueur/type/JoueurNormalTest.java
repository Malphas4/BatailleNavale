package M1.reseau.modele.joueur.type;

import M1.reseau.modele.exception.IJoueurException;
import M1.reseau.modele.monde.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurNormalTest {

    private JoueurNormal _joueur1;
    private JoueurNormal _joueur2;
    private JoueurNormal _joueur3;

    @BeforeEach
    void setUp() {
        Grille _grille1 = new Grille(8, 8);
        Grille _grille2 = new Grille(8, 8);
        Grille _grille3 = new Grille(4, 4);

        Grille _touche1 = new Grille(8, 8);
        Grille _touche2 = new Grille(8, 8);
        Grille _touche3 = new Grille(4, 4);

        try {
            _joueur1 = new JoueurNormal("Joueur 1", _grille1, _touche1);
            _joueur2 = new JoueurNormal("Joueur 2", _grille2, _touche2);
            _joueur3 = new JoueurNormal("Joueur 1", _grille3, _touche3);
        } catch (IJoueurException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void constructeurTest() {
        /* Vérification que joueur n'est pas null */
        assertNotNull(_joueur1);

        /* Vérification que les attributs sont non null */
        assertNotNull(_joueur1.get_pseudo());
        assertNotNull(_joueur1.get_grilleJoueur());
        assertNotNull(_joueur1.get_grilleTouche());

        /* Vérification de la valeur des attributs */
        assertEquals("Joueur 1", _joueur1.get_pseudo());

        /* Vérification des grilles */
        assertEquals(new Grille(8, 8), _joueur1.get_grilleJoueur());
        assertEquals(new Grille(8, 8), _joueur1.get_grilleTouche());
    }

    @Test
    void set_grilleJoueurTest() {
        /* Création d'une grille 7x7 */
        Grille _grille = new Grille(7, 7);

        /* Vérification de la méthode */
        _joueur1.set_grilleJoueur(_grille);
        assertNotNull(_joueur1.get_grilleJoueur());
        assertEquals(_grille, _joueur1.get_grilleJoueur());
    }

    @Test
    void set_grilleJoueurErreurNullTest() {
        /* Création d'une grille null */
        Grille _grille = null;

        /* Vérification de la méthode */
        try {
            _joueur1.set_grilleJoueur(_grille);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(_joueur1.get_grilleJoueur());
            assertEquals(new Grille(8, 8), _joueur1.get_grilleJoueur());
        }
    }

    @Test
    void set_grilleToucheTest() {
        /* Création d'une grille 8x8 */
        Grille _grille = new Grille(8, 8);

        /* Vérification de la méthode */
        try {
            _joueur1.set_grilleTouche(_grille);
            assertNotNull(_joueur1.get_grilleTouche());
            assertEquals(_grille, _joueur1.get_grilleTouche());
        } catch (IJoueurException e) {
            fail();
        }
    }

    @Test
    void set_grilleToucheErreurNullTest() {
        /* Création d'une grille 8x8 */
        Grille _grille = null;

        /* Vérification de la méthode */
        try {
            _joueur1.set_grilleTouche(_grille);
            fail();
        } catch (IllegalArgumentException | IJoueurException e) {
            assertNotNull(_joueur1.get_grilleTouche());
        }
    }

    @Test
    void set_grilleToucheErreurLargeurTest() {
        /* Création d'une grille 8x8 */
        Grille _grille = new Grille(8, 7);

        /* Vérification de la méthode */
        try {
            _joueur1.set_grilleTouche(_grille);
            fail();
        } catch (IJoueurException e) {
            assertNotNull(_joueur1.get_grilleTouche());
            assertNotEquals(_grille, _joueur1.get_grilleTouche());
        }
    }

    @Test
    void set_grilleToucheErreurLongueurTest() {
        /* Création d'une grille 8x8 */
        Grille _grille = new Grille(7, 8);

        /* Vérification de la méthode */
        try {
            _joueur1.set_grilleTouche(_grille);
            fail();
        } catch (IJoueurException e) {
            assertNotNull(_joueur1.get_grilleTouche());
            assertNotEquals(_grille, _joueur1.get_grilleTouche());
        }
    }

}