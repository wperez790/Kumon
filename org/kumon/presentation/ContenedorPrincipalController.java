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
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class ContenedorPrincipalController implements Initializable {
    
    @FXML
    private SplitPane splitPanePrincipal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Contexto.splitPane = splitPanePrincipal;
        
    }    
    
    public void init(){
        try {
            Contexto.primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/ContenedorPrincipal.fxml"));
            Scene scene = new Scene(root);
            Contexto.primaryStage.setScene(scene);
            Contexto.primaryStage.setTitle("Kumon");
            Contexto.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void close() throws Exception{
        Contexto.primaryStage.close();
        Contexto.abrirLogin();
    }
    
}
