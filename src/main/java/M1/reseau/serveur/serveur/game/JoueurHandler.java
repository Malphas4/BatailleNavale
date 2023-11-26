package M1.reseau.serveur.serveur.game;

import M1.reseau.serveur.cor.ServerCORBuilder;
import M1.reseau.serveur.serveur.ServeurGlobale;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public final class JoueurHandler extends Thread {

    private Socket socket;
    private String _pseudo;




    public Boolean get_monTour() {
        return _monTour;
    }

    public void set_monTour(Boolean _monTour) {
        this._monTour = _monTour;
    }

    Boolean _monTour=true;


    BufferedReader in = null;
    PrintWriter out = null;


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String get_pseudo() {
        return _pseudo;
    }

    public void set_pseudo(String _pseudo) {
        this._pseudo = _pseudo;
    }

    public JoueurHandler(Socket socket, String pseudo) throws IOException {
        setName("ConnectionHandler");
        System.out.println("initialisation d'un nouveau JoueurHandler "+_pseudo);
        this.socket = socket;
        _pseudo=pseudo;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    public JoueurHandler(Socket socket) throws IOException {
        this.socket = socket;
        _pseudo="pseudonyme";
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run(){

        String _msg;

        try{
            while(!isInterrupted()) {

                while ((_msg = in.readLine()) != null) {
                    System.out.println(get_pseudo() + " a recu : " + _msg);

                    String[] _sp = _msg.split(";");
                    if (_pseudo.equals("pseudonyme"))set_pseudo(_sp[2]);

                    ServerCORBuilder
                            .getInstance()
                            .solveServer(
                                    _msg,
                                    ServeurGlobale.sv.get_salon(Integer.parseInt(_sp[1]))
                            );

                }
                out = new PrintWriter(socket.getOutputStream(), true);
            }
        }
        catch(IOException e){
            System.err.println("JoueurHandler : La connexion a été interrompue.");
            for (SalonThread _salon : ServeurGlobale.sv.get_tabSalons()) {
                if (_salon.get_j1() == this) _salon.set_j1((JoueurHandler) null);
                if (_salon.get_j2() == this) _salon.set_j2((JoueurHandler) null);
                if (_salon.get_gameService().get_partie().get_joueur1().equals(get_pseudo()))
                    _salon.get_gameService().get_partie().set_joueur1(null);
                if (_salon.get_gameService().get_partie().get_joueur2().equals(get_pseudo()))
                    _salon.get_gameService().get_partie().set_joueur2(null);
            }
        } finally{
            try{
                if(socket != null)
                    socket.close(); // Close the socket (closing the socket also closes the input and output streams)
            }
            catch(IOException e){
                System.err.println("JoueurHandler : Le socket est fermé.");
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
    synchronized public void sendMessage(String s) throws IOException {
        this.out.println("chat;"+this._pseudo.concat(";")+s);
       //TODO allieurs System.err.println(out);

    }
    synchronized public void sendFinTour() throws IOException {
        this.out.println("fintour;"+_pseudo);
        _monTour=true;
       //TODO allieurs System.err.println(out);
    }    synchronized public void sendDebutTour() throws IOException {
        this.out.println("fintour;"+_pseudo);
        _monTour=true;
       //TODO allieurs System.err.println(out);
    }

}
