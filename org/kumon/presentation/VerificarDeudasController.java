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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import org.kumon.business.DeudaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Deuda;
import org.kumon.model.Prestamo;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class VerificarDeudasController implements Initializable {

    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private JFXButton btnAnular;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXComboBox<String> comboBoxFiltro;
    @FXML
    private JFXTreeTableView<Debt> tableDeudas;
    //AUX
    DeudaBO deudaBO = Contexto.construirDeudaBO();
    @FXML
    private JFXButton btnPagar;
    @FXML
    private Label deudasLabel;

    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           if(Contexto.SeteoPago){
               deudasLabel.setText("Elija la Deuda a Pagar");
               btnAnular.setVisible(false);
              
        }
        /*Seteo comboBox*/
        ObservableList<String> filtros
                = FXCollections.observableArrayList(
                        "Deudas Vencidas",
                        "Deudas a Vencer",
                        "Todas las Deudas"
                );
        comboBoxFiltro.setItems(filtros);

        /**/
 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> idDeuda = new JFXTreeTableColumn<>("idDeuda");
        idDeuda.setPrefWidth(182);
        idDeuda.setCellValueFactory((param) -> {
            return param.getValue().getValue().idDeuda;
        });
        /**/

 /*Seteo de la columna idAsignatura*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> idAsignatura = new JFXTreeTableColumn<>("Asignatura");
        idAsignatura.setPrefWidth(182);
        idAsignatura.setCellValueFactory((param) -> {
            return param.getValue().getValue().idAsignatura;
        });
        /**/

 /*Seteo de la columna DNI*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> idAlumno = new JFXTreeTableColumn<>("DNI");
        idAlumno.setPrefWidth(182);
        idAlumno.setCellValueFactory((param) -> {
            return param.getValue().getValue().idAlumno;
        });
        /**/

 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> nombrePersona = new JFXTreeTableColumn<>("Nombre");
        nombrePersona.setPrefWidth(182);
        nombrePersona.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombrePersona;
        });
        /**/
 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> montoT = new JFXTreeTableColumn<>("Monto Total");
        montoT.setPrefWidth(182);
        montoT.setCellValueFactory((param) -> {
            return param.getValue().getValue().montoTotal;
        });
        /**/
 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> montoA = new JFXTreeTableColumn<>("Monto Adeudado");
        montoA.setPrefWidth(182);
        montoA.setCellValueFactory((param) -> {
            return param.getValue().getValue().montoAdeudado;
        });
        /**/
 /*Seteo de la columna idDeuda*/
        JFXTreeTableColumn<VerificarDeudasController.Debt, String> vencimiento = new JFXTreeTableColumn<>("Vencimiento");
        vencimiento.setPrefWidth(182);
        vencimiento.setCellValueFactory((param) -> {
            return param.getValue().getValue().vencimiento;
        });
        /**/

 /*Seteo de elementos en la Tabla*/
        ObservableList<VerificarDeudasController.Debt> deudasOList = FXCollections.observableArrayList();
        List list;
        try {
            if (Contexto.tipoDeuda == 1) {
                list = deudaBO.obtenerDeudasVencidas();
            } else if (Contexto.tipoDeuda == 2) {
                list = deudaBO.obtenerDeudasAVencer();
            } else {
                list = deudaBO.obtenerTodos();
            }

            Deuda deuda;
            for (int i = 0; i < list.size(); i++) {
                deuda = (Deuda) list.get(i);
                /*Carga en una ObservableList los Libros*/
                deudasOList.add(new VerificarDeudasController.Debt(deuda.getIdDeuda(), deuda.getIdAsignatura(), deuda.getIdAlumno(), deuda.getNombreAlumno(), deuda.getMonto(), deuda.getMontoAdeudado(), deuda.getVencimiento()));

            }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<VerificarDeudasController.Debt> root = new RecursiveTreeItem<VerificarDeudasController.Debt>(deudasOList, RecursiveTreeObject::getChildren);
        tableDeudas.getColumns().setAll(idDeuda, idAsignatura, idAlumno, nombrePersona, montoT, montoA, vencimiento);
        tableDeudas.setRoot(root);
        tableDeudas.setShowRoot(false);

        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableDeudas.setPredicate(new Predicate<TreeItem<VerificarDeudasController.Debt>>() {
                    @Override
                    public boolean test(TreeItem<VerificarDeudasController.Debt> t) {
                        Boolean flag = t.getValue().nombrePersona.getValue().contains(newValue) || t.getValue().idDeuda.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        /**/
    }

    @FXML
    private void btnAnularAction(ActionEvent event) throws Exception {
        Debt debt = tableDeudas.getSelectionModel().getSelectedItem().getValue();
        deudaBO.anularDeuda(Integer.parseInt(debt.idDeuda.getValue()));
        recargar();
    }

    private void recargar() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarDeudas.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Pagos.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void filtrarAction(ActionEvent event) throws IOException {

        if (comboBoxFiltro.getValue().equalsIgnoreCase("Deudas Vencidas")) {
            Contexto.tipoDeuda = 1;
        } else if (comboBoxFiltro.getValue().equalsIgnoreCase("Deudas a Vencer")) {
            Contexto.tipoDeuda = 2;
        } else {
            Contexto.tipoDeuda = 0;
        }
        recargar();
    }

    @FXML
    private void btnPagarAction(ActionEvent event) {
    }

    private static class Debt extends RecursiveTreeObject<Debt> {

        StringProperty idDeuda;
        StringProperty idAsignatura;
        StringProperty idAlumno;
        StringProperty nombrePersona;
        StringProperty montoTotal;
        StringProperty montoAdeudado;
        StringProperty vencimiento;

        public Debt(Integer idDeuda, Integer idAsignatura, String idAlumno, String nombrePersona, Double montoTotal, Double montoAdeudado, Date vencimiento) {
            this.idDeuda = new SimpleStringProperty(idDeuda.toString());
            this.idAsignatura = new SimpleStringProperty(idAsignatura.toString());
            this.idAlumno = new SimpleStringProperty(idAlumno);
            this.nombrePersona = new SimpleStringProperty(nombrePersona);
            this.montoTotal = new SimpleStringProperty(montoTotal.toString());
            this.montoAdeudado = new SimpleStringProperty(montoAdeudado.toString());
            this.vencimiento = new SimpleStringProperty(vencimiento.toString());
        }

    }

}
