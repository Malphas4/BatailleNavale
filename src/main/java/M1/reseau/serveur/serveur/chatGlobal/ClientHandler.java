package M1.reseau.serveur.serveur.chatGlobal;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> _listeClients=new ArrayList<>();
    private Socket _socket;

    //buffer a l'ancienne
    final int _taille = 1024;
    final byte[] _buffer = new byte[_taille];
    //buffer recent
    private BufferedWriter _bufferEcriture;
    private BufferedReader _bufferLecture;
    private String _pseudoClient;

    public ClientHandler(Socket socket){
        try{
            _socket=socket;
            _bufferEcriture=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            _bufferLecture=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            _pseudoClient= _bufferLecture.readLine();
            _listeClients.add(this);
            //broadcast("Initialiastion du chat ")
        }catch (IOException e){
            fermetureGlobale(_socket,_bufferEcriture,_bufferLecture);
        }
    }

    @Override
    public void run() {
        String _message;
        while(_socket.isConnected()){
            try{
                _message=_bufferLecture.readLine();
                //if code broadcast
                if(_listeClients.size()>1) broadcast(_message);
                //si code specifique
                //                    fermetureGlobale(socket, _bufferEcriture, _bufferLecture);
                //gestion des autres codes;
            }catch (IOException e) {
                fermetureGlobale(_socket, _bufferEcriture, _bufferLecture);
            }
        }
    }
    /*Fonction envoyant ubn mesage a chaque client
    * */
    public void broadcast(String messageEnTransit){
        for (ClientHandler iterHandler :_listeClients){
            try{
                if(!iterHandler._pseudoClient.equals(_pseudoClient)) {
                    iterHandler._bufferEcriture.write(messageEnTransit);
                    iterHandler._bufferEcriture.newLine();
                    iterHandler._bufferEcriture.flush();
                }//else if code de fermeture dans le message
                    // fermetureGlobale(socket, _bufferEcriture, _bufferLecture);
            }catch (IOException e){
                fermetureGlobale(_socket, _bufferEcriture, _bufferLecture);

            }
        }
    }
    /*Deconnexion d'un client, on retire son handler de la liste
    * */
    public void deconnexionClient(){
        _listeClients.remove(this);
    }
    /*fermeture du socket et des bufffer pour un client
    * */
    public void fermetureGlobale(Socket socket,BufferedWriter _bufferEcriture,BufferedReader _bufferLecture){
        deconnexionClient();
        try {
            if(_bufferEcriture !=null) _bufferEcriture.close();
            if (_bufferLecture!=null) _bufferLecture.close();
            if(socket !=null) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
