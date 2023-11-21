package M1.reseau.serveur.serveur.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class JoueurHandler extends Thread {

    private Socket socket;


    public JoueurHandler(Socket socket){
        setName("ConnectionHandler");
        this.socket = socket;
    }


    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;

        String _msg;

        try{

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while( (_msg = in.readLine()) != null){

                String[] _msgTraitement=_msg.split(";");


                //
                if(_msgTraitement[0].equals("TIR"))
                    out.println("case a traiter");

                //victoire par défaut
                else if(_msgTraitement[0].equals("ABANDON"))
                    out.println("victoire");

                    //on quite le while
                else if(_msgTraitement[0].equals("EXIT"))
                    break;
                else if(_msgTraitement[0].equals("CHAT"))
                    out.println("chat");

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(socket != null)
                    socket.close(); // Close the socket (closing the socket also closes the input and output streams)
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
