package M1.reseau.model.game.distance;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.grid.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartieClientTest {

    private PartieClient _partieVide;
    private PartieClient _partiePleine;

    @BeforeEach
    void setUp() {
        /* Initialisation de la partie vide */
        _partieVide = new PartieClient(8, 8);

        /*Initialisation de la partie pleine */
        _partiePleine = new PartieClient(8, 8);
        try {
            _partiePleine.ajouterJoueur("Joueur 1");
        } catch (IPartieException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void constructeurTest() {
        /* Vérification que partie n'est pas null */
        assertNotNull(_partieVide);

        /* Vérification des attributs */
        assertNull(_partieVide.get_joueur());
        assertFalse(_partieVide.is_aGagner());
        assertFalse(_partieVide.is_tourJoueur());
    }

    @Test
    void set_joueurTest() {
        /* Initialisation avec Partie Vide */

        /* Test de la méthode */
        try {
            JoueurNormal _joueur = new JoueurNormal("Joueur 1",
                                                    (Grille) _partieVide.get_fabriqueGrille().creerGrille(),
                                                    (Grille) _partieVide.get_fabriqueGrille().creerGrille());
            _partieVide.set_joueur(_joueur);
            assertNotNull(_partieVide.get_joueur());
            assertEquals("Joueur 1", _partieVide.get_joueur().get_pseudo());
        } catch (IJoueurException e) {
            fail();
        }
    }

    @Test
    void set_joueurErreurNullTest() {
        /* Initialisation avec Partie Vide */
        JoueurNormal _joueur = null;

        /* Test de la méthode */
        try {
            _partieVide.set_joueur(_joueur);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(_partieVide.get_joueur());
        }
    }

    @Test
    void commencerTest() {
        /* Initialisation avec partie pleine */

        /* On test la méthode */
        try {
            _partiePleine.commencer();
            assertTrue(_partiePleine.is_commence());
        } catch (IPartieException e) {
            fail();
        }
    }

    @Test
    void commencerErreurJoueurNullTest() {
        /* Initialisation avec partie vide */

        /* On test la méthode */
        try {
            _partieVide.commencer();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partieVide.is_commence());
        }
    }

    @Test
    void commencerErreurCommenceTest() {
        /* Initialisation avec partie pleine */
        _partiePleine.set_commence(true);

        /* On test la méthode */
        try {
            _partiePleine.commencer();
            fail();
        } catch (IPartieException e) {
            assertTrue(_partiePleine.is_commence());
        }
    }

    @Test
    void commencerErreurTermineTest() {
        /* Initialisation avec partie pleine */
        _partiePleine.set_termine(true);

        /* On test la méthode */
        try {
            _partiePleine.commencer();
            fail();
        } catch (IPartieException e) {
            assertFalse(_partiePleine.is_commence());
        }
    }

    @Test
    void tourSuivantTest() {
        /* Initialisation avec partie pleine */
        _partiePleine.set_commence(true);

        /* On test la méthode */
        try {
            _partiePleine.tourSuivant();
            assertEquals(1, _partiePleine.get_nbTour());
            assertTrue(_partiePleine.is_tourJoueur());
        } catch (IPartieException e) {
            fail();
        }
    }

    @Test
    void tourSuivantErreurCommenceTest() {
        /* Initialisation avec partie pleine */

        /* On test la méthode */
        try {
            _partiePleine.tourSuivant();
            fail();
        } catch (IPartieException e) {
            assertEquals(0, _partiePleine.get_nbTour());
        }
    }

    @Test
    void tourSuivantErreurTermineTest() {
        /* Initialisation avec partie pleine */
        _partiePleine.set_commence(true);
        _partiePleine.set_termine(true);

        /* Test de la méthode */
        try {
            _partiePleine.tourSuivant();
            fail();
        } catch (IPartieException e) {
            assertEquals(0, _partiePleine.get_nbTour());
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
        _partiePleine.set_commence(true);
        _partiePleine.set_tourJoueur(true);

        /* Vérification que le joueur courant est le joueur 1 */
        try {
            assertEquals(_partiePleine.get_joueur(), _partiePleine.getJoueurCourant());
        } catch (IPartieException e) {
            fail();
        }

        /* On passe le tour */
        try {
            _partiePleine.tourSuivant();
        } catch (IPartieException e) {
            fail();
        }

        /* Vérification que le joueur courant retourne une erreur */
        JoueurNormal _joueur = null;
        try {
            _joueur = (JoueurNormal) _partiePleine.getJoueurCourant();
            fail();
        } catch (IPartieException e) {
            assertNull(_joueur);
        }
    }

    @Test
    void getJoueurCourantErreurJoueurNullTest() {
        /* Test de la méthode avec Partie Vide */
        try {
            _partieVide.getJoueurCourant();
            fail();
        } catch (IPartieException e) {
            assertNull(_partieVide.get_joueur());
        }
    }

    @Test
    void ajouterJoueur() {

    }

    @Test
    void fin() {

    }
}