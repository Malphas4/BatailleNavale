package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.type.CaseNormal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCaseNormalTest {

    CaseNormal _case;

    @BeforeEach
    void setUp() {
        this._case = (CaseNormal) (new FabriqueCaseNormal()).creerCase(1, 1);
    }

    @Test
    void creerCaseTest() {
        assertEquals(1, _case.get_x());
        assertEquals(1, _case.get_y());
    }
}