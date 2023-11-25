package M1.reseau.client.cor;

import M1.reseau.serveur.serveur.game.SalonThread;
import M1.reseau.utilities.InformationsUtilisateur;

import java.io.IOException;

@Deprecated
//A implémenter dans ControleurGrille pour traiter reponse masi peut faire sans
public class ExpertChatLocal extends  Expert{


    /**
     * Receive : chat;[salon id];[joueur];[message]
     * @param requete
     */
    public void traiter(String requete){
        String[] code= requete.split(";");
        if(code[0].equals("chatsalon") && code[1].equals((InformationsUtilisateur.getInstance().get_salon()))){
            if(code[2].equals(InformationsUtilisateur.getInstance().get_pseudo()))
                //TODO InformationsUtilisateur.getInstance.set_tour(true);
            System.out.println("update crhono");
            InformationsUtilisateur.getInstance().set_tmps(Integer.parseInt(code[3]));
        }
        else
            System.out.println("pas update chrono");

        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }
}
