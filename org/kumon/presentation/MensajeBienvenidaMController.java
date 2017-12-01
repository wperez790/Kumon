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
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class MensajeBienvenidaMController implements Initializable {

    @FXML
    private Label labelBienvenido;
    @FXML
    private Label labelNombre;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nombre = Contexto.getPersona().getNombre();
        String[] nombreCortado;
        nombreCortado = nombre.split("\\s+");
        labelNombre.setText(nombreCortado[0]);
    }    
    
    public void init(){
        
        try {
            
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MensajeBienvenidaMController.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
}
