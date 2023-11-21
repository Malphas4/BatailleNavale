package M1.reseau.serveur.cor;

public class expertCase extends Expert {
    public void traiter(String requete){
        String code= requete.split(":")[1];
            if(code.equals("01")){
                System.out.println("Case réceptionnée");
                /*
                * refrection ici sur la case transmise
                * ou on split en utilisant une virgule
                * on boucle sur le découpage
                * le premier int est la position x
                * le second int est la position y
                * le troisième int est l' état de la case
                * (vide, case bateau , bateau détruit, rocher , ...
                *
                * */
            }
            else
                System.out.println("Case non touchée");

        if(_suivant != null){
            _suivant.traiter(requete);
        }
    }
}
