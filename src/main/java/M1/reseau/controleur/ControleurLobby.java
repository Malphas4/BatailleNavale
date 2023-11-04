package M1.reseau.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;


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

    @FXML
    void envoisChatGeneral(ActionEvent event) {

    }

    @FXML
    void joindreSalon(ActionEvent event) {

            //change de stage
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
          //  commitToDatabase();
            thisStage.hide();

    }

    @FXML
    void selectionSalon(MouseEvent event) {

    }

}



