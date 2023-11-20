package M1.reseau.client.controller;



import M1.reseau.model.game.distance.PartieClient;
import M1.reseau.serveur.singletons.SingletonTCP;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;

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



     //variables du controleur
     Boolean _placementbateaux;
     Boolean _porteAvions,_croiseur,_contreTorpilleurs,_torpilleur=false;
     Boolean _monTour=false;
    int _hauteur,largeur=8;//AF apres recuperation coté serveur
    //= 0;

    int _typeBateau = 0;
    int _nbBateau = 0;



    /*********************************
     * Déclaration des constructeurs
     *********************************/

    public ControleurGrille() {

    }
    @FXML
    public void initialize() {
        _placementbateaux=true;
        Node _joueur ,_adversaire= null;
        HBox Grilles =new HBox(50);
        System.out.println("initialisation des grilles\n");

        boolean enemy = false;
        int ships = 5;
        VBox _maGrille = new VBox();
        VBox _GrilleDeSauron = new VBox();
        for (int y = 0; y < 6; y++) {
            HBox colonne = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKTURQUOISE);
                _uneCase.setStroke(Color.BLACK);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée x:"+_x+" y:"+_y+"\n");
                    if(_placementbateaux){
                        System.out.print("placement d'un bateau \n");
                        //on envoie le debut du bateau a placer
                        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));

                    }else if (_monTour){
                        System.out.print("Feu à volonté\n");
                        //on envoie la case du tir
                        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                        //on attend le message du serveur

                    }else System.out.print("bateaux deja placées ou pas mon tour\n");


                });

                colonne.getChildren().add(_uneCase);
            }
            _maGrille.getChildren().add(colonne);

        }
        Grilles.getChildren().add(_maGrille);

        for (int y = 0; y < 6; y++) {
            HBox colonnade = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKBLUE);
                _uneCase.setStroke(Color.LIGHTGOLDENRODYELLOW);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée ennemie x:"+_x+" y:"+_y+"\n");
                    if (_monTour &&!_placementbateaux){
                        //on envoie la case à toucher
                       // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    }else System.out.print("pas mon tour\n");


                });

                colonnade.getChildren().add(_uneCase);
            }
            _GrilleDeSauron.getChildren().add(colonnade);

        }
        Grilles.getChildren().add(_GrilleDeSauron);

        _panGrilles.getChildren().add(Grilles);

    }

    @FXML
    void Abandonner(ActionEvent event) {
        //on envoie le message d'abandon
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo()));
        //on retourne au Lobby
        FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml"));
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
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo().concat(";").concat(_msgTransit)));

    }

    @FXML
    void Quitter(ActionEvent event) {

        //on envoie au serveur le ragequit
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo()));
        System.out.print("au revoir\n");
        Platform.exit();
        //fermeture serveur UDP et TCP AF
        //SingletonUDP.fermetureSocket();
        //SingletonTCP.fermetureSocket();
        System.exit(0);

    }


    @FXML
    void RotationBateau(ActionEvent event) {

    }

    @FXML
    void ValiderPoser(ActionEvent event) {

    }

    @FXML
    void selectionContreTorpille(ActionEvent event) {
        _typeBateau = 3;
    }

    @FXML
    void selectionCroiseur(ActionEvent event) {
        _typeBateau = 4;
    }

    @FXML
    void selectionPorteAv(ActionEvent event) {
        _typeBateau = 5;
    }

    @FXML
    void selectionTorpilleur(ActionEvent event) {
        _typeBateau = 2;

    }



    void modifCase(int x, int y, Color c, boolean proprio) {
        String _id;
        if (proprio) {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g1"));
        } else {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g2"));
        }
        _sceneGrille.lookup(_id);
    }


}
