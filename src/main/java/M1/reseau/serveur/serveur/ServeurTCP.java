package M1.reseau.serveur.serveur;

import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.salon.SalonPrive;
import M1.reseau.serveur.singletons.SingletonUDP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * This class implements a TCP/IP java Socket server
 * @author malphas
 *
 */
public class ServeurTCP {

    //static ServerSocket variable
    private static ServerSocket serveur;
    static Scanner input=new Scanner(System.in);
    static boolean _statusServeurUDP=true;

    //liste des salons du serveur

    private static int port = 9876;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //create the socket server object
        serveur = new ServerSocket(port);
        //liste des salons du serveur
        ArrayList<Salon> _listeSalons = new ArrayList<>();

        //variables des boucles de gestion du serveur, TCP/IP, interface console et UDP
        boolean _statusServeur = true;
        boolean _statusConsole = true;


        //instantation du singleton UDP
        try {
            SingletonUDP.getInstance();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }


        Thread threadUDP = new Thread(new Runnable() {
            @Override
            public void run() {
                while (_statusServeurUDP) {
                    try {
                        System.out.printf(SingletonUDP.getInstance().reception());
                        SingletonUDP.getInstance().message("test");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //Thread UDP
        threadUDP.start();

        /* thread avec UDP tournant en arrière plan*/

        while (_statusConsole) {
            //commande console pour gérer le serveur
            System.out.println("commandes !\n" +
                    "ls pour la liste des salons\n" +
                    "lj liste des joueurs\n" +
                    "ns [difficulte] [nom] [mot de passe] creation d'un nouveau salon avec mdp facultatif\n" +
                    "exit pour clore le serveur\n");
            String str1 = input.nextLine();

            if ("exit".equals(str1)) {
                System.out.println("Au revoir, extinction du serveur");
                _statusConsole = false;
                _statusServeur = false;
                _statusServeurUDP = false;


            } else if ("ls".equals(str1)) {
                System.out.print("Informations des salons :\n");
                for (Salon iterSalon : _listeSalons) {
                    if (_listeSalons.isEmpty())
                        System.out.print("  Aucun salon n'est disponible\n");
                    else
                        System.out.print("  " + iterSalon.infos() + "\n");
                }

            } else if ("ns".equals(str1)) {
                System.out.print("creation d'un salon :\n");
                System.out.print("Difficulté du nouveau salon ? \n options possibles \n f pour facile \n d pour difficile \n m pour moyen" + "\n");
                String difficulte = input.nextLine();

                System.out.print("nb joueur de base 2 pas modifiable pour l'instant\n");
                //int _nbJoueur=2;
                System.out.print("mot de passe? laisser vide pour ne pas en attribuer\n");
                String _mdp = input.nextLine();
                if (_mdp.trim().isEmpty()) {
                    _listeSalons.add(new Salon(difficulte, "partie classique"));
                } else {
                    _listeSalons.add(new SalonPrive(difficulte, "partie privée", _mdp));
                }


            } else if ("lj".equals(str1)) {
                System.out.print("liste des joueurs :\n");
                for (Salon iterSalon : _listeSalons) {
                    if (_listeSalons.isEmpty())
                        System.out.print("  Aucun salon n'est disponible\n");
                    //else
                    //  System.out.print("  "+iterSalon.getJoueurPseudo()+"\n");
                }
            } else {
                System.out.print("commande inconnue\n");
            }
            //keep listens indefinitely until receives 'exit' call or program terminates
            //Pas bon, singletonTCP ou thread pour gestion de multiples clients
            //a sortir
            while (_statusServeur) {
                System.out.println("Attente de connexion ...");
                //creating socket and waiting for M1.reseau.client connection
                Socket socket = serveur.accept();
                //read from socket to ObjectInputStream object
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //convert ObjectInputStream object to String
                // String message = "YOLO";
                String message = (String) ois.readObject();
                System.out.println("Message reçu: " + message);
                //create ObjectOutputStream object
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //write object to Socket
                oos.writeObject("Case touchee " + message);
                //close resources
                ois.close();
                oos.close();
                socket.close();
                //terminate the server if M1.reseau.client sends exit request
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Au revoir");
                    _statusServeur = false;
                    SingletonUDP.fermetureSocket();
                }
            }
            System.out.println(" extinction du serveur!!");
            //close the ServerSocket object
            serveur.close();
        }

        /**
         * Thread UDP
         * Theard qui gère les échanges UDP
         * utilisé pour la connection et l'inscription des joueurs
         * Peut etre utilisé pour le chat général
         */

        //Thread t = new Thread(new Runnable() {

        /*@Override
        public void run() {
            //instantation du singleton UDP
            try {
                SingletonUDP.getInstance();
            } catch (SocketException | UnknownHostException e) {
                throw new RuntimeException(e);
            }
                try {
                    System.out.printf(SingletonUDP.getInstance().reception());

                    SingletonUDP.getInstance().message("test");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });*/


    }
}
