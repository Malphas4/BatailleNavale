package M1.reseau.client.cor;

/**
 * Liste des codes hexa dans un fichier dans un pakcage ressources
 * UDP est seulement utilisé pour l'identification et l'attribution de l'identifiant
 * TCP reprend la main après
 */
@Deprecated
//Pas utilisé, connexion et salons gérés par UDp dans le thread
//chrono;pseudo/idSalon/temps en int a afficher
public class expertIdentification   extends Expert {
    public void traiter(String requete){
        String code= requete.split(":")[0];
        if(code.equals("01")){
            System.out.println("Identification réussie");
        }
        else
            System.out.println("Identification échouée");

        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }
    public boolean valider(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equals("chrono");
    }
}
