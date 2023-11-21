package M1.reseau.serveur.serveur;

import M1.reseau.serveur.serveur.chatGlobal.Commandes;
import M1.reseau.serveur.serveur.chatGlobal.ServeurChatTCP;

import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class ServeurMain {

    /**
     * variables
     * **/

    //vecteur des salons du serveur
    private Vector _tabSalons = new Vector();
    //vecteurs des différents clients pour chat global par exemple
    private Vector _tabClients = new Vector();

    /**
     * main
     * **/
    // Methode : la première méthode exécutée, elle attend les connexions
    public static void main(String args[]) throws SocketException, UnknownHostException {
        // instance de la classe principale
        ServeurMain ServeurMain = new ServeurMain();

        try
        {
            Integer port;
            // si pas d'argument : port 18000 par défaut
            if(args.length<=0) port=new Integer("18000");
            // sinon il s'agit du numéro de port passé en argument
            else port = Integer.valueOf(args[0]);

            new Commandes(ServeurMain); // lance le thread de gestion des commandes
            Thread tUDP= new ServeurUDP(ServeurMain);
            tUDP.start();
            //Lance le thread du serveur UDP en arrière plan
            // new ServeurUDP(ServeurChatTCP);

            ServerSocket _socket = new ServerSocket(port.intValue()); // ouverture d'un socket serveur sur port
            affichageLancement(port);
            /*while (true) // attente en boucle de connexion (bloquant sur ss.accept)
            {
                new Serveurthread(_socket.accept(),main); // un client se connecte, un nouveau thread client est lancé
            }*/
        }
        catch (Exception e) { }
    }

    /**Affichage du prompt serveur initial
     *
     * */
    static private void affichageLancement(Integer port) {
        System.out.println("--------");
        System.out.println("Serveur en ligne");
        System.out.println("--------");
        System.out.println("Utilise le port : " + port.toString());
        System.out.println("--------");
        System.out.println("Quitter : tapez \"quit\"");
        System.out.println("Nombre de connectes : tapez \"total\"");
        System.out.println("Liste des salons : tapez \"ls\"");
        System.out.println("Liste des joueurs : tapez \"lj\"");
        System.out.println("Creation d'un nouveau salon : tapez \"ns\"");
    }


    synchronized public int getNbSalons() {
        return _tabSalons.size();
    }
}
