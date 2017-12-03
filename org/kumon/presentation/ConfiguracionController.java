/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
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
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.SistemaBO;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class ConfiguracionController implements Initializable {

    @FXML
    private JFXTextField textFieldPrecio;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;
    private static Stage primaryStage;
    private SistemaBO sistemaBO;
    Notifications error;

    public ConfiguracionController() {
        primaryStage = new Stage();
        sistemaBO = Contexto.construirSistemaBO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Double precio = 0.0;
        try {
            precio = sistemaBO.obtenerPrecioPorMateria();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textFieldPrecio.setText(precio.toString());
        textFieldPrecio.setOnKeyPressed(e -> {
            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    aceptarAccion();

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Configuracion.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon Configuracion");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnAceptarAction(ActionEvent event) throws Exception {
        aceptarAccion();

    }

    private void aceptarAccion() throws Exception {
        boolean ok = true;
        try {
            sistemaBO.modificarPrecioPorMateria(Double.parseDouble(textFieldPrecio.getText()));
        } catch (Exception ex) {
            ok = false;
            ex.printStackTrace();
        }
        if (!ok) {
            textFieldPrecio.setUnFocusColor(RED);
            error = Notifications.create();
            error.title("Modificacion de Precio por Materia");
            error.darkStyle();
            error.text("Solo acepta valores númericos y el caracter ' . ' para decimales");
            error.hideAfter(Duration.seconds(3));
            error.position(Pos.BOTTOM_RIGHT);
            error.showError();
        } else {
            textFieldPrecio.setUnFocusColor(GREEN);
            Contexto.precioPorMateria = sistemaBO.obtenerPrecioPorMateria();
            primaryStage.close();
        }
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) {
        primaryStage.close();
    }

}
