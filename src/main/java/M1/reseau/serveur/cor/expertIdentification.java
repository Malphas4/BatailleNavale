package M1.reseau.serveur.cor;

/**
 * Liste des codes hexa dans un fichier dans un pakcage ressources
 * UDP est seulement utilisé pour l'identification et l'attribution de l'identifiant
 * TCP reprend la main après
 */
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
}
