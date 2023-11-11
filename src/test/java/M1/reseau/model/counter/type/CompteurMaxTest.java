package M1.reseau.model.counter.type;

import M1.reseau.model.exception.ICompteurException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteurMaxTest {

    private CompteurMax _compteur1;
    private CompteurMax _compteur2;

    @BeforeEach
    void setUp() {
        _compteur1 = new CompteurMax();
        _compteur2 = new CompteurMax(10, 30);
    }

    @Test
    void constructeurVideTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur1);

        /* Vérification des attributs  */
        assertEquals(1024, _compteur1.get_max());
    }

    @Test
    void constructeurTest(){
        /* Vérification que compteur n'est pas null */
        assertNotNull(_compteur2);

        /* Vérification des attributs */
        assertEquals(30, _compteur2.get_max());
    }

    @Test
    void set_maxTest() {
        /* On set le compteur à 20 */
        _compteur1.set_max(20);

        /* On vérifie le nouveau compteur */
        assertEquals(20, _compteur1.get_max());
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
    void incrementeErrorTest() {
        try {
            _compteur2.incremente(30);
            fail();
        } catch (ICompteurException e) {
            assertNotEquals(40, _compteur1.get_compteur());
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