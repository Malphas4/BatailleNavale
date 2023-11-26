package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.JoueurHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadTCP extends Thread {

    ServeurGlobale sv;


    private ServerSocket serverSocket;
    private int portNumber = 9999;

    public ThreadTCP() throws IOException {
        setName("ThreadsTCP");
        System.out. println("serveur TCP initialisé");

        sv=ServeurGlobale.sv;
        // create a ServerSocket instance and bind it to the specified port number
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out. println("socket TCP : " +portNumber);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Serveur TCP fin initialisation");
    }

    @Override
    public void run(){
        try{
            while (!isInterrupted()) {

                // accept a new connection
                System.out.println("serveur TCP en attente de connexion");
                Socket socket = serverSocket.accept();
                System.out.println("client se connectant");
                JoueurHandler j = new JoueurHandler(socket);
                System.out.println("creation du JoueurHandler");
                ServeurGlobale.sv.addClient(j);
                j.start();

            }
        } catch(IOException e){
            System.err.println("Le serveur TCP est DEAD.");
        } finally{
            try{
                // close the ServerSocket instance before termination
                serverSocket.close();
                System.out. println("fermeture serveur TCP");

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}

