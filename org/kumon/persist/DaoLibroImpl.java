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
import org.kumon.model.Libro;
import org.kumon.persist.interfaces.ILibro;

/**
 *
 * @author walt
 */
public class DaoLibroImpl extends Conexion implements ILibro{
    
    
    @Override
    public List obtenerTodos() throws Exception{
       
        List lista = new ArrayList();
        Libro libro = new Libro();
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT *"
                    + " FROM Libros order by nombreLibro;");
            /* Recorre el Result set y setea el libro con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                libro = new Libro();
                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setNombre(rs.getString("nombreLibro"));
                libro.setStock(rs.getInt("stock"));
                libro.setAutor(rs.getString("autor"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setEditorial(rs.getString("editorial"));
                lista.add(libro);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;

    }

    @Override
    public void nuevoLibro(Libro libro) throws Exception {
         this.conectar();
        try {
                PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Libros(nombreLibro,editorial,autor,descripcion,stock)"
                        + " Values(?,?,?,?,?)");
                st.setString(1, libro.getNombre());
                st.setString(2, libro.getEditorial());
                st.setString(3, libro.getAutor());
                st.setString(4, libro.getDescripcion());
                st.setInt(5, libro.getStock());
                st.executeUpdate();
    
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }

    @Override
    public void modificarStockLibro(int cantidad, int idLibro) throws Exception {
        this.conectar();
        try {
                PreparedStatement st = this.conexion.prepareStatement("UPDATE Libros SET stock = ? WHERE idLibro ="+idLibro+";");
                st.setInt(1, cantidad);
                st.executeUpdate();
    
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }

    
    @Override
    public void modificarLibro(Libro libro) throws Exception {
         this.conectar();
         int idLibro = libro.getIdLibro();
        try {
                PreparedStatement st = this.conexion.prepareStatement("UPDATE Libros SET nombreLibro = ?, editorial = ?,autor = ?,descripcion = ?,stock = ?"
                        + " WHERE idLibro = "+idLibro+";");
                st.setString(1, libro.getNombre());
                st.setString(2, libro.getEditorial());
                st.setString(3, libro.getAutor());
                st.setString(4, libro.getDescripcion());
                st.setInt(5, libro.getStock());
                st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }

    @Override
    public void borrarLibro(int idLibro) throws Exception {
          this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Libros"
                    + " WHERE idLibro = "+idLibro+";");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
    }

    public Libro getLibroByID(Integer id) throws Exception {
         Libro aux = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idLibro, nombreLibro, editorial, autor, descripcion, stock"
                    + " FROM Libros where idLibro = " + id + " ;");

            while (rs.next()) {
                aux = new Libro();
                aux.setIdLibro(rs.getInt("idLibro"));
                aux.setNombre(rs.getString("nombreLibro"));
                aux.setEditorial(rs.getString("editorial"));
                aux.setAutor(rs.getString("autor"));
                aux.setDescripcion(rs.getString("descripcion"));
                aux.setStock(rs.getInt("stock"));
             

            }
            this.cerrar();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        return aux;
    }
    
}
   


