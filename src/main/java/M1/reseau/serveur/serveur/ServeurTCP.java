package M1.reseau.serveur.serveur;

import M1.reseau.serveur.salon.Salon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
    //liste des salons du serveur

    private static int port = 9876;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //create the socket server object
        serveur = new ServerSocket(port);
        ArrayList<Salon> _listeSalons=new ArrayList<>();
        boolean _statusServeur=true;

        //commande console pour gérer le serveur
        System.out.println("commandes !\n" +
                "ls pour la liste des salons\n" +
                "lj liste des joueurs\n" +
                "ns [difficulte] [nom] [mot de passe] creation d'un nouveau salon avec mdp facultatif\n" +
                "exit pour clore le serveur\n");
        String str1=input.nextLine();

        if("exit".equals(str1))
        {
            System.out.println("Au revoir, extinction du serveur");
            _statusServeur=false;

        } else if ("ls".equals(str1)) {
            System.out.print("Informations des salons :\n");
            for (Salon iterSalon:_listeSalons) {
                //if (_listeSalons.isEmpty()){
                //}else
                System.out.print("  "+iterSalon.infos()+"\n");

            }

        }

        //keep listens indefinitely until receives 'exit' call or program terminates
        while(_statusServeur){
            System.out.println("Attente de connexion ...");
            //creating socket and waiting for M1.reseau.client connection
            Socket socket = serveur.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
           // String message = "YOLO";
            String message =(String) ois.readObject();
            System.out.println("Message reçu: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Case touchee "+message);
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if M1.reseau.client sends exit request
            if(message.equalsIgnoreCase("exit")){
                System.out.println("Au revoir");
                _statusServeur=false;
            };
        }
        System.out.println(" extinction du serveur!!");
        //close the ServerSocket object
        serveur.close();
    }



}
