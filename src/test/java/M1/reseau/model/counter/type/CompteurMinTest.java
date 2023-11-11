package M1.reseau.model.counter.type;

import M1.reseau.model.counter.classic.CompteurMin;
import M1.reseau.model.exception.ICompteurException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteurMinTest {

    private CompteurMin _compteur1;
    private CompteurMin _compteur2;

    @BeforeEach
    void setUp() {
        _compteur1 = new CompteurMin();
        _compteur2 = new CompteurMin(10, -10);
    }

    @Test
    void constructeurVideTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur1);

        /* Vérification des attributs  */
        assertEquals(0, _compteur1.get_min());
    }

    @Test
    void constructeurTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur2);

        /* Vérification des attributs */
        assertEquals(-10, _compteur2.get_min());
    }

    @Test
    void set_minTest() {
        /* On set le compteur à 20 */
        _compteur1.set_min(20);

        /* On vérifie le nouveau compteur */
        assertEquals(20, _compteur1.get_min());
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
            _compteur2.decremente(2);
            assertEquals(8, _compteur2.get_compteur());
        } catch (ICompteurException e) {
            fail();
        }
    }

    @Test
    void decrementeErrorTest() {
        try {
            _compteur1.decremente(2);
            fail();
        } catch (ICompteurException e) {
            assertEquals(0, _compteur1.get_compteur());
        }
    }
}