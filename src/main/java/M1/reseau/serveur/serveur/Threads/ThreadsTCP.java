package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.JoueurHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadsTCP  extends Thread {

    ServeurGlobale sv;


    private ServerSocket serverSocket;
    private int portNumber = 9999;

    public ThreadsTCP() throws IOException {
        setName("ThreadsTCP");
        sv=ServeurGlobale.sv;
        // create a ServerSocket instance and bind it to the specified port number
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void run(){
        try{
            while (! isInterrupted()) {

                // accept a new connection
                Socket socket = serverSocket.accept();
                JoueurHandler j=new JoueurHandler(socket);
                ServeurGlobale.sv.addClient(j);
                j.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                // close the ServerSocket instance before termination
                serverSocket.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}