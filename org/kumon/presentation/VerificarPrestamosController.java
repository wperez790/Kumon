/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PrestamoBO;
import org.kumon.main.Contexto;
import org.kumon.model.Prestamo;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class VerificarPrestamosController implements Initializable {

    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnConfirmarDev;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXTreeTableView<Loan> tablePrestamos;
    @FXML
    private JFXComboBox<String> comboBoxFiltro;

    //AUX
    PrestamoBO prestamoBO;
    private Notifications notificacion;
    //

    public VerificarPrestamosController() {
        prestamoBO = Contexto.construirPrestamoBO();
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        /*Seteo comboBox*/
        ObservableList<String> filtros
                = FXCollections.observableArrayList(
                        "Todos los prestamos no Devueltos",
                        "Todos los prestamos",
                        "Prestamos Vencidos"
                );
        comboBoxFiltro.setItems(filtros);


        /**/
 /*Seteo de la columna Nombre Libro*/
        JFXTreeTableColumn<VerificarPrestamosController.Loan, String> nombreLibro = new JFXTreeTableColumn<>("Libro");
        nombreLibro.setPrefWidth(256);
        nombreLibro.setCellValueFactory((param) -> {
            return param.getValue().getValue().libroNombre;
        });
        /**/

 /*Seteo de la columna Nombre Persona*/
        JFXTreeTableColumn<VerificarPrestamosController.Loan, String> nombrePersona = new JFXTreeTableColumn<>("Persona");
        nombrePersona.setPrefWidth(256);
        nombrePersona.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombrePersona;
        });
        /**/

 /*Seteo de la columna Fecha Inicial*/
        JFXTreeTableColumn<VerificarPrestamosController.Loan, String> fechaInicial = new JFXTreeTableColumn<>("Fecha Inicial");
        fechaInicial.setPrefWidth(256);
        fechaInicial.setCellValueFactory((param) -> {
            return param.getValue().getValue().fechaDesde;
        });
        /**/

 /*Seteo de la columna Fecha Final*/
        JFXTreeTableColumn<VerificarPrestamosController.Loan, String> fechaFinal = new JFXTreeTableColumn<>("Fecha Final");
        fechaFinal.setPrefWidth(256);
        fechaFinal.setCellValueFactory((param) -> {
            return param.getValue().getValue().fechaHasta;
        });
        /**/
 /*Seteo de la columna Devuelto*/
        JFXTreeTableColumn<VerificarPrestamosController.Loan, String> devuelto = new JFXTreeTableColumn<>("Devuelto");
        devuelto.setPrefWidth(256);
        devuelto.setCellValueFactory((param) -> {
            return param.getValue().getValue().devuelto;
        });
        /**/

 /*Seteo de elementos en la Tabla*/
        ObservableList<VerificarPrestamosController.Loan> prestamosOList = FXCollections.observableArrayList();
        List list;
        try {
            if (Contexto.prestamosNoDevueltos && !Contexto.prestamosVencidos) {
                list = prestamoBO.obtenerPrestamosNoDevueltos();
            } else if (!Contexto.prestamosNoDevueltos && Contexto.prestamosVencidos) {
                list = prestamoBO.obtenerPrestamosVencidos();
            } else {
                list = prestamoBO.obtenerTodos();
            }
            Prestamo prestamo;
            for (int i = 0; i < list.size(); i++) {
                prestamo = (Prestamo) list.get(i);
                /*Carga en una ObservableList los Libros*/
                prestamosOList.add(new VerificarPrestamosController.Loan(prestamo.getNombreLibro(), prestamo.getNombrePersona(), prestamo.getFechaDesde(), prestamo.getFechaHasta(), prestamo.getIdLibro(), prestamo.getIdPersona(), prestamo.getDevuelto()));

            }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<VerificarPrestamosController.Loan> root = new RecursiveTreeItem<VerificarPrestamosController.Loan>(prestamosOList, RecursiveTreeObject::getChildren);
        tablePrestamos.getColumns().setAll(nombreLibro, nombrePersona, fechaInicial, fechaFinal, devuelto);
        tablePrestamos.setRoot(root);
        tablePrestamos.setShowRoot(false);

        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tablePrestamos.setPredicate(new Predicate<TreeItem<VerificarPrestamosController.Loan>>() {
                    @Override
                    public boolean test(TreeItem<VerificarPrestamosController.Loan> t) {
                        Boolean flag = t.getValue().nombrePersona.getValue().contains(newValue) || t.getValue().libroNombre.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        /**/
    }

    @FXML
    private void btnAnularAction(ActionEvent event) throws Exception {
        Loan loan = tablePrestamos.getSelectionModel().getSelectedItem().getValue();
        if (loan.devuelto.getValue().equalsIgnoreCase("NO")) {
            prestamoBO.anularPrestamo(loan.idLibro.getValue(), loan.idPersona.getValue(), loan.fechaDesde.getValue());
            recargar();
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            String mensaje= "Libro anteriormente devuelto";
            notificar(img, mensaje);
        }
    }

    private void notificar(Image img, String mensaje) {
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text(mensaje);
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        notificacion.show();
    }

    @FXML
    private void btnDevolucionAction(ActionEvent event) throws IOException, Exception {
        Loan loan = tablePrestamos.getSelectionModel().getSelectedItem().getValue();
        if (loan.devuelto.getValue().equalsIgnoreCase("NO")) {
            prestamoBO.marcarDevuelto(loan.idLibro.getValue(), loan.idPersona.getValue(), loan.fechaDesde.getValue());
            recargar();
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            String mensaje=  "Libro anteriormente devuelto";
            notificar(img, mensaje);
        }
    }

    private void recargar() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarPrestamos.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void filtrarAction(ActionEvent event) throws IOException {
        if (comboBoxFiltro.getValue().equalsIgnoreCase("Todos los prestamos no Devueltos")) {
            Contexto.prestamosNoDevueltos = true;
            Contexto.prestamosVencidos= false;
        } else if (comboBoxFiltro.getValue().equalsIgnoreCase("Prestamos Vencidos")) {
            Contexto.prestamosNoDevueltos = false;
            Contexto.prestamosVencidos= true;
        } else {
            Contexto.prestamosNoDevueltos = false;
            Contexto.prestamosVencidos= false;
        }
        recargar();
    }

    private static class Loan extends RecursiveTreeObject<Loan> {

        StringProperty libroNombre;
        StringProperty fechaDesde;
        StringProperty fechaHasta;
        StringProperty nombrePersona;
        StringProperty idLibro;
        StringProperty idPersona;
        StringProperty devuelto;

        public Loan(String libroNombre, String nombrePersona, Date fechaDesde, Date fechaHasta, Integer idLibro, String idPersona, Integer devuelto) {
            this.libroNombre = new SimpleStringProperty(libroNombre);
            this.nombrePersona = new SimpleStringProperty(nombrePersona);
            this.fechaDesde = new SimpleStringProperty(fechaDesde.toString());
            this.fechaHasta = new SimpleStringProperty(fechaHasta.toString());
            this.idLibro = new SimpleStringProperty(idLibro.toString());
            this.idPersona = new SimpleStringProperty(idPersona);
            if (devuelto == 1) {
                this.devuelto = new SimpleStringProperty("SI");
            } else {
                this.devuelto = new SimpleStringProperty("NO");
            }
        }

    }

}
