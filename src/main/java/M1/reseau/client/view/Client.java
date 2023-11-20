package M1.reseau.client.view;

import M1.reseau.serveur.serveur.chatGlobal.
        ServeurChatTCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket _socket;
    private BufferedReader _bufferLecture;
    private BufferedWriter _bufferEcriture;
    //inclus pour l'instant mais utilisera l'instance
    //apres
    //car UDP sera utilise pour la connexion
    //le pseudo sera alors deja instancie
   // ou attricie pour la creation d un compte
    String _pseudo;

    public Client(Socket socket, String pseudo){
        try{
            _socket=socket;
            _bufferEcriture=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            _bufferLecture=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            _pseudo=pseudo;
        }catch (IOException e){
            fermetureGlobale(_socket,_bufferEcriture,_bufferLecture);
        }
    }

    /*Fonction d'envoie de message par console
    * La fonction similaire avec le message en parametre est a privilégier
    * de par son code avant le message
    * */
    public void Message(){
        try{
            _bufferEcriture.write(_pseudo);
            _bufferEcriture.newLine();
            _bufferEcriture.flush();

            //pour tester
            Scanner scanner= new Scanner(System.in);
            while (_socket.isConnected()){
                String _messageEnTransit=scanner.nextLine();
                _bufferEcriture.write("02:"+_pseudo+":"+_messageEnTransit);
                _bufferEcriture.newLine();
                _bufferEcriture.flush();
            }

        }catch (IOException e){
            fermetureGlobale(_socket,_bufferEcriture,_bufferLecture);
        }
    }
    public void Message(String _contenu){
        try{
           /* _bufferEcriture.write(_pseudo);
            _bufferEcriture.newLine();
            _bufferEcriture.flush();

            //pour tester
            Scanner scanner= new Scanner(System.in);
            while (_socket.isConnected()){
                //String _messageEnTransit=scanner.nextLine();
                _bufferEcriture.write(_contenu);
                _bufferEcriture.newLine();
                _bufferEcriture.flush();
            }*/
            _bufferEcriture.write(_contenu);
            _bufferEcriture.newLine();
            _bufferEcriture.flush();

        }catch (IOException e){
            fermetureGlobale(_socket,_bufferEcriture,_bufferLecture);
        }
    }

    /**Fonction D'ecoute
     * */
    public void EcouteMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String _messageGlobale;

                while (_socket.isConnected()){
                    try {
                        _messageGlobale=_bufferLecture.readLine();
                        //getControleur
                        //get le champ _chatGlobal
                        //set content actuel+ _messageGlobale
                        System.out.println("_messageGlobale");
                    }catch (IOException e){
                        fermetureGlobale(_socket,_bufferEcriture,_bufferLecture);
                    }
                }
            }
        }).start();
    }

    public void fermetureGlobale(Socket socket,BufferedWriter _bufferEcriture,BufferedReader _bufferLecture){
        try {
            if(_bufferEcriture !=null) _bufferEcriture.close();
            if (_bufferLecture!=null) _bufferLecture.close();
            if(socket !=null) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
        /*public static void main ...



     */


    public static void main(String[] args) throws IOException {
        Scanner scanner= new Scanner(System.in);
        System.out.print("mot de passe ?\n");
        String msg=scanner.nextLine();
        Socket _socket=new Socket("localhost",18000);
        Client client = new Client(_socket,msg);
    }
}
