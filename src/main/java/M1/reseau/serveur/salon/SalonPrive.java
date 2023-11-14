package M1.reseau.serveur.salon;

public class SalonPrive extends Salon{
    private String  _motDePasse="0ùBx2";

    public  SalonPrive(String nom, String description, int nbMax, String motDePasse){
     super(nom,description,nbMax);
     _motDePasse=motDePasse;
    }

    public SalonPrive(String difficulte, String partiePrivee, String mdp) {
        super(difficulte,partiePrivee);
        _motDePasse=mdp;
    }

    public String getMotDePasse() {
        return _motDePasse;
    }

    public void set_motDePasse(String _motDePasse) {
        this._motDePasse = _motDePasse;
    }
}
