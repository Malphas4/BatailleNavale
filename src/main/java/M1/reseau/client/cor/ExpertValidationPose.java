package M1.reseau.client.cor;

public class ExpertValidationPose  extends Expert {
    public void traiter(String requete){
        String code= requete.split(":")[0];
        if(code.equals("pose")){
            System.out.println("pose réussie");
        }
        else
            System.out.println("pose échouée");
        {
            if (_suivant != null) {
                _suivant.traiter(requete);
            }
        }
    }
    public boolean valider(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equals("pose");
    }
}