package M1.reseau.client.controller;


import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField _mdp;


    @FXML
    void validationPseudo(ActionEvent event)throws IOException {
        System.out.print("connect\n");
        Stage stageActuel = null;
        String _messageServeur;
        if (_champPseudo.getText().trim().isEmpty()) {
            //alerte AF
            _msgErreur.setText("Veuillez entrer un pseudo");
        } else {
            String _pseudo=_champPseudo.getText();
            System.out.print("connection pseudo : " +  _pseudo + "\n");

            //Mise a jour du pseudonyme dans l'instance
             InformationsUtilisateur.getInstance().set_pseudo(_champPseudo.getText());
             SingletonUDP.getInstance().message("codePseudo:"+_pseudo);
             _messageServeur=SingletonUDP.getInstance().reception();
             SingletonUDP.getInstance().message("codeMdp:"+_mdp.getText());

            //injection du pseudo dans le controleur Lobby et Grille
            //FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            //ControleurGrille controleurGrille = grilleLoader.getController();
            //appel d'une fonction de Grille ou appel de setPseudo par exemple



            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
            Scene sceneMenu = menuLoader.load();

            //ControleurMenu _controleurMenu = menuLoader.getController();
            stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.setScene(sceneMenu);
            stageActuel.show();

        }

    }
    @FXML
    void inscriptionPseudo(ActionEvent event) {
        System.out.print("inscription\n");


    }

}