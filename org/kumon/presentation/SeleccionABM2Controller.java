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
public class SeleccionABM2Controller implements Initializable {

    @FXML
    private JFXButton btnAlumno;
    @FXML
    private JFXButton btnFamiliar;
    @FXML
    private JFXButton btnAuxiliar;
    @FXML
    private JFXButton btnAdministrador;
    @FXML
    private JFXButton btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void init() {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM2.fxml"));
            Contexto.splitPane.getItems().set(0, root);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

    @FXML
    private void btnAlumnoAction(ActionEvent event) throws IOException, Exception {
        /*     Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABMAlumno.fxml"));
        Contexto.splitPane.getItems().set(0, pane);*/
        Contexto.abrirPlanillaABMAlumno();
    }

    @FXML
    private void btnFamiliarAction(ActionEvent event) throws IOException {
        /* Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABMFamiliar.fxml"));
        Contexto.splitPane.getItems().set(0, pane);*/
        Contexto.abrirPlanillaABMfamiliar();
    }

    @FXML
    private void btnAuxiliarAction(ActionEvent event) throws IOException, Exception {
        /* Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABM.fxml"));
        Contexto.splitPane.getItems().set(0, pane);*/
        Contexto.tipoUser=2;
        Contexto.abrirPlanillaABM();
    }

    @FXML
    private void btnAdministradorAction(ActionEvent event) {
        Contexto.abrirComprobarAdmin();
        //SETEADO DE tipoUser dentro de comprobar() en ComprobarAdminController
    }


    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM1.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    
}
