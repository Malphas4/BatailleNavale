package M1.reseau.client.controller;

import M1.reseau.client.ClientUDP;
import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.singletons.SingletonUDP;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;


public class ControleurLobby {

    @FXML
    private Button _btnRejoindre;

    @FXML
    private Button _btnchatLobby;

    @FXML
    private TextField _chatInput;

    @FXML
    private TextArea _fieldChatGlobal;

    @FXML
    private TextArea _fieldChatLobby;

    @FXML
    private AnchorPane _infoSalon;

    @FXML
    private VBox _listeSalon;

    //variables pour salons en dynamiques
    int _nbSalons=2;//AF mettre 2
    int nbSalons=0;
    String _salonChoisi;
    String _demandeSalons="NBsalons?";

    ArrayList<Button> _listeBtnSalon = new ArrayList<>(); //our Collection to hold newly created Buttons


    @FXML
    void envoisChatGeneral(ActionEvent event) {
        String _msgm=_chatInput.getText();
        System.out.println(_msgm);
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo().concat(";").concat(_msgm));
        //envoi au serveur d'un message global


    }

    @FXML
    void joindreSalon(ActionEvent event) {

            //change de stage

          //  thisStage.hide();
        /*quand l'id du salon est selection éet change,
        il faut demander au serveur les infos dudit salon
        , les afficher dans le label prévu
        rendre ce bouton visible et interactif
        et n'envoyer la validation que quand ce bouton est cliqué
         */


    }

    @FXML
    void selectionSalon(MouseEvent event) {
        //pas utile car event générés dynamiquement


    }
    @FXML
    public void initialize() {
        System.out.println("initialisation de Lobby et de la liste des salons");

        for (int i = 0; i < nbSalons; i++) {
            Button b=new Button("Salon ".concat(String.valueOf(i)));
            b.setId(String.valueOf(i));
            System.out.println(i);
            b.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    Node source = (Node) event.getSource();
                    _salonChoisi= source.getId();
                    System.out.println("selectionSalon; "+_salonChoisi);
                }
            });

            _listeSalon.getChildren().add(b);


        }
        Timeline timer15Secondes = new Timeline(
                new KeyFrame(Duration.seconds(15),
                        event -> {
                            majSalons(event);
                            System.out.println("timer 15s");
                        }));
        timer15Secondes.setCycleCount(Timeline.INDEFINITE);
        timer15Secondes.play();

    }
    @FXML
    void majSalons(ActionEvent event) {

        try {
            SingletonUDP.getInstance().message(_demandeSalons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            nbSalons = Integer.parseInt(SingletonUDP.getInstance().reception());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < nbSalons; i++) {
            Button b=new Button("Salon ".concat(String.valueOf(i)));
            b.setId(String.valueOf(i));
            System.out.println(i);
            b.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    Node source = (Node) event.getSource();
                    _salonChoisi= source.getId();
                    System.out.println("selectionSalon; "+_salonChoisi);
                }
            });

            _listeSalon.getChildren().add(b);
            Button _tempBtn =new Button("test");
            _tempBtn.setId("4");
            _listeBtnSalon.add(_tempBtn);
            _tempBtn.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("test btn dynamique");
                        //TODO envoi au serveur du numero du salon
                    }
            });

            _listeSalon.getChildren().clear(); //remove all Buttons that are currently in the container
            _listeSalon.getChildren().addAll(_listeBtnSalon); //then add all your Buttons that you just created

        }



        //handle button click
       // _listeSalon.getChildren().clear(); //remove all Buttons that are currently in the container
        //_listeSalon.getChildren().addAll(_listeBtnSalon); //then add all your Buttons that you just created


    }/*
    Timeline fiveSecondsWonder = new Timeline(
        new KeyFrame(Duration.seconds(5),
            new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    //System.out.println("Mise a jour des salons");
                    majSalons(event);
                }
        })
    );*/


}



