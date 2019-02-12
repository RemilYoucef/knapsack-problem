/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgo_sad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Youcef
 */
public class MainWindowController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void identifButtonClick() throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IdentifProbleme.fxml")); 
        Parent root = fxmlLoader.load(); 
        Stage stage = new Stage(); 
        stage.setTitle("Identification du probleme"); 
        stage.setScene(new Scene(root)); 
        stage.show(); 
    }
    
    @FXML
    private void algofButtonClick() throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Algo.fxml")); 
        Parent root = fxmlLoader.load(); 
        Stage stage = new Stage(); 
        stage.setTitle("Algorithme de résolution du probleme"); 
        stage.setScene(new Scene(root)); 
        stage.show(); 
    }
    
        @FXML
    private void deroulementButtonClick() throws IOException
    { 
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Deroulement.fxml")); 
        Parent root = fxmlLoader.load(); 
        Stage stage = new Stage(); 
        stage.setTitle("Deroulement de l'algorithme"); 
        stage.setScene(new Scene(root)); 
        stage.show(); 
    }
}
