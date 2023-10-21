package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseBateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCaseBateauTest {

    private FabriqueCaseBateau _fabrique;

    @BeforeEach
    void setUp() {
        this._fabrique = new FabriqueCaseBateau();
    }

    @Test
    void testcreerCase() {
        ICase _case = this._fabrique.creerCase(1, 2);
        assertInstanceOf(CaseBateau.class, _case);
        assertEquals(1, _case.get_x());
        assertEquals(2, _case.get_y());
        assertFalse(_case.isPlacable());
    }
}