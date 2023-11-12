package M1.reseau.client.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        void accesLobby(ActionEvent event) {
            System.out.print("Aller au Lobby\n");

        }

        @FXML
        void accesLocal(ActionEvent event) {
            System.out.print("Jouer en local\n");


        }

        @FXML
        void quitter(ActionEvent event) {
            System.out.print("au revoir\n");


        }

    }

