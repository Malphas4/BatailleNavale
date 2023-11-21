package M1.reseau.model.world.element.state;

import M1.reseau.model.world.element.ICase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseToucheTest {

    private CaseTouche _case1;
    private CaseTouche _case2;
    private CaseTouche _case3;

    @BeforeEach
    void setUp() {
        this._case1 = new CaseTouche(1, 2);
        this._case2 = new CaseTouche(2, 1);
        this._case3 = new CaseTouche(1, 2);
    }

    @Test
    void testConstructeur() {
        ICase _case = new CaseTouche(1, 2);

        /* Verification de l'existence de l'objet */
        assertNotNull(_case);

        /* Verificiation de l'instanciation de l'objet*/
        assertInstanceOf(CaseTouche.class, _case);

        /* Verification des attributs de l'objet */
        assertEquals(1, _case.get_x());
        assertEquals(2, _case.get_y());

        /* Vérification méthode abstraite */
        assertFalse(_case.isPlacable());
        assertFalse(_case.isTirable());
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
        assertEquals(this._case1, this._case3);
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
        assertNotEquals(this._case1, this._case2);
    }

}