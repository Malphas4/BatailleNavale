package M1.reseau.model.game;

import M1.reseau.model.world.grid.builder.FabriqueGrille;

import java.util.UUID;

public abstract class Partie implements IPartie {

    /***************************************
     * Déclaration des variables
     ***************************************/
    private UUID _uuid = UUID.randomUUID(); /* ID unique de la partie*/
    private int _nbTour; /* Nombre de tour sur la partie par défaut à 0 */

    /**
     * Etat de la partie
     * Plus tard peut être remplacer par un DP State
     * Pause pour l'instant ne sera pas implémenté dans les fonctionnalités
     */
    private boolean _commence = false; /* Est-ce que la partie a commencée ? */
    private boolean _pause = false; /* Est-ce que la partie est en pause ? */
    private boolean _termine = false; /* Est-ce que la partie est terminée ? */

    /**
     * Définition de la grille
     */
    private FabriqueGrille _fabriqueGrille; /* Définit le constructeur de grille pour les joueurs */

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    /**
     * Constructeur par défaut de Partie
     * @param _longueur Longueur de la grille
     * @param _largeur Largeur de la grille
     */
    public Partie(int _longueur, int _largeur) {
        set_nbTour(0);
        set_fabriqueGrille(new FabriqueGrille(_longueur, _largeur));
    }


    /***************************************
     * Déclaration des getters et setters
     ***************************************/
    /**
     * Getter du UUID
     * @return
     */
    public UUID get_uuid() {
        return _uuid;
    }

    /**
     * Setter du UUID
     * @param _uuid
     */
    public void set_uuid(UUID _uuid) {
        if (_uuid == null) throw new IllegalArgumentException("Partie : Le uuid ne peut pas être null.");
        this._uuid = _uuid;
    }

    /**
     * Getter du nombre de tour
     * @return Nombre de tour
     */
    public int get_nbTour() {
        return _nbTour;
    }

    /**
     * Setter du nombre de tour
     * @param _nbTour
     */
    public void set_nbTour(int _nbTour) {
        if (_nbTour < 0) throw new IllegalArgumentException("Partie : Le nombre de tour doit être supérieur ou égal à 0.");
        this._nbTour = _nbTour;
    }

    /**
     * Getter du statut de début
     * @return la partie a-t-elle commencée ?
     */
    public boolean is_commence() {
        return _commence;
    }

    /**
     * Setter du statut de début
     * @param _commence
     */
    public void set_commence(boolean _commence) {
        this._commence = _commence;
    }

    /**
     * Getter du statut de pause
     * @return La partie est-elle en pause ?
     */
    public boolean is_pause() {
        return _pause;
    }

    /**
     * Setter du statut de pause
     * @param _pause
     */
    public void set_pause(boolean _pause) {
        this._pause = _pause;
    }

    /**
     * Getter du statut de fin
     * @return La partie est-elle terminée ?
     */
    public boolean is_termine() {
        return _termine;
    }

    /**
     * Setter du statut de fin
     * @param _termine
     */
    public void set_termine(boolean _termine) {
        this._termine = _termine;
    }

    /**
     * Getter de la fabrique de grille
     * @return Une grille fabriquée
     */
    public FabriqueGrille get_fabriqueGrille() {
        return _fabriqueGrille;
    }

    /**
     * Setter de la fabrique de grille
     * @param _fabriqueGrille
     */
    public void set_fabriqueGrille(FabriqueGrille _fabriqueGrille) {
        if (_fabriqueGrille == null) throw new IllegalArgumentException("Partie : La fabrique ne peut pas être null.");
        this._fabriqueGrille = _fabriqueGrille;
    }

    /***************************************
     * Override méthode de Partie
     ***************************************/

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Partie{" +
                "_uuid=" + _uuid +
                ", _nbTour=" + _nbTour +
                ", _commence=" + _commence +
                ", _pause=" + _pause +
                ", _termine=" + _termine +
                ", _fabriqueGrille=" + _fabriqueGrille +
                '}';
    }
}
