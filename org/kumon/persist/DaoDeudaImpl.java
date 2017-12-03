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
import org.kumon.model.Deuda;
import org.kumon.model.Pago;
import org.kumon.persist.interfaces.IDeudas;

/**
 *
 * @author walt
 */
public class DaoDeudaImpl extends Conexion implements IDeudas{

    @Override
    public void registrarDeuda(Deuda deuda) throws Exception {
       this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Deudas(idAsignatura,idAlumno,vencimiento,monto,montoAdeudado)"
                    + " Values(?,?,?,?,?)");
            st.setInt(1, deuda.getIdAsignatura());
            st.setString(2, deuda.getIdAlumno());
            st.setDate(3, deuda.getVencimiento());
            st.setDouble(4, deuda.getMonto());
            st.setDouble(5, deuda.getMonto());
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
    }
    

    @Override
    public void anularDeuda(Integer idDeuda) throws Exception {
         this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Deudas"
                    + " WHERE idDeuda = " + idDeuda +";");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
        
    }

    @Override
    public List obtenerDeudasVencidas() throws Exception {
        List lista = new ArrayList();
        Deuda deuda;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idDeuda, idAsignatura, idAlumno, monto, montoAdeudado, vencimiento, nombrePersona"
                    + " FROM Deudas d inner join Alumnos inner join Personas p WHERE d.idAlumno = p.idPersona and vencimiento <= CURDATE() ;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                deuda = new Deuda();
                deuda.setIdAsignatura(rs.getInt("idAsignatura"));
                deuda.setMonto(rs.getDouble("monto"));
                deuda.setMontoAdeudado(rs.getDouble("montoAdeudado"));
                deuda.setVencimiento(rs.getDate("vencimiento"));
                deuda.setIdAlumno(rs.getString("idAlumno"));
                deuda.setIdDeuda(rs.getInt("idDeuda"));
                deuda.setNombreAlumno(rs.getString("nombrePersona"));
                lista.add(deuda);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerDeudasVencidas() error");
        }
        this.cerrar();

        return lista;

    }
    

    @Override
    public List obtenerDeudasAVencer() throws Exception {
        List lista = new ArrayList();
        Deuda deuda;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT d.idDeuda, d.idAsignatura, d.idAlumno, d.monto, d.montoAdeudado,d.vencimiento, p.nombrePersona"
                    + " FROM Deudas d inner join Alumnos inner join Personas p WHERE d.idAlumno = p.idPersona and d.vencimiento >= CURDATE() ;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                deuda = new Deuda();
                deuda.setIdAsignatura(rs.getInt("idAsignatura"));
                deuda.setMonto(rs.getDouble("monto"));
                deuda.setMontoAdeudado(rs.getDouble("montoAdeudado"));
                deuda.setVencimiento(rs.getDate("vencimiento"));
                deuda.setIdAlumno(rs.getString("idAlumno"));
                deuda.setIdDeuda(rs.getInt("idDeuda"));
                deuda.setNombreAlumno(rs.getString("nombrePersona"));
                lista.add(deuda);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerDeudasAVencer() error");
        }
        this.cerrar();

        return lista;
    }

    @Override
    public List obtenerTodos() throws Exception {
       List lista = new ArrayList();
        Deuda deuda;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT d.idDeuda, d.idAsignatura, d.idAlumno, d.monto,d.montoAdeudado, d.vencimiento, p.nombrePersona"
                    + " FROM Deudas d inner join Alumnos inner join Personas p WHERE d.idAlumno = p.idPersona ;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                deuda = new Deuda();
                deuda.setIdAsignatura(rs.getInt("idAsignatura"));
                deuda.setMonto(rs.getDouble("monto"));
                deuda.setMontoAdeudado(rs.getDouble("montoAdeudado"));
                deuda.setVencimiento(rs.getDate("vencimiento"));
                deuda.setIdAlumno(rs.getString("idAlumno"));
                deuda.setIdDeuda(rs.getInt("idDeuda"));
                deuda.setNombreAlumno(rs.getString("nombrePersona"));
                lista.add(deuda);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;

    }

    public void modificarMontoAdeudado(Pago pagos) throws Exception {
        this.conectar();
        try {
                PreparedStatement st = this.conexion.prepareStatement("UPDATE Deudas SET montoAdeudado = montoAdeudado - ? WHERE idDeuda ="+pagos.getIdDeuda()+";");
                st.setDouble(1, pagos.getMonto());
                st.executeUpdate();
    
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }

    public void restaurarMontoAdeudado(Pago pago) throws Exception {
        this.conectar();
        try {
                PreparedStatement st = this.conexion.prepareStatement("UPDATE Deudas SET montoAdeudado = montoAdeudado + ? WHERE idDeuda ="+pago.getIdDeuda()+";");
                st.setDouble(1, pago.getMonto());
                st.executeUpdate();
    
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }
    
    
}
