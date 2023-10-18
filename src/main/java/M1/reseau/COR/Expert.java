package M1.reseau.COR;

public class Expert {


    public M1.reseau.COR.Expert _suivant = null;
    public void  Suivant(M1.reseau.COR.Expert sv){
        _suivant = sv;
    }

    public void traiter(String requete){
        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }
}


