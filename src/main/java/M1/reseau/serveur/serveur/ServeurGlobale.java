package M1.reseau.serveur.serveur;

import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.serveur.Threads.Commandes;
import M1.reseau.serveur.serveur.Threads.ThreadUDP;
import M1.reseau.serveur.serveur.Threads.ThreadsTCP;
import M1.reseau.serveur.serveur.game.JoueurHandler;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class ServeurGlobale {
    //GAmesServices


    /**
     * variables
     * **/
    //nb de clienst connectées
    int _nbClients=0;
    int _nbSalons=0;
    //vecteur des salons du serveur
    private static Vector<SalonThread> _tabSalons = new Vector();
    //vecteurs des différents clients pour chat global par exemple
    private Vector<JoueurHandler> _tabClients = new Vector();
    public static ServeurGlobale sv;


    /**
     * main
     * **/
    // Methode : la première méthode exécutée, elle attend les connexions
    public static void main(String args[]) throws SocketException, UnknownHostException {
        // instance de la classe principale
         sv = new ServeurGlobale();

        Thread  tTCP = null;

        try {
            tTCP = new ThreadsTCP();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tTCP.start();
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

    synchronized public Vector<SalonThread> get_tabSalons() {
        return _tabSalons;
    }

    synchronized public Vector<JoueurHandler> get_tabClients() {
        return _tabClients;
    }

    synchronized public int addClient(JoueurHandler t)
    {
        // un client en plus connecté
        _nbClients++;
        //  ajoute le nouveau flux de sortie au tableau
        _tabClients.addElement(t);
        return _tabClients.size()-1; // on retourne le numéro du client ajouté (size-1)
    }



    synchronized public int addSalon(SalonThread t)
    {
        // un client en plus connecté
        _nbSalons++;
        //  ajoute le nouveau flux de sortie au tableau
        _tabSalons.add(t) ;
        return _tabSalons.size()-1; // on retourne le numéro du client ajouté (size-1)
    }


    synchronized public JoueurHandler get_1Client(String s) {
        JoueurHandler tempJoueur = null;
        for (JoueurHandler iterJoueur:_tabClients
             ) {
            if(iterJoueur.getName().equals(s))tempJoueur=iterJoueur;
            break ;
        }
        System.out.println("Joueur non existant");
        return tempJoueur;
    }

    public static void set_tabSalons(Vector<SalonThread> _tabSalons) {
        ServeurGlobale._tabSalons = _tabSalons;
    }

    synchronized public SalonThread get_salon(int id) {
        SalonThread tmp = _tabSalons.get(id);
        return tmp;
    }

   synchronized public int _tabClientsTaille() {
        return _tabClients.size();
    }

    synchronized public void sendAll(String message)
    {
        PrintWriter out; // déclaration d'une variable permettant l'envoi de texte vers le client
        for (int i = 0; i < sv._tabClientsTaille(); i++) // parcours de la table des connectés
        {
            try {
                _tabClients.elementAt(i).message(message); // extraction de l'élément courant (type PrintWriter)
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /*if (out != null) // sécurité, l'élément ne doit pas être vide
            {

            }*/
        }
    }

    //** Methode : détruit le client no i **
   synchronized public void delClient(int i)
    {
        _nbClients--; // un client en moins ! snif
        if (_tabClients.elementAt(i) != null) // l'élément existe ...
        {
            _tabClients.removeElementAt(i); // ... on le supprime
       }
   }

}
