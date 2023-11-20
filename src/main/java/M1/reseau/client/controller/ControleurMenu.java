package M1.reseau.client.controller;


import M1.reseau.serveur.singletons.SingletonTCP;
import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
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
    void initialize() {
    }

    @FXML
    void accesLobby(ActionEvent event)throws IOException {
        System.out.print("Aller au Lobby\n");
        //on fixe le type de partie à 1vs 1 pvp
        InformationsUtilisateur.getInstance().set_typePartie(1);
        FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml.fxml"));
        Scene sceneLobby = lobbyLoader.load();
        //ControleurLobby _controleurLobby = lobbyLoader.getController();
        // Appel dialogue pour la demande du pseudo du joueur

        Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActuel.setScene(sceneLobby);
        stageActuel.show();
    }

    @FXML
    void accesLocal(ActionEvent event) throws IOException{
        System.out.print("Jouer en local\n");
        //on fixe le type de partie à 1vs serveur pve
        InformationsUtilisateur.getInstance().set_typePartie(0);
        FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
        // AnchorPane grille=grilleLoader.load();
        // Scene sceneGrille = new Scene(grille);
         Scene sceneGrille=grilleLoader.load();

        // Appel dialogue pour la demande du pseudo du joueur 1

        // Appel dialogue pour la demande du pseudo du joueur 2

        //ControleurMenu ControleurGrille = grilleLoader.getController();
        Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActuel.setScene(sceneGrille);
        stageActuel.show();
    }

    @FXML
    void quitter(ActionEvent event) {
        System.out.print("au revoir\n");
        Platform.exit();
        //fermeture serveur UDP et TCP AF
        //SingletonUDP.fermetureSocket();
        //SingletonTCP.fermetureSocket();
        System.exit(0);
    }

}

