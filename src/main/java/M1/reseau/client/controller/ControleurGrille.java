package M1.reseau.client.controller;


import M1.reseau.model.world.grid.Grille;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.scene.control.PasswordField;

public class ControleurGrille {

    @FXML
    private Button _btnAbandon;
    @FXML
    private Button _btnChatPartie;
    @FXML
    private Button _btnQuitter;
    @FXML
    private TextFlow _chatPartie;
    @FXML
    private TextArea _infoParties;
    @FXML
    private AnchorPane _panPartie;
    @FXML
    private TextField _textChatpartie;
    @FXML
    private PasswordField _mdp;

    /*********************************
     * Déclaration des constructeurs
     *********************************/

    public ControleurGrille() {

    }

    @FXML
    void initialize() {

    }

    @FXML
    void Tirer(MouseEvent event) {

    }

    @FXML
    void Abandonner(ActionEvent event) {

    }

    @FXML
    void EnvoyerMessage(ActionEvent event) {

    }

    @FXML
    void Quitter(ActionEvent event) {

    }

}
