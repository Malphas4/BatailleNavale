package M1.reseau.client.cor;

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



    /**
     * Subclasses will implement this method with concrete checks.
     */
   // public abstract boolean check(String email, String password);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
   /* protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }*/
//}


