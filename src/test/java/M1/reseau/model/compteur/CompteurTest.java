package M1.reseau.model.compteur;

import M1.reseau.model.exception.ICompteurException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteurTest {

    private Compteur _compteur1;
    private Compteur _compteur2;

    @BeforeEach
    void setUp() {
        _compteur1 = new Compteur();
        _compteur2 = new Compteur(10);
    }

    @Test
    void constructeurVideTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur1);

        /* Vérification des attributs */
        assertEquals(0, _compteur1.get_compteur());
    }

    @Test
    void constructeurTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur2);

        /* Vérification des attributs */
        assertEquals(10, _compteur2.get_compteur());
    }

    @Test
    void set_compteurTest() {
        /* On set le compteur à 20 */
        _compteur1.set_compteur(20);

        /* On vérifie le nouveau compteur */
        assertEquals(20, _compteur1.get_compteur());
    }

    @Test
    void incrementeTest() {
        try {
            _compteur1.incremente(2);
            assertEquals(2, _compteur1.get_compteur());
        } catch (ICompteurException e) {
            fail();
        }
    }

    @Test
    void decrementeTest() {
        try {
            _compteur1.decremente(2);
            assertEquals(-2, _compteur1.get_compteur());
        } catch (ICompteurException e) {
            fail();
        }
    }
}