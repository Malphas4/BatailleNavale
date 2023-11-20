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
    String _messageServeur="";


    @FXML
    void validationPseudo(ActionEvent event)throws IOException {

        //recuperation du stage si changement de fenetre
        Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
        ControleurMenu _controleurMenu = menuLoader.getController();
        Scene sceneMenu = menuLoader.load();
        System.out.print("bouton validation pseudo\n");
       // String _messageServeur="";
        String _pseudo=_champPseudo.getText();
        String _mdpInput=_mdp.getText();
        String _connexion="";


        if (_pseudo.trim().isEmpty()||_pseudo.trim().equals("pseudonyme")) {
            //alerte AF
            _msgConsigne.setText("Pseudo manquant ! Veuillez entrer votre speudo et votre mot de passe");
            System.out.print("pseudo vide\n");
        } else if (_mdpInput.trim().isEmpty()){
            _msgConsigne.setText("Mot de passe manquant ! Veuillez entrer votre mot de passe");
            System.out.print("mdp vide\n");
        }else if(!( _pseudo.isEmpty() ||_mdpInput.isEmpty())) {
            _connexion = "0C:".concat(_pseudo).concat(";").concat(_mdpInput);
            System.out.print(_connexion);
            //Mise a jour du pseudonyme dans l'instance
            InformationsUtilisateur.getInstance().set_pseudo(_pseudo);

            //envoi et repetion du message
            SingletonUDP.getInstance().message(_connexion);
            _messageServeur = SingletonUDP.getInstance().reception();

            //injection du pseudo dans le controleur Lobby et Grille
            //FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            //ControleurGrille controleurGrille = grilleLoader.getController();
            //appel d'une fonction de Grille ou appel de setPseudo par exemple


            // traitement de la chaine COR pour savoir quel message traiter
            String[] _msgT = _messageServeur.split(":");
            //messahe valide selon COR a la fin
            if (_msgT[0].equals("1C")) {
                stageActuel.setScene(sceneMenu);

            }
            if (_msgT[0].equals("1D")) {
                _btnPseudoConnexion.setDisable(true);
                _btnPseudoConnexion.setVisible(false);

            }
        }
        stageActuel.show();

    }


    @FXML
    void inscriptionPseudo(ActionEvent event) throws IOException {
        System.out.print("inscription\n");

        //recuperation du stage si changement de fenetre
        Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
        ControleurMenu _controleurMenu = menuLoader.getController();
        Scene sceneMenu = menuLoader.load();
        String _pseudo=_champPseudo.getText();
        String _mdpInput=_mdp.getText();
        String _inscription="";


        if (_pseudo.trim().isEmpty()||_pseudo.trim().equals("pseudonyme")) {
            //alerte AF
            _msgConsigne.setText("Pseudo manquant ! \nVeuillez entrer votre speudo et votre mot de passe");
            System.out.print("pseudo vide\n");
        } else if (_mdpInput.trim().isEmpty()){
            _msgConsigne.setText("Mot de passe manquant ! \nVeuillez entrer votre mot de passe");
            System.out.print("mdp vide\n");
        }else if(!( _pseudo.isEmpty() ||_mdpInput.isEmpty())) {
            _inscription = "12:".concat(_pseudo).concat(";").concat(_mdpInput);
            System.out.print(_inscription);
            //Mise a jour du pseudonyme dans l'instance
            SingletonUDP.getInstance().message(_inscription);
            _messageServeur = SingletonUDP.getInstance().reception();

            //injection du pseudo dans le controleur Lobby et Grille
            //FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            //ControleurGrille controleurGrille = grilleLoader.getController();
            //appel d'une fonction de Grille ou appel de setPseudo par exemple


            // traitement de la chaine COR pour savoir quel message traiter
            String[] _msgT = _messageServeur.split(":");
            //messahe valide selon COR a la fin
            if (_msgT[0].equals("12")) {
                stageActuel.setScene(sceneMenu);
                InformationsUtilisateur.getInstance().set_pseudo(_pseudo);


            }
            if (_msgT[0].equals("13")) {
                _msgConsigne.setText("Compte déjà existant");

            }
        }
        stageActuel.show();


    }

}