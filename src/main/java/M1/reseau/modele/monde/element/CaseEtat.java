package M1.reseau.modele.monde.element;

public abstract class CaseEtat extends Case {

    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     *
     * @param _x
     * @param _y
     */
    protected CaseEtat(int _x, int _y) {
        super(_x, _y);
    }

    /**
     * Les cases états sont nécessairement implacable car on définit la placabilite d'une case à l'initialisation
     * @return
     */
    @Override
    public boolean isPlacable() {
        return false;
    }

}
