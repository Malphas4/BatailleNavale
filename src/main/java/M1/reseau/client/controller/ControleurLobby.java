package M1.reseau.client.controller;

import M1.reseau.serveur.salon.Salon;
import javafx.event.ActionEvent;
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

import java.util.ArrayList;


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
    String _salonChoisi;
    ArrayList<Button> _listeBtnSalon = new ArrayList<>(); //our Collection to hold newly created Buttons


    @FXML
    void envoisChatGeneral(ActionEvent event) {
        String _msgm=_chatInput.getText();
        System.out.println(_msgm);
        //envoi au serveur d'un message global


    }

    @FXML
    void joindreSalon(ActionEvent event) {

            //change de stage
         //   Node node = (Node) event.getSource();
         //   Stage thisStage = (Stage) node.getScene().getWindow();
          //  commitToDatabase();
          //  thisStage.hide();



    }

    @FXML
    void selectionSalon(MouseEvent event) {


    }
    @FXML
    public void initialize() {
        System.out.println("initialisation de Lobby et de la liste des salons");
        for (int i = 0; i < _nbSalons; i++) {
            Button b=new Button("Salon ".concat(String.valueOf(i)));
            b.setId(String.valueOf(i));
            System.out.println(i);
            b.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    Node source = (Node) event.getSource();
                    _salonChoisi= source.getId();
                    System.out.println("test Salon "+_salonChoisi);
                }
            });

            _listeSalon.getChildren().add(b);

        }

    }
    @FXML
    void majSalons(ActionEvent event) {
        //try {
            //connection = database.getConnection();
            //statement = connection.createStatement();

            //resultSet = statement.executeQuery(sqlQuery);
            //demande au serveur la liste des salons
            //singletonTCP.getInstance.message("");
            //seperation pour recuperer le code

            //separation pour recuperer la liste des salons
           /* for (:
                 ) {


                //Creation du bouton et du l'event

                for (:
                     ) {
                    bloucle pr recuperer le nom et creer
                }


            }*/
         Button _tempBtn =new Button("test");
         _tempBtn.setId("4");
         _listeBtnSalon.add(_tempBtn);
        _tempBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("test btn dynamique");
            }
        });//handle button click



        //}
            _listeSalon.getChildren().clear(); //remove all Buttons that are currently in the container
            _listeSalon.getChildren().addAll(_listeBtnSalon); //then add all your Buttons that you just created

       /* } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            statement.close();
            connection.close;
        }*/
    }

}



