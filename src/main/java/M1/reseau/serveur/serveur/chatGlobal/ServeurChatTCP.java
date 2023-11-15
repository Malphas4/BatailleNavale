package M1.reseau.serveur.serveur.chatGlobal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurChatTCP {

    private ServerSocket _socketTCP;

    /**Constructeur
     *
     ***/
    public ServeurChatTCP(ServerSocket socket){
        _socketTCP=socket;
    }
    public void demarageServeur(){

        try {
            //boucle tant que le socket est ouvert
            while (!_socketTCP.isClosed()) {
                Socket _socket = _socketTCP.accept();
                System.out.print("Nouvelle connexion au serveur TCP");
                ClientHandler _clientHandler = new ClientHandler(_socket);
                Thread _thead = new Thread(_clientHandler);
                _thead.start();

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Fermeture du socket et ferme le serveur
     *
     */
    public void fermetureServeurTCP(){
        try {
            if (_socketTCP !=null) _socketTCP.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*public static void main ...
    ServeurSocket _socketTCP=new ServeurSocket(9732);
    ServeurChatTCP _serveur= new ServeurChatTCP(_socketTCP);
    _serveur.start();


     */

}
