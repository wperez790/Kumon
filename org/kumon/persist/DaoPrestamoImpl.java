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
import org.kumon.business.LibroBO;
import org.kumon.main.Contexto;
import org.kumon.model.Prestamo;
import org.kumon.persist.interfaces.IPrestamo;

/**
 *
 * @author walt
 */
public class DaoPrestamoImpl extends Conexion implements IPrestamo {

    @Override
    public void registrarPrestamo(Prestamo prestamo) throws Exception {
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Prestamos(idLibro,idPersona,fechaInicial,fechaFinal)"
                    + " Values(?,?,?,?)");
            st.setInt(1, prestamo.getIdLibro());
            st.setString(2, prestamo.getIdPersona());
            st.setDate(3, prestamo.getFechaDesde());
            st.setDate(4, prestamo.getFechaHasta());
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
    }

    @Override
    public void anularPrestamo(String idLibro, String idPersona, String fechaDesde) throws Exception {
        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Prestamos"
                    + " WHERE idLibro = " + idLibro + " and idPersona = " + idPersona+ " and fechaInicial = '" +fechaDesde+" 00:00:00';");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
        
        LibroBO libroBO = Contexto.construirLibroBO();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(idLibro));
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Libros SET stock = ? "
                    + " WHERE idLibro = " + idLibro +";");
            st.setInt(1, Contexto.libro.getStock()+1);

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        
    } 
    

    @Override
    public void marcarDevuelto(String idLibro, String idPersona, String fechaDesde) throws Exception {
        /*Sentencia sql para setear devuelto*/
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Prestamos SET devuelto = ? "
                    + " WHERE idLibro = " + idLibro + " and idPersona = " + idPersona + " and fechaInicial = '"+fechaDesde+ " 00:00:00';");
            st.setInt(1, 1);

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        /*Sentencia para reestablecer el stock*/
        LibroBO libroBO = Contexto.construirLibroBO();
        Contexto.libro = libroBO.getLibroByID(Integer.parseInt(idLibro));
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Libros SET stock = ? "
                    + " WHERE idLibro = " + idLibro +";");
            st.setInt(1, Contexto.libro.getStock()+1);

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        /**/
    }

    @Override
    public List obtenerPrestamosNoDevueltos() throws Exception {
        List lista = new ArrayList();
        Prestamo prestamo;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT pr.idLibro, pr.idPersona, fechaInicial, fechaFinal, devuelto, nombreLibro, nombrePersona"
                    + " FROM Prestamos pr inner join Libros l inner join Personas p  WHERE devuelto = 0 and pr.idLibro = l.idLibro and p.idPersona = pr.idPersona "
                    + "order by fechaFinal;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setIdPersona(rs.getString("idPersona"));
                prestamo.setFechaDesde(rs.getDate("fechaInicial"));
                prestamo.setFechaHasta(rs.getDate("fechaFinal"));
                prestamo.setDevuelto(rs.getInt("devuelto"));
                prestamo.setNombreLibro(rs.getString("nombreLibro"));
                prestamo.setNombrePersona(rs.getString("nombrePersona"));
                lista.add(prestamo);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodosNoDevueltos() error");
        }
        this.cerrar();
        return lista;
    }

    @Override
    public List obtenerTodos() throws Exception {

        List lista = new ArrayList();
        Prestamo prestamo;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT pr.idLibro, pr.idPersona, fechaInicial, fechaFinal, devuelto, nombreLibro, nombrePersona"
                    + " FROM Prestamos pr inner join Libros l inner join Personas p  WHERE pr.idLibro = l.idLibro and p.idPersona = pr.idPersona "
                    + "order by fechaFinal;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setIdPersona(rs.getString("idPersona"));
                prestamo.setFechaDesde(rs.getDate("fechaInicial"));
                prestamo.setFechaHasta(rs.getDate("fechaFinal"));
                prestamo.setDevuelto(rs.getInt("devuelto"));
                prestamo.setNombreLibro(rs.getString("nombreLibro"));
                prestamo.setNombrePersona(rs.getString("nombrePersona"));
                lista.add(prestamo);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;

    }

}
