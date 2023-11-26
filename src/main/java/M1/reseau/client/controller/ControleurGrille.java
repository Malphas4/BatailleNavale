package M1.reseau.client.controller;


import M1.reseau.model.world.element.Case;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.serveur.singletons.SingletonTCP;
import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class ControleurGrille {


    @FXML
    private Button _btnAbandon;
    @FXML
    private Button _btnChatPartie;
    @FXML
    private Button _btnQuitter;
    @FXML
    private TextArea _chatPartie;
    @FXML
    private TextArea _infoParties;
    @FXML
    private AnchorPane _panPartie;
    @FXML
    private TextField _textChatpartie;
    @FXML
    private PasswordField _mdp;
    @FXML
    private Pane _panGrilles;

    @FXML
    private Button _btnContreTorpille;

    @FXML
    private Button _btnCroiseur;

    @FXML
    private Button _btnPorteAv;
    @FXML
    private Scene _sceneGrille;

    @FXML
    private Button _btnRotation;

    @FXML
    private Button _btnTorpilleur;

    @FXML
    private Button _btnValidationPause;
    @FXML
    private Label _labelChrono;
    @FXML
    private Label _labelTimer;


    VBox maGrille ;
    VBox saGrille;
    ArrayList<Case>  _mesBateaux=new ArrayList<>();
    ArrayList<Case>  _bateauxMechants=new ArrayList<>();
    ArrayList<Case> _mesBateauxAttenteValidation=new ArrayList<>();
    int chrono=30;
    boolean debutCrono=false;

    //initialisation des couleurs utilisées
    Color _couleurEau = Color.DARKTURQUOISE;
    Color _couleurBord = Color.BLACK;
    Color _couleurBateau=Color.GREEN;
    Color _couleurRocher = Color.DARKGREY;
    Color _couleurPasDeCible = Color.DARKSLATEBLUE;
    Color _couleurEpave = Color.DARKRED;




    //variables du controleur
     boolean _placementbateaux;
     boolean _porteAvions,_croiseur,_contreTorpilleurs,_torpilleur=false;
     boolean _monTour=false;//variable fixant le tour du joueur
    boolean _positionBateau=true;//variable fisant si le bateau doit etre msi verticalement ou horizontalement
    //horizon par defaut
    int _hauteur,largeur=8;//AF apres recuperation coté serveur
    //= 0;

    int _monSalon;



    String _monPseudo;
    int _typeBateau = 0;
    int _nbBateau = 0;
    String _traitementTCP;



    /*********************************
     * Déclaration des constructeurs
     *********************************/

    public ControleurGrille() {

    }
    @FXML
    public void initialize() {
        //disable le chat local si la partie est contre un bot
        if (InformationsUtilisateur.getInstance().get_typePartie()==0) {
            _chatPartie.setText("Le chat du salon est inactif contre un bot");
            _btnChatPartie.setDisable(true);
            _textChatpartie.setDisable(true);
            _chatPartie.setDisable(true);
        }


        //initialisation des arrays contenant les cases des bateaux

        _mesBateauxAttenteValidation=new ArrayList<>();


        _placementbateaux=true;
        Boolean proretaire=true;
        Node _joueur ,_adversaire= null;
        HBox Grilles =new HBox(50);
        System.out.println("initialisation des grilles\n");

        boolean enemy = false;
        int ships = 5;
        String _id="";
        proretaire=true;
        VBox _maGrille = new VBox();


        VBox _GrilleDeSauron = new VBox();
        for (int y = 0; y < 8; y++) {
            HBox colonne = new HBox();
            for (int x = 0; x < 8; x++) {
                // selon le propriétaire de la grille
                if (proretaire) {
                    //attribution de l'id afin de retrouver la case
                    _id = String.valueOf(x).concat(String.valueOf(y)).concat("g1");
                    _couleurEau = Color.DARKTURQUOISE;
                    _couleurBord = Color.BLACK;
                    //=String.valueOf(x).concat(String.valueOf(y)).concat("g2")
                } else {
                    //attribution de l'id afin de retrouver la case
                    _id = String.valueOf(x).concat(String.valueOf(y)).concat("g2");

                    _couleurEau = Color.DARKBLUE;
                    _couleurBord = Color.LIGHTGOLDENRODYELLOW;
                }

                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKTURQUOISE);
                _uneCase.setId(_id);
                _uneCase.setStroke(Color.BLACK);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée x:"+_x+" y:"+_y+"\n");
                    String psd= InformationsUtilisateur.getInstance().get_pseudo();
                    String salon= String.valueOf(InformationsUtilisateur.getInstance().get_salon());
                    if (_placementbateaux ) {
                        for (Case iterEau :_mesBateauxAttenteValidation) {
                            modifCase(iterEau.get_x(), iterEau.get_y(),_couleurEau, true);
                        }
                        _mesBateauxAttenteValidation.clear();
                        if((_positionBateau &&(_x + _typeBateau < 8)  )||(!_positionBateau &&(_y + _typeBateau < 8))){
                        for (int k = 0; k < _typeBateau; k++) {
                            if (_positionBateau) {
                                if (!casePrise(_mesBateaux,_x+k,_y)){
                                _mesBateauxAttenteValidation.add(new CaseBateau(_x + k, _y));
                                modifCase(_x + k, _y, Color.PALEVIOLETRED, true);

                                }
                            } else{
                                if (!casePrise(_mesBateaux,_x,_y+k)) {
                                    _mesBateauxAttenteValidation.add(new CaseBateau(_x, _y + k));
                                    modifCase(_x, _y + k, Color.PALEVIOLETRED, true);
                                }

                            }
                        }
                        System.out.print("placement d'un bateau \n");

                    }
                    /*else if(_nbBateau==4){
                     */
                        //transimition des coordonnes des bateaux
                    }
                    else if (_monTour){
                        System.out.print("Blue on blue\n");
                    }else System.out.print("bateaux deja placées ou pas mon tour\n");


                });

                colonne.getChildren().add(_uneCase);
            }
            _maGrille.getChildren().add(colonne);

        }
        Grilles.getChildren().add(_maGrille);

        for (int y = 0; y < 8; y++) {
            HBox colonnade = new HBox();
            for (int x = 0; x < 8; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKBLUE);
                _uneCase.setStroke(Color.LIGHTGOLDENRODYELLOW);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée ennemie x:"+_x+" y:"+_y+"\n");
                    if (_monTour &&!_placementbateaux){
                        //TODO on envoie la case à toucher
                        //on envoie la case du tir
//                        try {
                        System.out.println("envoi du tir TCP");
                        // Receive : tirer;[salon id];[joueur tireur];[joueur victime];[x];[y]
                        try {
                            SingletonTCP.getInstance().message(
                                    "tirer;".
                                            concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon())).
                                            concat(";").
                                            concat(InformationsUtilisateur.getInstance().get_pseudo()).
                                            concat(";").
                                            //concat( pseudo ennemi PROBLEME), .concat(";")
                                                    concat(String.valueOf(_x)).
                                            concat(";")
                                            .concat(String.valueOf(_y)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        _monTour=false;
                        //TODO
                        try {
                            _traitementTCP=SingletonTCP.getInstance().reception();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String[] _traitementTCP2=_traitementTCP.split(";");
                        //toucher;[salon id];[tireur];[victime];[x];[y];[statut case]
                        if (_traitementTCP2[0].equals("toucher")&& _traitementTCP2[6].equals("true")){
                            modifCase(Integer.parseInt(_traitementTCP2[4]),Integer.parseInt(_traitementTCP2[5]),_couleurEpave,false);
                        }else if (_traitementTCP2[0].equals("toucher")&& _traitementTCP2[6].equals("false")){
                            modifCase(Integer.parseInt(_traitementTCP2[4]),Integer.parseInt(_traitementTCP2[5]),_couleurPasDeCible,false);
                        }


//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
                        //on attend le message du serveur et on change la case de couleur en fonction du resultat

                    }else System.out.print("pas mon tour\n");


                });

                colonnade.getChildren().add(_uneCase);
            }
            _GrilleDeSauron.getChildren().add(colonnade);

        }
        Grilles.getChildren().add(_GrilleDeSauron);
        maGrille=_maGrille;
        saGrille=_GrilleDeSauron;

        _panGrilles.getChildren().add(Grilles);


        //chrono tours
        Timeline timer1Seconde = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            try {
                                majChrono();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            //System.out.println("timer 1s");
                        }));
        timer1Seconde.setCycleCount(Timeline.INDEFINITE);
        timer1Seconde.play();




    }

    private void majChrono() throws IOException {
        //chrono Interne
        if (!_placementbateaux && debutCrono){
            chrono--;
          //  SingletonTCP.getInstance().reception
                    //("chrono;".concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon())));
            //TODO String[] msgT=SingletonTCP.getInstance().reception().split(";");
            //_monTour= Boolean.parseBoolean(msgT[1]);
            //_labelChrono.setText("00:".concat(String.valueOf(chrono)));
               //_monTour= !_monTour;
                SingletonTCP.getInstance().message("tour suivant;"
                       .concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon()))
                               .concat(";")
                       .concat(InformationsUtilisateur.getInstance().get_pseudo()));

                try {
                    String s =SingletonTCP.getInstance().reception();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] sT= toString().split(";");
                if (sT[0].equals("tour suivant") &&sT[2].equals(InformationsUtilisateur.getInstance().get_pseudo())) {

                    _monTour = true;
                    debutCrono = true;
                }  else {
                    _monTour=false;
                    debutCrono=false;
                    chrono=30;

                }


        }
        //Chronoserveur ayant la priorite des tours
    }

    @FXML
    void Abandonner(ActionEvent event) {
        //on envoie le message d'abandon
        messageAbandon();
        InformationsUtilisateur.getInstance().set_salon(-1);
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo()));
        //on retourne au Lobby
        FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
        try {
            Scene sceneLobby = lobbyLoader.load();
            Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.setScene(sceneLobby);
            stageActuel.show();
        } catch (IOException e) {
            System.out.print("erreur lors du retour au lobby, fermeture de l'application");
            Quitter(event);
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EnvoyerMessage(ActionEvent event) {
        String _msgTransit=_textChatpartie.getText();
        //on envoie au serveur le message avec le code pour le chat local
        try {
          SingletonTCP.getInstance().message("chat salon;".
                  concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon())
                  .concat(";")
                  .concat(InformationsUtilisateur.getInstance().get_pseudo().
                  concat(";")
                                  .concat(_msgTransit))));
      // System.out.println("bot moyen");
        } catch (IOException e) {
          throw new RuntimeException(e);
       }


        //reception du message  lobby
         String _reception= null;
        try {
            _reception = SingletonTCP.getInstance().reception();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] _receptionT =_reception.split(";");
        _chatPartie.setText(_chatPartie.getText().concat("\n").concat(_receptionT[1]));
        System.out.println("message lobby recu");


    }

    @FXML
    void Quitter(ActionEvent event) {
        messageAbandon();
        //on envoie au serveur le ragequit
        System.out.print("au revoir\n");
       // SingletonUDP.getInstance().fermetureSocket();
        //SingletonTCP.getInstance().fermetureSocket();
        Platform.exit();
        //fermeture serveur UDP et TCP AF

        System.exit(0);

    }

    private void messageAbandon() {
        //abandonner;[salon id];[joueur id]
        try {
            SingletonTCP.getInstance().message(
                    ("abandonner;".
                    concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon())).
                    concat(";").
                    concat(InformationsUtilisateur.getInstance().get_pseudo())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void RotationBateau(ActionEvent event) {
        _positionBateau=!_positionBateau;

    }

    @FXML
    void ValiderPoser(ActionEvent event) {
        if(!_mesBateauxAttenteValidation.isEmpty()) {
            _mesBateaux.addAll(_mesBateauxAttenteValidation);
            for (Case unBateau : _mesBateaux) {
                //Affichage des bateaux
                modifCase(unBateau.get_x(), unBateau.get_y(), _couleurBateau, true);
            }
            switch (_typeBateau) {

                case 0:
                    System.out.println("pas de bateau choisi");
                    break;

                case 2:
                    System.out.println("torpi");
                    _btnTorpilleur.setVisible(false);
                    _btnTorpilleur.setDisable(true);
                    _torpilleur = true;
                    _nbBateau++;
                    break;

                case 3:
                    System.out.println("contre torpie");
                    _btnContreTorpille.setVisible(false);
                    _btnContreTorpille.setDisable(true);
                    _contreTorpilleurs = true;
                    _nbBateau++;

                    break;
                case 4:
                    System.out.println("croiseur");
                    _btnCroiseur.setVisible(false);
                    _btnCroiseur.setDisable(true);
                    _croiseur = true;
                    _nbBateau++;

                    break;
                case 5:
                    System.out.println("porte-avions");
                    _nbBateau++;
                    _btnPorteAv.setVisible(false);
                    _btnPorteAv.setDisable(true);
                    _porteAvions = true;
                    break;
                default:
                    System.out.println("Choix incorrect");
                    break;
            }
            _typeBateau = 0;
        /*modifCase(3, 3,Color.GREEN ,true);
        modifCase(3, 4,Color.GREEN ,true);
        modifCase(1, 1, Color.GREEN ,true);
        modifCase(1, 2,Color.GREEN ,true);
        modifCase(1, 0,Color.GREEN ,true);*/
            if (_nbBateau >= 4) {
                //bateaux mis, on desactive les boutons de pose
                _btnRotation.setVisible(false);
                _btnRotation.setDisable(true);
                _btnValidationPause.setVisible(false);
                _btnValidationPause.setDisable(true);
                _placementbateaux = false;
                //TODO envoi des bateaux
                System.out.println("Envoi des bateaux au serveur");
                for (Case unBateau : _mesBateaux) {
                    //envoi d'une case bateau
                    modifCase(unBateau.get_x(), unBateau.get_y(), _couleurBateau, true);
                    String Bato = ("init bateau;")
                            .concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon()))
                            .concat(";")
                            .concat(InformationsUtilisateur.getInstance().get_pseudo())
                            .concat(";")
                            .concat(String.valueOf(unBateau.get_x()))
                            .concat(";")
                            .concat(String.valueOf(unBateau.get_y()));

                }
                String Bato = ("commencer;")
                        .concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon()))
                        .concat(";")
                        .concat(InformationsUtilisateur.getInstance().get_pseudo());
                try {
                    SingletonTCP.getInstance().message(Bato);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    String s = SingletonTCP.getInstance().reception();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] sT = toString().split(";");
                if (sT[0].equals("commencer") && sT[2].equals(InformationsUtilisateur.getInstance().get_pseudo())) {
                    _monTour = true;
                    debutCrono = true;
                }
                /*try {
                    String s = SingletonTCP.getInstance().reception();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] sT = toString().split(";");
                if (sT[0].equals("join") && sT[2].equals(InformationsUtilisateur.getInstance().get_pseudo())) {
                    _monTour = true;
                    debutCrono = true;
                }*/


            }



        }

    }

    @FXML
    void selectionContreTorpille(ActionEvent event)
    {
        _mesBateauxAttenteValidation.clear();
        _typeBateau = 3;
    }

    @FXML
    void selectionCroiseur(ActionEvent event)
    {
        _mesBateauxAttenteValidation.clear();

        _typeBateau = 4;
    }

    @FXML
    void selectionPorteAv(ActionEvent event) {
        _mesBateauxAttenteValidation.clear();
        _typeBateau = 5;
    }

    @FXML
    void selectionTorpilleur(ActionEvent event) {
        _mesBateauxAttenteValidation.clear();
        _typeBateau = 2;

    }



    void modifCase(int x, int y, Color c, boolean proprio) {
        String _id;
        if (proprio) {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g1"));
        } else {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g2"));
        }

        Rectangle _uneCase = (Rectangle) maGrille.getScene().lookup(_id);
        _uneCase.setFill(c);
    }

    public String get_monPseudo() {
        return _monPseudo;
    }

    public void set_monPseudo(String _monPseudo) {
        this._monPseudo = _monPseudo;
    }
    public boolean casePrise(ArrayList<Case> _aVerifier,int x,int y){
        for (Case iterPresence : _mesBateauxAttenteValidation) {
            int cooX = iterPresence.get_x();
            int cooY = iterPresence.get_y();

            if (cooX == x && cooY == y) {
                return true;
            }
        }
        return false;  // (x, y) not found
    }
//    public void bateauLock(int i){
//        switch (i) {
//
//            case 0:
//                System.out.println("pas de bateau mis sur la grille");
//                break;
//
//            case 2:
//                System.out.println("torpi sur la grille");
//                _torpilleur=true;
//                _nbBateau++;
//                break;
//
//            case 3:
//                System.out.println("contre torpie");
//                _btnContreTorpille.setVisible(false);
//                _btnContreTorpille.setDisable(true);
//                _contreTorpilleurs=true;
//                _nbBateau++;
//
//                break;
//            case 4:
//                System.out.println("croiseur");
//                _btnCroiseur.setVisible(false);
//                _btnCroiseur.setDisable(true);
//                _croiseur=true;
//                _nbBateau++;
//
//                break;
//            case 5:
//                System.out.println("porte-avions");
//                _nbBateau++;
//
//                _btnPorteAv.setVisible(false);
//                _btnPorteAv.setDisable(true);
//                _porteAvions=true;
//                break;
//            default:
//                System.out.println("Choix incorrect");
//                break;
//        }


//    }
    //void gagner(); TODO

    //void perdu();TODO
    void TraitemenString(){

    }
    void envoiString(String s){

    }

}


