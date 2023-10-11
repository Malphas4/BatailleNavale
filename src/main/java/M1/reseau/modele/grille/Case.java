package M1.reseau.modele.grille;

public class Case {
    enum EStatutCase {
        DEFAUT,
        TOUCHE,
        RATE,
        BATEAU
    }

    private EStatutCase statut = EStatutCase.DEFAUT;

    public Case() {

    }

    public EStatutCase getStatut() {
        return statut;
    }

    public void setStatut(EStatutCase statut) {
        this.statut = statut;
    }

}
