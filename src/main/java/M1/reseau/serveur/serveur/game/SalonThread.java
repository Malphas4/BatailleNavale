package M1.reseau.serveur.serveur.game;

import M1.reseau.serveur.serveur.ServeurGlobale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SalonThread extends Thread {


   // private ServerSocket serverSocket;
   //private int portNumber = 8888;
    private GameService _gameService;
    public String _nom;
    String _description;
    int _nbMaxJoueur=2;
    int _nbConnecte=0;

    private boolean _ready_j1;
    private boolean _ready_j2;

    public int get_temps() {
        return _temps;
    }

    public void set_temps(int _temps) {
        this._temps = _temps;
    }

    //variable qui decide du temsp pour chaque tours
    int _temps=30;
    //variable gérant la différence de temps entre l'horloge serveur et la partie
    int debutPartie=0;
    int _id=0;
    JoueurHandler _j1,  _j2;
    ServeurGlobale _serveurGlobale;
    Vector<JoueurHandler> _listeJoueur;
    ChronoThread _chrono;



    public SalonThread(ServeurGlobale sv, JoueurHandler j1, JoueurHandler j2, int id) {
        //setName("TcpServer");
        _serveurGlobale=ServeurGlobale.sv;
        _j1=j1;
        _j2=j2;
        _id=_serveurGlobale.getNbXlients()+1;
        // create a ServerSocket instance and bind it to the specified port number
//        try {
//            serverSocket = new ServerSocket(portNumber);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        _listeJoueur=new Vector<>();

        /* Initialisation du Chronomètre */
        _chrono = new ChronoThread(this);

        /* Start Thread */
        _chrono.start();
    }

    public SalonThread(ServeurGlobale sv, JoueurHandler j1, int id) throws IOException {
        //setName("TcpServer");
        _serveurGlobale=sv;
        _j1=j1;
        _j2=null;
        _id=_serveurGlobale.getNbXlients()+1;

        /* Initialisation du Chronomètre */
        _chrono = new ChronoThread(this);
        // create a ServerSocket instance and bind it to the specified port number
        //serverSocket = new ServerSocket(portNumber);
    }

    public SalonThread() {
        // setName("TcpServer");
        _serveurGlobale=ServeurGlobale.sv;
        _j1=null;
        _j2=null;
        _id=_serveurGlobale.getNbSalons();
        _gameService = new GameService();

        set_nom("Room(" + _id + ")");
        // create a ServerSocket instance and bind it to the specified port number
        /*try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /*public void run(){
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
        /*finally{
            try{
                // close the ServerSocket instance before termination
                serverSocket.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }*/
    /*}*/



    synchronized  public Socket getServerSocketj1() {
        return _j1.getSocket();
    }
    synchronized  public Socket getServerSocketj2() {
        return _j2.getSocket();
    }

    public void  messageLocal(String s){
        try {
            _j1.message(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            _j2.message(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   /* synchronized public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }*/

    synchronized public GameService get_gameService() {
        return _gameService;
    }

    synchronized public void set_gameService(GameService gameService) {
        this._gameService = gameService;
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

    synchronized public void set_j1(String sj1) {
        this._j1 = _serveurGlobale.get_1Client(sj1);
    }
    synchronized public void set_j2(String sj2) {
        this._j2 = _serveurGlobale.get_1Client(sj2);
    }

    synchronized public JoueurHandler get_j2() {
        return _j2;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public ChronoThread get_chrono() {
        return _chrono;
    }

    public void set_chrono(ChronoThread _chrono) {
        this._chrono = _chrono;
    }

    public boolean is_ready_j1() {
        return _ready_j1;
    }

    public void set_ready_j1(boolean _ready_j1) {
        this._ready_j1 = _ready_j1;
    }

    public boolean is_ready_j2() {
        return _ready_j2;
    }

    public void set_ready_j2(boolean _ready_j2) {
        this._ready_j2 = _ready_j2;
    }
}