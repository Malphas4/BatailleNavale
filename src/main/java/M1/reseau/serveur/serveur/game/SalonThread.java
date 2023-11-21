package M1.reseau.serveur.serveur.game;

import M1.reseau.serveur.serveur.ServeurGlobale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SalonThread extends Thread {


    private ServerSocket serverSocket;
    private int portNumber = 8888;
    private GameService _partie;
    public String _nom;
    String _description;
    int _nbMaxJoueur=2;
    int _nbConnecte=0;
    //variable qui decide du temsp pour chaque tours
    int _temps=30;
    //variable gérant la différence de temps entre l'horloge serveur et la partie
    int debutPartie=0;
    int _id=0;
    JoueurHandler _j1,  _j2;
    ServeurGlobale _serveurGlobale;
    Vector<JoueurHandler> _listeJoueur;



    public SalonThread(ServeurGlobale sv, JoueurHandler j1, JoueurHandler j2, int id) throws IOException {
        setName("TcpServer");
        _serveurGlobale=sv;
        _j1=j1;
        _j2=j2;
        _id=_serveurGlobale.getNbXlients()+1;
        // create a ServerSocket instance and bind it to the specified port number
        serverSocket = new ServerSocket(portNumber);
        _listeJoueur=new Vector<>();
    }
    public SalonThread(ServeurGlobale sv, JoueurHandler j1, int id) throws IOException {
        setName("TcpServer");
        _serveurGlobale=sv;
        _j1=j1;
        _j2=null;
        _id=_serveurGlobale.getNbXlients()+1;
        // create a ServerSocket instance and bind it to the specified port number
        serverSocket = new ServerSocket(portNumber);
    }
    public void run(){
        try{
            while (! isInterrupted()) {

                // accept a new connection
                Socket socket = serverSocket.accept();

                // create a new thread to handle communication with the remote client
                //new JoueurHandler(socket).start();

                //recuperation depuis ServeurGlobale pour la les recréer
                _j1.start();
                _j2.start();
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
    synchronized  public ServerSocket getServerSocket() {
        return serverSocket;
    }

    synchronized public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    synchronized public GameService get_partie() {
        return _partie;
    }

    synchronized public void set_partie(GameService _partie) {
        this._partie = _partie;
    }

    synchronized public String get_nom() {
        return _nom;
    }

    synchronized public void set_nom(String _nom) {
        this._nom = _nom;
    }

    synchronized public int getDebutPartie() {
        return debutPartie;
    }

    synchronized public void setDebutPartie(int debutPartie) {
        this.debutPartie = debutPartie;
    }

    synchronized public JoueurHandler get_j1() {
        return _j1;
    }

    synchronized public void set_j1(JoueurHandler _j1) {
        this._j1 = _j1;
    }
    synchronized public void set_j2(JoueurHandler _j2) {
        this._j2 = _j2;
    }

    synchronized public JoueurHandler get_j2() {
        return _j2;
    }

    synchronized public void set_j2(String s) {

        _j2 =  _serveurGlobale.get_1Client(s);
    }


}