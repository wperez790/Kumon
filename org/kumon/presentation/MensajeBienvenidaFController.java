/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class MensajeBienvenidaFController implements Initializable {

    @FXML
    private Label labelBienvenida;
    @FXML
    private Label labelNombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText((String)Contexto.getPersona().getNombre());
    }    
    

    
    
    public void init(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MensajeBienvenidaFController.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
}