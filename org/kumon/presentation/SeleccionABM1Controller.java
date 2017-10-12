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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class SeleccionABM1Controller implements Initializable {

    @FXML
    private JFXButton btnAlta;
    @FXML
    private JFXButton btnBaja;
    @FXML
    private JFXButton btnModificacion;
    @FXML
    private AnchorPane rootSeleccion1;
    @FXML
    private JFXButton btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Contexto.setBaja(false);
        Contexto.setModificar(false);
    }

    public void init() {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM1.fxml"));
            Contexto.splitPane.getItems().set(0, root);
           
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnAltaAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM2.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBajaAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
        Contexto.setBaja(true);
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnModificacionAction(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
        Contexto.setModificar(true);
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

}
