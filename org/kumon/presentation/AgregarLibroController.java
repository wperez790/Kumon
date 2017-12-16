/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.LibroBO;
import org.kumon.main.Contexto;
import org.kumon.model.Libro;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class AgregarLibroController implements Initializable {

    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldAutor;
    @FXML
    private JFXTextField textFieldEditorial;
    @FXML
    private JFXTextField textFieldStock;
    @FXML
    private JFXTextArea textAreaDescripcion;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnCancelar;
    //Aux
    Notifications notificacion;
    LibroBO libroBO;

    public AgregarLibroController() {
        libroBO = Contexto.construirLibroBO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnAgregarAction(ActionEvent event) throws Exception {
        Libro libro = new Libro();
        libro.setAutor(textFieldAutor.getText());
        libro.setDescripcion(textAreaDescripcion.getText());
        libro.setNombre(textFieldNombre.getText());
        libro.setStock(Integer.parseInt(textFieldStock.getText()));
        libro.setEditorial(textFieldEditorial.getText());

        if (libroBO.nuevoLibro(libro)) {

            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Registrado con Exito";
            notificar(img, mensaje);
        } else {

            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Error en el Registro";
            notificar(img, mensaje);
        }
    }

    private void notificar(Image img, String mensaje) throws IOException {
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text(mensaje);
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
        notificacion.show();
    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/AgregarLibro.fxml"));
            Contexto.splitPane.getItems().set(0, root);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

}
