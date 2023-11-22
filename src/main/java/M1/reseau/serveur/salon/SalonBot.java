package M1.reseau.serveur.salon;

import M1.reseau.model.game.distance.PartieClient;

public class SalonBot extends Salon{

    /**
     * variables
     * */
    PartieClient _partie;



    /**
     * Constructeurs
     * */
    public SalonBot(String nom, String description, int nbMax) {
        super(nom, description);
      //  _partie=new PartieClient(this._mode.getLargeur(),this._mode.getHauteur());
    }

    public SalonBot() {
        super();
    }

    public SalonBot(String nom, String _description,int nbjoueur,String difficulte){
       // super(nom, _description, nbjoueur,difficulte);
    }

}
