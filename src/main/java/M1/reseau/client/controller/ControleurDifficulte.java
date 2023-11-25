package M1.reseau.client.controller;

import M1.reseau.serveur.singletons.SingletonTCP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ControleurDifficulte {




    @FXML
    private AnchorPane Menu;

    @FXML
    private Button _btnJeuLocal;

    @FXML
    private Button _btnJeuMulti;

    @FXML
    private Button _btnQuitter;

    @FXML
    void difficile(ActionEvent event)  {
        InformationsUtilisateur.getInstance().set_bot(3);
//        try {
//           // SingletonTCP.getInstance().message("setbot;3");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("bot moyen");


    }

    @FXML
    void facile(ActionEvent event) {
        InformationsUtilisateur.getInstance().set_bot(1);
//        try {
//         //   SingletonTCP.getInstance().message("setbot;1");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("bot moyen");



    }

    @FXML
    void moyen(ActionEvent event) {
        InformationsUtilisateur.getInstance().set_bot(2);
       /* try {
            SingletonTCP.getInstance().message("setbot;2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println("bot moyen");


    }
    void viewSuivante() throws IOException {
        FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
        // AnchorPane grille=grilleLoader.load();
        // Scene sceneGrille = new Scene(grille);
        Scene sceneGrille=grilleLoader.load();
    }

}
