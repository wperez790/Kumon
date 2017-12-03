/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.LibroBO;
import org.kumon.business.PrestamoBO;
import org.kumon.main.Contexto;
import org.kumon.model.Libro;
import org.kumon.model.Prestamo;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class PrestamoLibrosController implements Initializable {
//AUX

    private Notifications notificacion;
    //
    @FXML
    private JFXButton btnPrestar;
    @FXML
    private JFXButton btnInformacion;
    @FXML
    private JFXButton btnBorrar;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXTreeTableView<Book> jfxTable;
    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private JFXButton btnAgregarLibro;
    @FXML
    private JFXButton btnMas;
    @FXML
    private JFXButton btnMenos;

    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXDatePicker datePickerHasta;
    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXButton btnVerificarPrestamos;
    //AUX
    private LibroBO libroBO;
    private PrestamoBO prestamoBO;
    //

    public PrestamoLibrosController() {
        libroBO = Contexto.construirLibroBO();
        prestamoBO = Contexto.construirPrestamoBO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Seteo de la columna Nombre Libro*/
        JFXTreeTableColumn<PrestamoLibrosController.Book, String> nombre = new JFXTreeTableColumn<>("Libro");
        nombre.setPrefWidth(200);
        nombre.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombre;
        });
        /**/

 /*Seteo de la columna Stock*/
        JFXTreeTableColumn<PrestamoLibrosController.Book, String> stock = new JFXTreeTableColumn<>("Stock");
        stock.setPrefWidth(120);
        stock.setCellValueFactory((param) -> {
            return param.getValue().getValue().stock;
        });
        /**/

 /*Seteo de elementos en la Tabla*/
        ObservableList<PrestamoLibrosController.Book> librosOList = FXCollections.observableArrayList();
        try {
            List list = libroBO.obtenerTodos();
            Libro libro;
            for (int i = 0; i < list.size(); i++) {
                libro = (Libro) list.get(i);
                /*Carga en una ObservableList los Libros*/
                librosOList.add(new PrestamoLibrosController.Book(libro.getIdLibro(), libro.getNombre(), libro.getStock()));

            }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<PrestamoLibrosController.Book> root = new RecursiveTreeItem<PrestamoLibrosController.Book>(librosOList, RecursiveTreeObject::getChildren);
        jfxTable.getColumns().setAll(nombre, stock);
        jfxTable.setRoot(root);
        jfxTable.setShowRoot(false);

        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                jfxTable.setPredicate(new Predicate<TreeItem<PrestamoLibrosController.Book>>() {
                    @Override
                    public boolean test(TreeItem<PrestamoLibrosController.Book> t) {
                        Boolean flag = t.getValue().stock.getValue().contains(newValue) || t.getValue().nombre.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        /**/

    }

    @FXML
    private void btnPrestarAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
        String idPersona = textFieldID.getText();
        java.sql.Date date = java.sql.Date.valueOf(datePickerHasta.getValue());
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Prestamo prestamo = new Prestamo(); //Variable auxiliar para definir el prestamo.
        prestamo.setIdPersona(idPersona);
        prestamo.setIdLibro(Integer.parseInt(book.id.getValue()));
        prestamo.setFechaDesde(sqlDate);
        prestamo.setFechaHasta(date);
        prestamoBO.registrarPrestamo(prestamo);
        Image img = new Image("/org/kumon/presentation/img/ok.png");
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text("Registrado con Exito");
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        recargar();
        notificacion.show();

    }

    private void recargar() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnInformacionAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
        Contexto.abrirVerLibro();
    }

    @FXML
    private void btnAgregarAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/AgregarLibro.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBorrarAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar Eliminar", ButtonType.OK, ButtonType.CANCEL);
        alertBox.setContentText("Esta seguro que desea eliminar este libro?");
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.initOwner(Contexto.primaryStage);
        alertBox.showAndWait();
        if (alertBox.getResult() == ButtonType.OK) {
            Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
            libroBO.borrarLibro(Contexto.libro.getIdLibro());
            recargar();
        } else {
            alertBox.close();
        }

    }

    @FXML
    private void btnModificarAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
        Contexto.abrirModificarLibro();
    }

    @FXML
    private void btnMasStockAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
        libroBO.modificarStockLibro(Contexto.libro.getStock() + 1, Contexto.libro.getIdLibro());
        recargar();
    }

    @FXML
    private void btnMenosAction(ActionEvent event) throws Exception {
        Book book = jfxTable.getSelectionModel().getSelectedItem().getValue();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(book.id.getValue()));
        int cantidad = Contexto.libro.getStock() - 1;
        libroBO.modificarStockLibro(cantidad, Contexto.libro.getIdLibro());
        recargar();
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnVerificarPrestamos(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarPrestamos.fxml"));
        Contexto.splitPane.getItems().set(0, pane);

    }

    /*Clase auxiliar Persona para setear datos en la tabla*/
    private static class Book extends RecursiveTreeObject<Book> {

        StringProperty stock;
        StringProperty nombre;
        StringProperty id;

        public Book(Integer id, String nombre, Integer stock) {
            if (stock < 10) {
                this.stock = new SimpleStringProperty("0" + stock + "");
            } else {
                this.stock = new SimpleStringProperty("" + stock + "");
            }
            this.nombre = new SimpleStringProperty(nombre);
            this.id = new SimpleStringProperty("" + id + "");
        }
    }
    /**/
}
