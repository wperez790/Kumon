/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private JFXButton btnABM;
    @FXML
    private AnchorPane rootMenu;
    @FXML
    private JFXButton btnAsistencia;
    @FXML
    private JFXButton btnCalendario;
    @FXML
    private JFXButton btnPagos;
    @FXML
    private JFXButton btnPrestamo;
    @FXML
    private JFXButton btnEnviarCorreo;
    @FXML
    private JFXButton btnDatos;
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private JFXDrawer jfxDrawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
       
        jfxDrawer.setSidePane(box);
        HamburgerBasicCloseTransition transition= new HamburgerBasicCloseTransition(jfxHamburger);
        transition.setRate(-1);
        jfxHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            if(jfxDrawer.isShown())
                jfxDrawer.close();
            else
                jfxDrawer.open();
            
        
        });
        
         } 
        catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }    
    
    public void init(){
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
            Contexto.splitPane.getItems().set(0, root);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        
    }

    @FXML
    private void btnABMAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM1.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
        
    }

    @FXML
    private void btnAsistenciaAction(ActionEvent event) throws IOException {
        Contexto.abrirAsistencia();
        
    }

    @FXML
    private void btnCalendarioAction(ActionEvent event) {
    }

    @FXML
    private void btnPagosAction(ActionEvent event) {
    }

    @FXML
    private void btnPrestamoAction(ActionEvent event) {
    }

    @FXML
    private void btnEnviarCorreoAction(ActionEvent event) {
    }

    @FXML
    private void btnDatosAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
            Contexto.splitPane.getItems().set(0, root);
        
    }
}
