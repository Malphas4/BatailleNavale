package M1.reseau.client2;

import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.Threads.Commandes;
import M1.reseau.serveur.serveur.Threads.ThreadTCP;
import M1.reseau.serveur.serveur.Threads.ThreadUDP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class mainClient {
    public static void main(String args[]) throws SocketException, UnknownHostException {
        // instance de la classe principale

        sv = new mainClient();

        Thread  tTCP = null;

        try {
            tTCP = new ThreadTCP();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //sv.start();



        //Creation d'un salon de départ
        //Doit être fait dans ThreadSalons normalement
        Salon s1=new Salon();
        //_tabSalons.add(s1);
        try
        {
            Integer port;
            // si pas d'argument : port 18000 par défaut
            if(args.length<=0) port=new Integer("18000");
                // sinon il s'agit du numéro de port passé en argument
            else port = Integer.valueOf(args[0]);
            ThreadUDP tUDP;
            tUDP = new ThreadUDP(sv);
            tUDP.start();
            //Lance le thread du serveur UDP
            // ServeurUDP serveurUDP = new ServeurUDP(ServeurGlobale);
            // ouverture d'un socket serveur sur port afin d'avoir 1 socket
            ServerSocket _socket = new ServerSocket(port);

            new Commandes(ServeurGlobale.sv); // lance le thread de gestion des commandes
            affichageLancement(port);

           /* while (true) // attente en boucle de connexion (bloquant sur ss.accept)
            {

               // new Serveurthread(_socket.accept(),main); // un client se connecte, un nouveau thread client est lancé
            }*/
        }
        catch (Exception e) {e.printStackTrace(); }
        tTCP.start();

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
        // System.out.println("Liste des joueurs : tapez \"lj\"");
        System.out.println("Creation d'un nouveau salon : tapez \"create\"");
    }