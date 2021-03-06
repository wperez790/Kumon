/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class ComprobarAdminController implements Initializable {

    @FXML
    private JFXButton btnComprobarAdmin;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXTextField user;

    //Auxiliares
    private Notifications error;
    private PersonaBO personaBO;
    static private Stage primaryStage;
    //

    public ComprobarAdminController() {
        personaBO = Contexto.construirPersonaBO();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user.setOnKeyPressed(e -> {
            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    if (comprobar()) {
                        primaryStage.close();
                        if (Contexto.pagos) {
                            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Pagos.fxml"));
                            Contexto.splitPane.getItems().set(0, root);

                        } else if (Contexto.baja) {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
                            Contexto.splitPane.getItems().set(0, pane);
                        } else {
                            primaryStage.close();
                            Contexto.abrirPlanillaABM();
                        }
                    }

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
        pass.setOnKeyTyped(e -> {

            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    if (comprobar()) {
                        primaryStage.close();
                        if (Contexto.pagos) {
                            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Pagos.fxml"));
                            Contexto.splitPane.getItems().set(0, root);
                        } else if (Contexto.baja) {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
                            Contexto.splitPane.getItems().set(0, pane);
                        } else {
                            primaryStage.close();
                            Contexto.abrirPlanillaABM();
                        }
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

        });
    }

    public void init() {
        try {
            primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/ComprobarAdmin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnComprobarAdminAction(ActionEvent event) throws Exception {
        if (comprobar()) {
            primaryStage.close();
            if (Contexto.pagos) {
                Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Pagos.fxml"));
                Contexto.splitPane.getItems().set(0, root);
            } else if (Contexto.baja) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
                Contexto.splitPane.getItems().set(0, pane);
            } else {
                Contexto.abrirPlanillaABM();
            }
        } else {
            throw new Exception("Error Admin Incorrecto");
        }
    }

    private boolean comprobar() throws Exception {
        //Comprueba si es un Usuario tipo Admin
        try {
            Contexto.setPersona(personaBO.obtenerPersonaByUser(user.getText()));
        } catch (Exception ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        if (personaBO.comprobarUsuario(user.getText(), pass.getText())) {

            if (personaBO.obtenerPersonaByUser(user.getText()).getTipoUser() == 1) {
                Contexto.tipoUser = 1; //SETEADO DE TIPO USER
                return true;
            } else {
                error = Notifications.create();
                error.title("Comprobacion Administrador");
                error.darkStyle();
                error.text("No es Usuario Administrador");
                error.hideAfter(Duration.seconds(3));
                error.position(Pos.BOTTOM_RIGHT);
                error.showError();
                return false;
            }
        } else {
            error = Notifications.create();
            error.title("Comprobacion Administrador");
            error.darkStyle();
            error.text("Error en la Comprobacion de los Datos");
            error.hideAfter(Duration.seconds(3));
            error.position(Pos.BOTTOM_RIGHT);
            error.showError();
            return false;
        }

    }

}
