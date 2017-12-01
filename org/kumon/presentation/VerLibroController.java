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
import org.kumon.model.Libro;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class VerLibroController implements Initializable {

    @FXML
    private Label labelNombre;
    @FXML
    private Label labelAutor;
    @FXML
    private Label labelEditorial;
    @FXML
    private Label labelStock;
    @FXML
    private Label labelID;
    @FXML
    private Label labelDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Libro l = Contexto.libro;
       labelAutor.setText(l.getAutor());
       labelNombre.setText(l.getNombre());
       labelDescripcion.setText(l.getDescripcion());
       labelID.setText(l.getIdLibro().toString());
       labelEditorial.setText(l.getEditorial());
       labelStock.setText(l.getStock().toString());      
              
    }    

    public void init() {
        Stage primaryStage = new Stage();
         try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerLibro.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
}
