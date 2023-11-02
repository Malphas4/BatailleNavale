package M1.reseau.modele.monde.grille;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.monde.element.ICase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grille implements IGrille {
    private int _longueur;
    private int _largeur;

    private List<ICase> _listeCase;
    private List<IBateau> _listeBateau;

    /**
     *
     */
    public Grille(int _longueur, int _largeur) {
        set_longueur(_longueur);
        set_largeur(_largeur);
        set_listeCase(new ArrayList<>());
        set_listBateau(new ArrayList<>());
    }

    /**
     *
     * @return
     */
    public int get_longueur() {
        return _longueur;
    }

    /**
     *
     * @param _longueur
     */
    public void set_longueur(int _longueur) {
        if (_longueur <= 0) throw new IllegalArgumentException("Grille : La longueur doit être supérieur à 0.");
        this._longueur = _longueur;
    }

    /**
     *
     * @return
     */
    public int get_largeur() {
        return _largeur;
    }

    /**
     *
     * @param _largeur
     */
    public void set_largeur(int _largeur) {
        if (_largeur <= 0) throw new IllegalArgumentException("Grille : La largeur doit être supérieur à 0");
        this._largeur = _largeur;
    }

    /**
     *
     * @return
     */
    public List<ICase> get_listeCase() {
        return _listeCase;
    }

    /**
     *
     * @param _cases
     */
    public void set_listeCase(List<ICase> _cases) {
        this._listeCase = _cases;
    }

    /**
     *
     * @return
     */
    public List<IBateau> get_listeBateau() {
        return _listeBateau;
    }

    /**
     *
     * @param _bateaux
     */
    public void set_listBateau(List<IBateau> _bateaux) {
        this._listeBateau = _bateaux;
    }

    /**
     *
     * @param _case
     * @return
     */
    @Override
    public boolean isDansLaGrille(ICase _case) {
        if (_case == null) throw new IllegalArgumentException("Grille : La case ne peut pas être null.");
        return get_listeCase().contains(_case);
    }

    /**
     *
     * @param _bateau
     * @return
     */
    @Override
    public boolean isDansLaGrille(IBateau _bateau) {
        if (_bateau == null) throw new IllegalArgumentException("Grille : Le bateau ne peut pas être null.");
        return get_listeBateau().contains(_bateau);
    }

    /**
     *
     * @param _case
     * @throws IGrilleException
     */
    @Override
    public void ajouterCase(ICase _case) throws IGrilleException {
        /* Verification case n'est pas null */
        if (_case == null) throw new IllegalArgumentException("Grille : La case a ajouté ne peut pas être null.");

        /* Verification case est dans la grille */
        if (_case.get_x() <= 0 && _case.get_x() > get_longueur()) throw new IGrilleException("Grille : La coordonnée x de la case ne sont pas dans la grille");
        if (_case.get_y() <= 0 && _case.get_y() > get_largeur()) throw new IGrilleException("Grille : La coordonnée x de la case ne sont pas dans la grille");

        /* On ajoute la case dans la liste de la grille */
        get_listeCase().add(_case);
    }

    /**
     *
     * @param _case
     * @throws IGrilleException
     */
    @Override
    public void supprimerCase(ICase _case) throws IGrilleException {
        /* Verification case n'est pas null */
        if (_case == null) throw new IllegalArgumentException("Grille : La case a ajouté ne peut pas être null.");

        /* Verification case est dans la liste de la grille */
        if (!get_listeCase().contains(_case)) throw new IGrilleException("Grille : La case n'est pas dans la liste de la grille.");

        /* On supprime la case dans la liste de la grille */
        get_listeCase().remove(_case);
    }

    /**
     *
     * @param _bateau
     * @throws IGrilleException
     */
    @Override
    public void ajouterBateau(IBateau _bateau) throws IGrilleException {
        /* Verification bateau n'est pas null */
        if (_bateau == null) throw new IllegalArgumentException("Grille : Le bateau a ajouté ne peut pas être null.");

        /* Verication bateau est sur la grille */
        if (!_bateau.isSurLaGrille(this)) throw new IGrilleException("Grille : Le bateau doit être sur la grille.");

        /* On ajoute le bateau dans la liste de la grille */
        this.get_listeBateau().add(_bateau);
    }

    /**
     *
     * @param _bateau
     * @throws IGrilleException
     */
    @Override
    public void supprimerBateau(IBateau _bateau) throws IGrilleException {
        /* Verification bateau n'est pas null */
        if (_bateau == null) throw new IllegalArgumentException("Grille : La case a ajouté ne peut pas être null.");

        /* Verification bateau est dans la liste de la grille */
        if (!this.get_listeCase().contains(_bateau)) throw new IGrilleException("Grille : Le bateau n'est pas dans la liste de la grille.");

        /* On supprime la case dans la liste de la grille */
        this.get_listeCase().remove(_bateau);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grille grille = (Grille) o;
        return get_longueur() == grille.get_longueur() && get_largeur() == grille.get_largeur() && Objects.equals(get_listeCase(), grille.get_listeCase()) && Objects.equals(get_listeBateau(), grille.get_listeBateau());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_longueur(), get_largeur(), get_listeCase(), get_listeBateau());
    }
}
