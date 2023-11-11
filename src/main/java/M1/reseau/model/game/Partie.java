package M1.reseau.model.game;

import M1.reseau.model.world.grid.builder.FabriqueGrille;

import java.util.UUID;

public abstract class Partie implements IPartie {

    /***************************************
     * Déclaration des variables
     ***************************************/
    private UUID _uuid = UUID.randomUUID();
    private int _nbTour;

    /**
     * Etat de la partie
     * Plus tard peut être remplacer par un DP State
     */
    private boolean _commence = false;
    private boolean _pause = false;
    private boolean _termine = false;

    /**
     * Définition de la grille
     */
    private FabriqueGrille _fabriqueGrille;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    public Partie(int _longueur, int _largeur) {
        set_nbTour(0);
        set_fabriqueGrille(new FabriqueGrille(_longueur, _largeur));
    }


    /***************************************
     * Déclaration des getters et setters
     ***************************************/
    /**
     *
     * @return
     */
    public UUID get_uuid() {
        return _uuid;
    }

    /**
     *
     * @param _uuid
     */
    public void set_uuid(UUID _uuid) {
        if (_uuid == null) throw new IllegalArgumentException("Partie : Le uuid ne peut pas être null.");
        this._uuid = _uuid;
    }

    public int get_nbTour() {
        return _nbTour;
    }

    /**
     *
     * @param _nbTour
     */
    public void set_nbTour(int _nbTour) {
        if (_nbTour < 0) throw new IllegalArgumentException("Partie : Le nombre de tour doit être supérieur ou égal à 0.");
        this._nbTour = _nbTour;
    }

    /**
     *
     * @return
     */
    public boolean is_commence() {
        return _commence;
    }

    /**
     *
     * @param _commence
     */
    public void set_commence(boolean _commence) {
        this._commence = _commence;
    }

    /**
     *
     * @return
     */
    public boolean is_pause() {
        return _pause;
    }

    /**
     *
     * @param _pause
     */
    public void set_pause(boolean _pause) {
        this._pause = _pause;
    }

    /**
     *
     * @return
     */
    public boolean is_termine() {
        return _termine;
    }

    /**
     *
     * @param _termine
     */
    public void set_termine(boolean _termine) {
        this._termine = _termine;
    }

    /**
     *
     * @return
     */
    public FabriqueGrille get_fabriqueGrille() {
        return _fabriqueGrille;
    }

    /**
     *
     * @param _fabriqueGrille
     */
    public void set_fabriqueGrille(FabriqueGrille _fabriqueGrille) {
        this._fabriqueGrille = _fabriqueGrille;
    }

}
