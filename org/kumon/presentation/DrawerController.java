/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class DrawerController implements Initializable {

    @FXML
    private JFXButton btnCerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCerrarSesionAction(ActionEvent event) throws IOException, Exception {
        /*      Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/login.fxml"));
        Contexto.splitPane.getItems().set(0, root);*/
        ContenedorPrincipalController contenedor = new ContenedorPrincipalController();
        contenedor.close();
    }
    
}
