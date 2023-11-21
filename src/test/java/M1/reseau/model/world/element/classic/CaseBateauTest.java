package M1.reseau.model.world.element.classic;

import M1.reseau.model.world.element.ICase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseBateauTest {

    private CaseBateau _case1;
    private CaseBateau _case2;
    private CaseBateau _case3;

    @BeforeEach
    void setUp() {
        this._case1 = new CaseBateau(1, 2);
        this._case2 = new CaseBateau(2, 1);
        this._case3 = new CaseBateau(1, 2);
    }

    @Test
    void testConstructeur() {
        ICase _case = new CaseBateau(1, 2);

        /* Verification de l'existence de l'objet */
        assertNotNull(_case);

        /* Verificiation de l'instanciation de l'objet*/
        assertInstanceOf(CaseBateau.class, _case);

        /* Verification des attributs de l'objet */
        assertEquals(1, _case.get_x());
        assertEquals(2, _case.get_y());

        /* Vérification méthode abstraite */
        assertFalse(_case.isPlacable());
        assertTrue(_case.isTirable());
    }

    @Test
    void testEquals() {
        /* Verification que case 1 et 3 ne sont pas null */
        assertNotNull(this._case1);
        assertNotNull(this._case3);

        /* Verification de la correspondance des coordonnees */
        assertEquals(1, this._case1.get_x());
        assertEquals(1, this._case3.get_x());

        assertEquals(2, this._case1.get_y());
        assertEquals(2, this._case3.get_y());

        /* Verification de l'override de == */
        assertTrue(this._case1.equals(this._case3));
    }

    @Test
    void testNotEquals() {
        /* Verification que case 1 et 3 ne sont pas null */
        assertNotNull(this._case1);
        assertNotNull(this._case2);

        /* Verification de la correspondance des coordonnees */
        assertEquals(1, this._case1.get_x());
        assertNotEquals(1, this._case2.get_x());

        assertEquals(2, this._case1.get_y());
        assertNotEquals(2, this._case2.get_y());

        /* Verification de l'override de == */
        assertFalse(this._case1.equals(this._case2));
    }
}