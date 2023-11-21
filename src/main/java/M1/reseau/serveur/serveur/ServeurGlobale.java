package M1.reseau.serveur.serveur;

import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.serveur.Threads.ThreadSalons;
import M1.reseau.serveur.serveur.Threads.ThreadUDP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class ServeurGlobale {


    /**
     * variables
     * **/
    //nb de clienqst connectées
    int _nbClients=0;
    int _nbSalons=0;
    //vecteur des salons du serveur
    private static Vector<ThreadSalons> _tabSalons = new Vector();
    //vecteurs des différents clients pour chat global par exemple
    private Vector<ThreadUDP> _tabClients = new Vector();
    public static ServeurGlobale sv;

    /**
     * main
     * **/
    // Methode : la première méthode exécutée, elle attend les connexions
    public static void main(String args[]) throws SocketException, UnknownHostException {
        // instance de la classe principale
         sv = new ServeurGlobale();

        Thread tcpServeur = new ThreadSalons();
        tcpServeur.start();



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
            ServerSocket _socket = new ServerSocket(port.intValue());
            affichageLancement(port);

            while (true) // attente en boucle de connexion (bloquant sur ss.accept)
            {

               // new Serveurthread(_socket.accept(),main); // un client se connecte, un nouveau thread client est lancé
            }
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
    synchronized public int getNbXlients() {
        return _nbClients;
    }

    synchronized public static Vector get_tabSalons() {
        return _tabSalons;
    }

    synchronized public Vector get_tabClients() {
        return _tabClients;
    }
    synchronized public int addClient(ThreadUDP t)
    {
        // un client en plus connecté
        _nbClients++;
        //  ajoute le nouveau flux de sortie au tableau
        _tabClients.addElement(t);
        return _tabClients.size()-1; // on retourne le numéro du client ajouté (size-1)
    }
    synchronized public int addSalon(ThreadSalons t)
    {
        // un client en plus connecté
        _nbSalons++;
        //  ajoute le nouveau flux de sortie au tableau
        _tabSalons.addElement(t);
        return _tabSalons.size()-1; // on retourne le numéro du client ajouté (size-1)
    }


}
