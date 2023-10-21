package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.Case;
import M1.reseau.modele.monde.element.type.CaseNormal;
import M1.reseau.modele.monde.element.type.CaseRocher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCaseRocherTest {

    private Case _case;

    @BeforeEach
    void setUp() {
        this._case = (CaseRocher) (new FabriqueCaseRocher()).creerCase(1, 1);
    }

    @Test
    void creerCase() {
        CaseRocher rocher = new CaseRocher(1, 1);
        assertInstanceOf(CaseRocher.class, this._case);
        assertEquals(1, this._case.get_x());
        assertEquals(1, this._case.get_y());
    }
}