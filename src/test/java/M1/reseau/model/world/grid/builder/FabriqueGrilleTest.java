package M1.reseau.model.world.grid.builder;

import M1.reseau.model.world.grid.Grille;
import M1.reseau.model.world.grid.IGrille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueGrilleTest {

    private FabriqueGrille _fabrique;

    @BeforeEach
    void setUp() {
        this._fabrique = new FabriqueGrille(4, 8);
    }

    @Test
    void constructeurTest() {
        /* On vérifie que l'objet n'est pas null */
        assertNotNull(this._fabrique);

        /* On vérifie chaque attribue */
        assertEquals(4, this._fabrique.get_longueur());
        assertEquals(8, this._fabrique.get_largeur());
    }

    @Test
    void creerGrilleTest() {
        /* On créer la grille avec la fabrique */
        IGrille _grille = this._fabrique.creerGrille();

        /* On vérifie que l'objet soit non null */
        assertNotNull(_grille);

        /* On vérifie que l'objet soit une grille */
        assertInstanceOf(Grille.class, _grille);

        Grille _grille1 = (Grille) _grille;

        /* On vérifie chaque attribut */
        assertEquals(4, _grille1.get_longueur());
        assertEquals(8, _grille1.get_largeur());

        /* On vérifie les listes sont non null */
        assertNotNull(_grille1.get_listeCase());
        assertNotNull(_grille1.get_listeBateau());

        /* On vérifie que les listes soit de taille 0 */
        assertEquals(4*8, _grille1.get_listeCase().size());
        assertEquals(0, _grille1.get_listeBateau().size());
    }
}