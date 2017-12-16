/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PagosBO;
import org.kumon.main.Contexto;
import org.kumon.model.Pago;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class InputPagoController implements Initializable {

    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXButton btnPagar1;
    @FXML
    private Label labelidDeuda;
    @FXML
    private JFXTextField textFieldValor;

    //AUX
    PagosBO pagosBO;
    Pago pago;
    private static Stage primaryStage;
    Notifications notificacion;

    public InputPagoController() {
        pagosBO = Contexto.construirPagosBO();
        pago = new Pago();
        primaryStage = new Stage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelidDeuda.setText(Contexto.idDeuda);
    }

    @FXML
    private void btnPagarTotalAction(ActionEvent event) throws Exception {
        Date fecha = new Date();
        java.sql.Date date = new java.sql.Date(fecha.getTime());
        pago.setFecha(date);
        pago.setIdDeuda(Integer.parseInt(Contexto.idDeuda));
        pago.setMonto(Contexto.monto);
        if (pagosBO.registrarPago(pago)) {

            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Registrado con Exito";
            notificar(img, mensaje);
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
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
        primaryStage.close();
        recargar();
        notificacion.show();
    }

    private void recargar() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarDeudas.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnPagarValorAction(ActionEvent event) throws Exception {
        Date fecha = new Date();
        java.sql.Date date = new java.sql.Date(fecha.getTime());
        pago.setFecha(date);
        pago.setIdDeuda(Integer.parseInt(Contexto.idDeuda));
        pago.setMonto(Double.parseDouble(textFieldValor.getText()));
        if (pagosBO.registrarPago(pago)) {

            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Registrado con Exito";
            notificar(img, mensaje);
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            String mensaje = "Error en el Registro";
            notificar(img, mensaje);
        }
    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/InputPago.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon Pagos");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
