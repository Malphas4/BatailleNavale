package M1.reseau.client.cor;

import M1.reseau.utilities.InformationsUtilisateur;

public class ExpertFinPartie extends Expert {


    /**
     * Receive : chat;[salon id];[joueur];[message]
     * @param requete
     */
    public void traiter(String requete){
        String[] code= requete.split(";");
        if(code[0].equals("victoire") && code[1].equals((InformationsUtilisateur.getInstance().get_salon()))){
            if(code[2].equals(InformationsUtilisateur.getInstance().get_pseudo()))
                //TODO InformationsUtilisateur.getInstance.set_tour(true);
                System.out.println("msg fin de partie + vainqueur" +code[2]);
            else System.out.println("msg fin de partie + perdant" +code[2]);
        }
        else
            System.out.println("pas fin de partie");

        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }

}
