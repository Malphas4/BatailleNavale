package M1.reseau;


import java.io.IOException;

import M1.reseau.client.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
       // client  Testclient = new client();
       // serveur Testserveur = new serveur();
        try {
            // Testclient.unClient();
            //   Testserveur.unServeur();
            launch();

        } catch (Exception e) {
           // e.printStackTrace();

        }

    }



    @Override
    public void start(Stage stage) throws Exception{


            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
            Scene sceneMenu = (Scene) menuLoader.load();

            ControleurMenu ControleurMenu = menuLoader.getController();

            FXMLLoader pseudoLoader = new FXMLLoader(getClass().getResource("/pseudo.fxml"));
            Scene scenePseudo=(Scene) pseudoLoader.load();
            ControleurPseudo ControleurPseudo = pseudoLoader.getController();

            FXMLLoader attenteLoader = new FXMLLoader(getClass().getResource("/attenteV2.fxml"));
            Scene sceneAttente=(Scene) attenteLoader.load();
            ControleurAttente ControleurAttente = attenteLoader.getController();

   /*
            FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml"));
            Scene sceneLobby=(Scene) lobbyLoader.load();
            ControleurLobby ControleurLobby = menuLoader.getController();
   */
            FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            Scene sceneGrille=(Scene) grilleLoader.load();
            ControleurGrille ControleurGrille = grilleLoader.getController();

            //initialisation des modeles
            //fonction
            //            DataModel model = new DataModel();
            //            ControleurMenu.initModel(model);
            //            ControleurPseudo.initModel(model);
            //            menuController.initModel(model);

            /*generation de la fenetre
            Pane paneTest = new Pane();
            Scene sceneTest = new Scene(paneTest, 800, 600);*/

            stage.setScene(sceneMenu);

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


       /* } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}