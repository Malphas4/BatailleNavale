package M1.reseau.serveur.serveur.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class SalonThread extends Thread {

    private ServerSocket serverSocket;
    private int portNumber = 8888;


    public SalonThread() throws IOException {
        setName("TcpServer");

        // create a ServerSocket instance and bind it to the specified port number
        serverSocket = new ServerSocket(portNumber);
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