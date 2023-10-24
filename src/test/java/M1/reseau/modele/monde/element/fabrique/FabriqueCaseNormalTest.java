package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseNormal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCaseNormalTest {

    private FabriqueCaseNormal _fabrique;

    @BeforeEach
    void setUp() {
        this._fabrique = new FabriqueCaseNormal();
    }

    @Test
    void testcreerCase() {
        ICase _case = this._fabrique.creerCase(1, 2);
        assertInstanceOf(CaseNormal.class, _case);
        assertEquals(1, _case.get_x());
        assertEquals(2, _case.get_y());
        assertTrue(_case.isPlacable());
    }
}