package M1.reseau.client.cor;

import M1.reseau.utilities.InformationsUtilisateur;

public class expertChrono   extends Expert {
    public void traiter(String requete){
        String[] code= requete.split(";");
        if(code[0].equals("chrono") && code[1].equals(InformationsUtilisateur.getInstance().get_pseudo())){

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

