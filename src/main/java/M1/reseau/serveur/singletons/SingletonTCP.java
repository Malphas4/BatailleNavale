package M1.reseau.serveur.singletons;

import java.io.*;
import java.net.*;

public class SingletonTCP implements Serializable
{
    InetAddress host;

    {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    //pour socket client AF
   Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;


    private static SingletonTCP _singletonTCP=null;

    int port = 9999;
    /** Constructeur privé */
    private SingletonTCP() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            socket  = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CLient TCp lancé au port "+port);

    }

    /** Instance unique pré-initialisée */
    private static SingletonTCP INSTANCE;

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonTCP getInstance()  {
        if (_singletonTCP == null) {
            _singletonTCP = new SingletonTCP();
        }
        return SingletonTCP._singletonTCP;
    }


    public void fermetureSocket()  {
        try{
            if(socket != null) {

                in.close();
                out.close();
                socket.close();
            }
        }catch (IOException e){ e.printStackTrace();}

    }

    /*envoi d'un message TCP
    *
    *
    * */
    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //recuperation de la socket output
        System.out.printf("envoi du message TCP : "+s+"\n");
        //OutputStream out = socket.getOutputStream();
        out.println(s);

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
/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class TcpClient {

    private void process() {
          Socket socket = null;

          BufferedReader in = null;
          PrintWriter out = null;

          try{
                /* Establish a TCP connection with the server running locally on the port number 7777.
                 * You can write "localhost" instead of "127.0.0.1".
                 * If the server is running on a remote computer, replace "127.0.0.1" with the server's IP address or hostname
                 */
                /*socket = new Socket("127.0.0.1", 7777);

                        // Get the input and output streams
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream(), true);

                        // Send a GET_TIME request and print the response to the standard output
                        out.println("GET_TIME");
                        System.out.println(in.readLine());

                        // Send a GET_JAVA_VERSION request and print the response to the standard output
                        out.println("GET_JAVA_VERSION");
                        System.out.println(in.readLine());

                        // Send a CLOSE request to the server to end communication
                        out.println("CLOSE");
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


public static void main(String[] args){
        new TcpClient().process();
        }
        }

*/
