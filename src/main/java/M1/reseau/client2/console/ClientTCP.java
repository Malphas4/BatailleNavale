package M1.reseau.client2.console;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.JoueurHandler;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientTCP extends Thread {

    private final InetAddress _adr;
    ServeurGlobale sv;


    private Socket socket;
    private int port = 9999;

    public ClientTCP() throws IOException {
        setName("ClientTCP");
        System.out. println("client TCP initialisé");

        _adr = InetAddress.getByName("localhost");


        sv=ServeurGlobale.sv;
        // create a ServerSocket instance and bind it to the specified port number
        try {
            socket = new Socket(_adr, port);
            System.out. println("socket TCP : " +port);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Serveur TCP fin initialisation");
    }

    @Override
    public void run(){
        try{
            while (()) {

                // accept a new connection



            }
        } finally{
            try{
                // close the ServerSocket instance before termination
                socket.close();
                System.out. println("fermeture serveur TCP");

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
