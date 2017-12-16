/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import org.kumon.business.PagosBO;
import org.kumon.main.Contexto;
import org.kumon.model.Pago;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class VerificarPagosController implements Initializable {

    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXTreeTableView<Payment> tablePagos;
    //AUX
    PagosBO pagosBO;

    //

    public VerificarPagosController() {
        pagosBO = Contexto.construirPagosBO();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        /*Seteo de la columna idPago*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> idPago = new JFXTreeTableColumn<>("IdPago");
        idPago.setPrefWidth(213);
        idPago.setCellValueFactory((param) -> {
            return param.getValue().getValue().idPago;
        });
        /**/
 /*Seteo de la columna Nombre*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> nombrePersona = new JFXTreeTableColumn<>("Nombre");
        nombrePersona.setPrefWidth(213);
        nombrePersona.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombrePersona;
        });
        /**/
 /*Seteo de la columna idAlumno/DNI*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> idAlumno = new JFXTreeTableColumn<>("DNI");
        idAlumno.setPrefWidth(213);
        idAlumno.setCellValueFactory((param) -> {
            return param.getValue().getValue().idAlumno;
        });
        /**/
 /*Seteo de la columna Fecha*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> fecha = new JFXTreeTableColumn<>("Fecha");
        fecha.setPrefWidth(213);
        fecha.setCellValueFactory((param) -> {
            return param.getValue().getValue().fecha;
        });
        /**/
 /*Seteo de la columna Monto*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> monto = new JFXTreeTableColumn<>("Monto");
        monto.setPrefWidth(213);
        monto.setCellValueFactory((param) -> {
            return param.getValue().getValue().monto;
        });
        /**/
 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarPagosController.Payment, String> idDeuda = new JFXTreeTableColumn<>("IdDeuda");
        idDeuda.setPrefWidth(213);
        idDeuda.setCellValueFactory((param) -> {
            return param.getValue().getValue().idDeuda;
        });
        /**/
 /*Seteo los elementos en la tabla*/
        ObservableList<VerificarPagosController.Payment> pagosOList = FXCollections.observableArrayList();
        List list;
        try {
            Pago pago;
            list = pagosBO.obtenerTodos();
            for (int i = 0; i < list.size(); i++) {
                pago = (Pago) list.get(i);
                /*Carga en una ObservableList los Libros*/
                pagosOList.add(new VerificarPagosController.Payment(pago.getIdPago(), pago.getNombrePersona(), pago.getIdAlumno(), pago.getFecha(), pago.getMonto(), pago.getIdDeuda()));

            }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<VerificarPagosController.Payment> root = new RecursiveTreeItem<VerificarPagosController.Payment>(pagosOList, RecursiveTreeObject::getChildren);
        tablePagos.getColumns().setAll(idPago, nombrePersona, idAlumno, fecha, monto, idDeuda);
        tablePagos.setRoot(root);
        tablePagos.setShowRoot(false);

        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tablePagos.setPredicate(new Predicate<TreeItem<VerificarPagosController.Payment>>() {
                    @Override
                    public boolean test(TreeItem<VerificarPagosController.Payment> t) {
                        Boolean flag = t.getValue().nombrePersona.getValue().contains(newValue) || t.getValue().idDeuda.getValue().contains(newValue) || t.getValue().idAlumno.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        /**/
    }

    @FXML
    private void btnAnularAction(ActionEvent event) throws Exception {
        Payment pay = tablePagos.getSelectionModel().getSelectedItem().getValue();
        pagosBO.anularPago(Integer.parseInt(pay.idPago.getValue()));
        recargar();
    }

    private void recargar() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarPagos.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Pagos.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    private static class Payment extends RecursiveTreeObject<Payment> {

        StringProperty idPago;
        StringProperty nombrePersona;
        StringProperty fecha;
        StringProperty monto;
        StringProperty idDeuda;
        StringProperty idAlumno;

        public Payment(Integer idPago, String nombrePersona, String idAlumno, Date fecha, Double monto, Integer idDeuda) {
            this.idPago = new SimpleStringProperty(idPago.toString());
            this.nombrePersona = new SimpleStringProperty(nombrePersona);
            this.idAlumno = new SimpleStringProperty(idAlumno);
            this.fecha = new SimpleStringProperty(fecha.toString());
            this.monto = new SimpleStringProperty(monto.toString());
            this.idDeuda = new SimpleStringProperty(idDeuda.toString());
        }

    }

}
