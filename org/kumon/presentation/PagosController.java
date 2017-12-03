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
public class PagosController implements Initializable {

    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private JFXButton btnVerificarPagos;
    @FXML
    private JFXButton btnVerificarDeudas;
    @FXML
    private JFXButton btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistrarAction(ActionEvent event) throws IOException {
        Contexto.SeteoPago = true; //Variable que controla despues que va a inicializarse en el fxml/Si es true inicializa para Pagar
        Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarDeudas.fxml"));
        Contexto.splitPane.getItems().set(0, root);
    }

    @FXML
    private void btnVerPagosAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarPagos.fxml"));
        Contexto.splitPane.getItems().set(0, root);
    }

    @FXML
    private void btnVerDeudasAction(ActionEvent event) throws IOException {
        Contexto.SeteoPago=false;  //Variable que controla despues que va a inicializarse en el fxml/Si es true inicializa para Vizualizar Deudas
        Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarDeudas.fxml"));
        Contexto.splitPane.getItems().set(0, root);
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
        Contexto.splitPane.getItems().set(0, root);
    }
    
}
