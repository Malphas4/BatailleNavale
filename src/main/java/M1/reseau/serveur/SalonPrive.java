package M1.reseau.serveur;

public class SalonPrive extends Salon{
    private String  _motDePasse="0ùBx2";

    public  SalonPrive(String nom, String description, int nbMax, String motDePasse){
     super(nom,description,nbMax);
     _motDePasse=motDePasse;
    }

    public String getMotDePasse() {
        return _motDePasse;
    }

    public void set_motDePasse(String _motDePasse) {
        this._motDePasse = _motDePasse;
    }
}
