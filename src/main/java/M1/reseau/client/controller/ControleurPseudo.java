package M1.reseau.client.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
    void validationPseudo(ActionEvent event)throws IOException {
        System.out.print("connect\n");
        Stage stageActuel = null;
        if (_champPseudo.getText().trim().isEmpty()) {
            _msgErreur.setText("Veuillez entrer un pseudo");
        } else {
            System.out.print("connection pseudo : " + _champPseudo.getText() + "\n");

            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
            Scene sceneGrille = menuLoader.load();

            //ControleurMenu _controleurMenu = menuLoader.getController();
            stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.setScene(sceneGrille);
            stageActuel.show();

            stageActuel.setUserData(_champPseudo.getText());
        }

    }
    @FXML
    void inscriptionPseudo(ActionEvent event) {
        System.out.print("inscription\n");


    }

}