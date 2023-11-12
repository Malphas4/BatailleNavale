package M1.reseau.model.game.local;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.grid.Grille;
import M1.reseau.model.world.grid.builder.FabriqueGrille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartieLocaleTest {

    private PartieLocale _partieVide;
    private PartieLocale _partieJ1;
    private PartieLocale _partieJ12;

    @BeforeEach
    void setUp() {
        /* Initialisation d'une partie à vide */
        _partieVide = new PartieLocale(8, 8);

        /* Initialisation d'une partie avec 1 joueur */
        _partieJ1 = new PartieLocale(8, 8);
        try {
            _partieJ1.ajouterJoueur("Joueur 1");
        } catch (IPartieException e) {
            throw new RuntimeException(e);
        }

        /* Initialisation d'une partie avec 2 joueurs */
        _partieJ12 = new PartieLocale(8, 8);
        try {
            _partieJ12.ajouterJoueur("Joueur 1");
            _partieJ12.ajouterJoueur("Joueur 2");
        } catch (IPartieException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void constructeurTest() {
        /* Vérification que partie n'est pas null */
        assertNotNull(_partieVide);

        /* Vérification des attributs */
        // Vérification pour les joueurs
        assertNull(_partieVide.get_joueur1());
        assertNull(_partieVide.get_joueur2());
        assertFalse(_partieVide.is_aGagner1());
        assertFalse(_partieVide.is_aGagner2());
        // Vérification de la classe parente
        assertEquals(0, _partieVide.get_nbTour());
        assertFalse(_partieVide.is_commence());
        assertFalse(_partieVide.is_pause());
        assertFalse(_partieVide.is_termine());
        // Vérification de la grille
        assertNotNull(_partieVide.get_fabriqueGrille());
        assertEquals(new FabriqueGrille(8, 8), _partieVide.get_fabriqueGrille());
    }

    @Test
    void set_joueur1Test() {
        /* On ajoute un joueur 1 sur la partie vide */
        try {
            _partieVide.set_joueur1(new JoueurNormal(
                    "Joueur 1",
                    (Grille) _partieVide.get_fabriqueGrille().creerGrille(),
                    (Grille) _partieVide.get_fabriqueGrille().creerGrille()
            ));
            assertNotNull(_partieVide.get_joueur1());
        } catch (IJoueurException e) {
            fail();
        }
    }

    @Test
    void set_joueur1ErreurNullTest() {
        /* On ajoute un joueur null sur la partie vide */
        try {
            _partieVide.set_joueur1(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(_partieVide.get_joueur1());
        }
    }

    @Test
    void set_joueur2Test() {
        /* On ajoute un joueur 2 sur la partie vide */
        try {
            _partieVide.set_joueur2(new JoueurNormal(
                    "Joueur 2",
                    (Grille) _partieVide.get_fabriqueGrille().creerGrille(),
                    (Grille) _partieVide.get_fabriqueGrille().creerGrille()
            ));
            assertNotNull(_partieVide.get_joueur2());
        } catch (IJoueurException e) {
            fail();
        }
    }

    @Test
    void set_joueur2ErreurNullTest() {
        /* On ajoute un joueur null sur la partie vide */
        /* On test la méthode */
        try {
            _partieVide.set_joueur2(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(_partieVide.get_joueur2());
        }
    }

    @Test
    void commencerTest() {
        /* On test avec partie avec 2 joueurs */

        /* On test la méthode */
        try {
            _partieJ12.commencer();
            assertTrue(_partieJ12.is_commence());
        } catch (IPartieException e) {
            fail();
        }
    }

    @Test
    void commencerErreurJoueurNullTest() {
        /* On test avec partie avec 1 joueur */

        /* On test la méthode */
        try {
            _partieJ1.commencer();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partieJ1.is_commence());
        }
    }

    @Test
    void commencerErreurCommenceTest() {
        /* On test partie avec 2 joueurs */
        _partieJ12.set_commence(true);

        /* On test la méthode */
        try {
            _partieJ12.commencer();
            fail();
        } catch (IPartieException e) {
            assertTrue(_partieJ12.is_commence());
        }
    }

    @Test
    void commencerErreurTermineTest() {
        /* On test partie avec 2 joueurs */
        _partieJ12.set_termine(true);

        /* On test la méthode */
        try {
            _partieJ12.commencer();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partieJ12.is_commence());
        }
    }

    @Test
    void tourSuivantTest() {
        /* On initialise partie j12 */
        _partieJ12.set_commence(true);

        /* On test la méthode */
        try {
            _partieJ12.tourSuivant();
            assertEquals(1, _partieJ12.get_nbTour());
        } catch (IPartieException e) {
            fail();
        }
    }

    @Test
    void tourSuivantErreurCommenceTest() {
        /* On test la méthode */
        try {
            _partieJ12.tourSuivant();
            fail();
        } catch (IPartieException e) {
            assertEquals(0, _partieJ12.get_nbTour());
        }
    }

    @Test
    void tourSuivantErreurTermineTest() {
        /* On initialise la partie J12 */
        _partieJ12.set_commence(true);
        _partieJ12.set_termine(true);

        /* Test de la méthode */
        try {
            _partieJ12.tourSuivant();
            fail();
        } catch (IPartieException e) {
            assertEquals(0, _partieJ12.get_nbTour());
        }
    }

    @Test
    void tourSuivantErreurJoueurNullTest() {
        /* Test sur partie vide */
        _partieVide.set_commence(true);
        _partieVide.set_termine(true);

        /* Test de la méthode */
        try {
            _partieVide.tourSuivant();
            fail();
        } catch (IPartieException e) {
            assertEquals(0, _partieVide.get_nbTour());
        }
    }

    @Test
    void getJoueurCourantTest() {
        /* Initialisation de Partie J12 */
        _partieJ12.set_commence(true);

        /* Vérification que le joueur courant est le joueur 1 */
        try {
            assertEquals(_partieJ12.get_joueur1(), _partieJ12.getJoueurCourant());
        } catch (IPartieException e) {
            fail();
        }

        /* On passe le tour */
        try {
            _partieJ12.tourSuivant();
        } catch (IPartieException e) {
            fail();
        }

        /* Vérification que le joueur courant est le joueur 1 */
        try {
            assertEquals(_partieJ12.get_joueur2(), _partieJ12.getJoueurCourant());
        } catch (IPartieException e) {
            fail();
        }
    }

    @Test
    void getJoueurCourantErreurJoueurNullTest() {
        /* Test de la méthode avec Partie Vide */
        try {
            _partieVide.getJoueurCourant();
            fail();
        } catch (IPartieException e) {
            assertNull(_partieVide.get_joueur1());
        }

        /* Test de la méthode avec Partie J1 */
        try {
            _partieJ1.getJoueurCourant();
            fail();
        } catch (IPartieException e) {
            assertNull(_partieJ1.get_joueur2());
        }
    }

    @Test
    void ajouterJoueurTest() {
        /* Initialisation avec Partie Vide */
        /* Test de la méthode */
        // Ajout du joueur 1
        try {
            _partieVide.ajouterJoueur("Joueur 1");
            assertNotNull(_partieVide.get_joueur1());
            assertNull(_partieVide.get_joueur2());
        } catch (IPartieException e) {
            fail();
        }

        // Ajout du joueur 2
        try {
            _partieVide.ajouterJoueur("Joueur 2");
            assertNotNull(_partieVide.get_joueur2());
        } catch (IPartieException e) {
            fail();
        }

        assertEquals("Joueur 1", _partieVide.get_joueur1().get_pseudo());
        assertEquals("Joueur 2", _partieVide.get_joueur2().get_pseudo());
    }

    @Test
    void ajouterJoueurErreurJoueurNonNullTest() {
        /* Initialisation avec Partie J12 */
        /* Test de la méthode */
        try {
            _partieJ12.ajouterJoueur("Joueur 3");
            fail();
        } catch (IPartieException e) {
            assertEquals("Joueur 1", _partieJ12.get_joueur1().get_pseudo());
            assertEquals("Joueur 2", _partieJ12.get_joueur2().get_pseudo());
        }
    }

    @Test
    void finTest() {
        /* Initialisation avec Partie J12 */
        /* joueur 1 a gagné */
        _partieJ12.set_commence(true);
        _partieJ12.set_aGagner1(true);

        /* Test de la méthode */
        try {
            _partieJ12.fin();
            assertTrue(_partieJ12.is_termine());
        } catch (IPartieException e) {
            fail();
        }

        /* joueur 2 a gagné */
        _partieJ12.set_termine(false);
        _partieJ12.set_aGagner1(false);
        _partieJ12.set_aGagner2(true);

        /* Test de la méthode */
        try {
            _partieJ12.fin();
            assertTrue(_partieJ12.is_termine());
        } catch (IPartieException e) {
            fail();
        }

    }

    @Test
    void finErreurCommenceTest() {
        /* Initialisation de Partie J12 */
        /* Test de la méthode */
        try {
            _partieJ12.fin();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partieJ12.is_termine());
        }
    }

    @Test
    void finErreurTermineTest() {
        /* Initialisation de Partie J12 */
        _partieJ12.set_commence(true);
        _partieJ12.set_termine(true);

        /* Test de la méthode */
        try {
            _partieJ12.fin();
            fail();
        } catch (IPartieException e) {
            assertTrue(_partieJ12.is_termine());
        }
    }

    @Test
    void finErreurGagneTest() {
        /* Initialisation de Partie J12 */
        _partieJ12.set_commence(true);

        /* Test de la méthode */
        /* Test de la méthode */
        try {
            _partieJ12.fin();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partieJ12.is_termine());
        }
    }
}