package M1.reseau.serveur.serveur.game;

import java.io.*;
import java.net.Socket;

public final class JoueurHandler extends Thread {

    private Socket socket;
    private String _pseudo;

    BufferedReader in = null;
    PrintWriter out = null;



    public JoueurHandler(Socket socket,String pseudo) throws IOException {
        setName("ConnectionHandler");
        this.socket = socket;
        _pseudo=pseudo;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    public void run(){


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


    /*Deconnexion d'un client, on retire son handler de la liste
     * */
    public void deconnexionClient(){
        //TODO
       // _salonGlobale.DeleteClient(this);
    }
    /*fermeture du socket et des bufffer pour un client
     * */
    public void fermetureGlobale(Socket socket, BufferedWriter _bufferEcriture, BufferedReader _bufferLecture){
        deconnexionClient();
        try {
            if(_bufferEcriture !=null) _bufferEcriture.close();
            if (_bufferLecture!=null) _bufferLecture.close();
            if(socket !=null) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    synchronized public void message(String s) throws IOException {
        this.out.println(s);
    }
    synchronized public String reception() throws IOException {
        return in.readLine();
    }
}
