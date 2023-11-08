package M1.reseau.modele.bateau.type;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.Case;
import M1.reseau.modele.monde.element.type.CaseNormal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BateauLigneTest {

    private BateauLigne _bateauVide;
    private BateauLigne _bateauPlein;

    @BeforeEach
    void setUp() {
        _bateauVide = new BateauLigne();
        _bateauPlein = new BateauLigne(1);
    }

    @Test
    void constructeurVideTest() {
        /* Vérification que le bateau n'est pas null */
        assertNotNull(_bateauVide);

        /* Vérification que les attributs ne sont pas null */
        assertNotNull(_bateauVide.get_uuid());
        assertNotNull(_bateauVide.get_listeCase());

        /* Vérification de la taille et des valeurs des attributs */
        assertEquals(0, _bateauVide.get_listeCase().size());
        assertEquals(1, _bateauVide.get_maxCase());
    }

    @Test
    void constructeurTest() {
        /* Vérification que le bateau n'est pas null */
        assertNotNull(_bateauPlein);

        /* Vérification que les attributs ne sont pas null */
        assertNotNull(_bateauPlein.get_uuid());
        assertNotNull(_bateauPlein.get_listeCase());

        /* Vérification de la taille et des valeurs des attributs */
        assertEquals(0, _bateauPlein.get_listeCase().size());
        assertEquals(1, _bateauPlein.get_maxCase());
    }

    @Test
    void set_maxCaseTest() {
        /* On test set_maxCase */
        _bateauVide.set_maxCase(10);

        /* On vérifie l'attribut */
        assertEquals(10, _bateauVide.get_maxCase());
    }

    @Test
    void set_maxCaseErreurInferieurTest() {
        try{
            _bateauVide.set_maxCase(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(1, _bateauVide.get_maxCase());
        }
    }

    @Test
    void ajouterCaseTest() {
        /* Création d'une case non vide */
        CaseNormal _case = new CaseNormal(1, 1);

        /* Vérification de l'ajout de case */
        try {
            _bateauPlein.add_case(_case);
            assertEquals(1, _bateauPlein.get_listeCase().size());
            assertTrue(_bateauPlein.get_listeCase().contains(_case));
        } catch (IBateauException e) {
            fail();
        }
    }

    @Test
    void ajouterCaseErreurNullTest() {
        /* Création d'une case non vide */
        CaseNormal _case = null;

        /* Vérification de l'ajout de case */
        try {
            _bateauPlein.add_case(_case);
            fail();
        } catch (IllegalArgumentException | IBateauException e) {
            assertEquals(0, _bateauPlein.get_listeCase().size());
            assertFalse(_bateauPlein.get_listeCase().contains(_case));
        }
    }

    @Test
    void ajouterCaseErreurMaxSizeTest() {
        /* Création de 2 cases distinctes */
        CaseNormal _case1 = new CaseNormal(1, 1);
        CaseNormal _case2 = new CaseNormal(2, 2);

        /* Vérification de l'ajout de case */
        try {
            _bateauPlein.add_case(_case1);
            _bateauPlein.add_case(_case2);
            fail();
        } catch (IBateauException e) {
            assertEquals(1, _bateauPlein.get_listeCase().size());
            assertTrue(_bateauPlein.get_listeCase().contains(_case1));
            assertFalse(_bateauPlein.get_listeCase().contains(_case2));
        }
    }

    @Test
    void ajouterCaseErreurDansLaListeTest() {
        /* Création de 2 cases similaires */
        CaseNormal _case1 = new CaseNormal(1, 1);
        CaseNormal _case2 = new CaseNormal(1, 1);

        /* Vérification de l'ajout de case */
        try {
            _bateauPlein.add_case(_case1);
            _bateauPlein.add_case(_case2);
            fail();
        } catch (IBateauException e) {
            assertEquals(1, _bateauPlein.get_listeCase().size());
            assertTrue(_bateauPlein.get_listeCase().contains(_case1));
        }
    }
}