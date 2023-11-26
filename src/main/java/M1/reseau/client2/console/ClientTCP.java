package M1.reseau.client2.console;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.ClientHandler;
import M1.reseau.serveur.serveur.game.JoueurHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientTCP extends Thread {

    private final InetAddress _adr;
    private DataOutputStream out;
    ServeurGlobale sv;
    


    private Socket socket;
    private int port = 9999;
    private BufferedReader in;

    public ClientTCP() throws IOException {
        setName("ClientTCP");
        System.out. println("client TCP initialisé");

        _adr = InetAddress.getByName("localhost");
        InputStream inp = null;

        try {
            inp = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }


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

    public void fermeture(Socket socket,BufferedWriter _bufferEcriture,BufferedReader _bufferLecture){
        try {
            if(_bufferEcriture !=null) _bufferEcriture.close();
            if (_bufferLecture!=null) _bufferLecture.close();
            if(socket !=null) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //recuperation de la socket output
        System.out.printf("envoi du message TCP : "+s+"\n");
        //out.println(s);

    }
    /**
     * reception d'un message avec TCP
     *  @return message recu de type String
     *  @throws IOException
     *  La lecture d'un socket vide est une erreur pour l'intant
     *  une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {

        //You can wrap the InputStream object in an InputStreamReader or BufferedReader to read data at higher level (character and String). For example, using InputStreamReader:
        String s= in.readLine();
        System.out.println("message recu TCP" +s);
        return s;
    }

}
