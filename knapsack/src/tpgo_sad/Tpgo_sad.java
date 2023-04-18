/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgo_sad;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Youcef
 */
public class Tpgo_sad extends Application {
    
    private Stage stage;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Probleme du sac à dos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
        this.stage = stage;
        this.scene = scene;
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Tpgo_sad instance;

    public Tpgo_sad() {
        instance = this;
    }
       
    public void switchScene(String fxml) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        scene.setRoot(root);
        
    }
    
    public Stage getStage()
    {
        return stage;
    }

    public void switchFullScreen() 
    {
        stage.setFullScreen(!stage.isFullScreen());
    }

}
