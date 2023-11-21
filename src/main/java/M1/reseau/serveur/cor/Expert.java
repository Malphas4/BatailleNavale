package M1.reseau.serveur.cor;

public class Expert {


    public Expert _suivant = null;
    public void  Suivant(Expert sv){
        _suivant = sv;
    }

    public void traiter(String requete){
        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }
}


