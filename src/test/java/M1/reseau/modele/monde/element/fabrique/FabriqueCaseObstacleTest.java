package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.Case;
import M1.reseau.modele.monde.element.type.CaseObstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueCaseObstacleTest {

    private Case _case;

    @BeforeEach
    void setUp() {
        this._case = (CaseObstacle) (new FabriqueCaseRocher()).creerCase(1, 1);
    }

    @Test
    void creerCase() {
        CaseObstacle rocher = new CaseObstacle(1, 1);
        assertInstanceOf(CaseObstacle.class, this._case);
        assertEquals(1, this._case.get_x());
        assertEquals(1, this._case.get_y());
    }
}