package M1.reseau.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class implements a TCP/IP java Socket server
 * @author malphas
 *
 */
public class ServeurTCP {

    //static ServerSocket variable
    private static ServerSocket serveur;

    private static int port = 9876;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //create the socket server object
        serveur = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Attente de connexion ...");
            //creating socket and waiting for M1.reseau.client connection
            Socket socket = serveur.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = "YOLO";
            //(String) ois.readObject();
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
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        serveur.close();
    }



}
