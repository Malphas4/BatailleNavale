package M1.reseau.client.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControleurPseudo {

    @FXML
    private Button _btnPseudoConnexion;

    @FXML
    private Button _btnPseudoInscription;

    @FXML
    private TextField _champPseudo;

    @FXML
    private TextArea _msgConsigne;

    @FXML
    private TextArea _msgErreur;

    @FXML
    private Pane _panePseudo;

    @FXML
    void validationPseudo(ActionEvent event) {
        System.out.print("connect\n");


    }
    @FXML
    void inscriptionPseudo(ActionEvent event) {
        System.out.print("inscription\n");


    }

}