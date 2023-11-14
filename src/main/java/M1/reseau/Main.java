package M1.reseau;


import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import M1.reseau.client.ClientUDP;
import M1.reseau.client.controller.*;
import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
       // client  Testclient = new client();
       // serveur Testserveur = new serveur();

        //instantation des informations utilisateur
        InformationsUtilisateur.getInstance();
        try {
            SingletonUDP.getInstance();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            // Testclient.unClient();
            //   Testserveur.unServeur();
            launch();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void start(Stage stage) throws Exception{



            FXMLLoader pseudoLoader = new FXMLLoader(getClass().getResource("/pseudo.fxml"));
            Scene scenePseudo = pseudoLoader.load();
            ControleurPseudo ControleurPseudo = pseudoLoader.getController();

            FXMLLoader attenteLoader = new FXMLLoader(getClass().getResource("/attenteV2.fxml"));
            Scene sceneAttente = attenteLoader.load();
            ControleurAttente ControleurAttente = attenteLoader.getController();


            FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml"));
            Scene sceneLobby = lobbyLoader.load();
            ControleurLobby ControleurLobby = lobbyLoader.getController();

            /*
            FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            Scene sceneGrille = grilleLoader.load();
            ControleurGrille ControleurGrille = grilleLoader.getController();
        */

            //initialisation des modeles
            //fonction
            //            DataModel model = new DataModel();
            //            ControleurMenu.initModel(model);
            //            ControleurPseudo.initModel(model);
            //            menuController.initModel(model);

            /*generation de la fenetre
            Pane paneTest = new Pane();
            Scene sceneTest = new Scene(paneTest, 800, 600);*/

            stage.setScene(scenePseudo);

            stage.show();


            /*
            //init controleurs & model
            DataModel model = new DataModel();
            listController.initModel(model);
            editorController.initModel(model);
            menuController.initModel(model);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
             */

    }


}