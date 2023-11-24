package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.serveur.game.JoueurHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadsTCP  extends Thread {


    private ServerSocket serverSocket;
    private int portNumber = 9999;

    public ThreadsTCP() throws IOException {
        setName("ThreadsTCP");
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

                // create a new thread to handle communication with the remote client
                new JoueurHandler(socket).start();
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