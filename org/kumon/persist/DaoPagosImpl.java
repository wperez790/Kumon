/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.kumon.model.Pago;
import org.kumon.persist.interfaces.IPagos;

/**
 *
 * @author walt
 */
public class DaoPagosImpl extends Conexion implements IPagos {

    @Override
    public void registrarPago(Pago pagos) throws Exception {
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Pagos(monto, fecha, idDeuda)"
                    + " Values(?,?,?)");
            st.setDouble(1, pagos.getMonto());
            st.setDate(2, pagos.getFecha());
            st.setDouble(3, pagos.getIdDeuda());
            
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
  
    }

    @Override
    public void anularPago(Integer idPago) throws Exception {
         this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Pagos"
                    + " WHERE idPago = " + idPago +";");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
    }

    @Override
    public List obtenerTodos() throws Exception {
         List lista = new ArrayList();
        Pago pago;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idPago, nombrePersona, fecha, pa.monto, pa.idDeuda, d.idAlumno FROM Pagos pa "
                    + "INNER JOIN Deudas d INNER JOIN Alumnos a INNER JOIN Personas p "
                    + "WHERE pa.idDeuda = d.idDeuda AND d.idAlumno = a.idAlumno AND a.idAlumno = idPersona order by fecha;");
            /* Recorre el Result set y setea el pago con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("idPago"));
                pago.setFecha(rs.getDate("fecha"));
                pago.setIdDeuda(rs.getInt("idDeuda"));
                pago.setMonto(rs.getDouble("monto"));
                pago.setNombrePersona(rs.getString("nombrePersona"));
                pago.setIdAlumno(rs.getString("idAlumno"));
                lista.add(pago);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;
    }

    public Pago obtenerPagoById(Integer idPago) throws Exception {
       Pago pago = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idPago, monto, fecha, idDeuda "
                    + " FROM Pagos "
                    + " WHERE  idPago = "+ idPago+";");
                   /* Recorre el Result set y setea el pago con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("idPago"));
                pago.setFecha(rs.getDate("fecha"));
                pago.setIdDeuda(rs.getInt("idDeuda"));
                pago.setMonto(rs.getDouble("monto"));
             
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();
        return pago;
    
    }

}
