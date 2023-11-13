package M1.reseau.client.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurMenu {

        @FXML
        private AnchorPane Menu;

        @FXML
        private Button _btnJeuLocal;

        @FXML
        private Button _btnJeuMulti;

        @FXML
        private Button _btnQuitter;

        @FXML
        private Stage _menuDemarage;

        @FXML
        void accesLobby(ActionEvent event)throws IOException {
            System.out.print("Aller au Lobby\n");

                FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml"));
                Scene sceneLobby = lobbyLoader.load();
                //ControleurLobby _controleurLobby = lobbyLoader.getController();
                Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageActuel.setScene(sceneLobby);
                stageActuel.show();


        }

        @FXML
        void accesLocal(ActionEvent event) throws IOException{
            System.out.print("Jouer en local\n");
            FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
           // AnchorPane grille=grilleLoader.load();
           // Scene sceneGrille = new Scene(grille);
             Scene sceneGrille=grilleLoader.load();

            //ControleurMenu ControleurGrille = grilleLoader.getController();
            Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.setScene(sceneGrille);
            stageActuel.show();


        }

        @FXML
        void quitter(ActionEvent event) {
            System.out.print("au revoir\n");
            Platform.exit();
            System.exit(0);


        }

    }

