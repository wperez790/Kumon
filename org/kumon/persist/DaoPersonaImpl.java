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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;
import org.kumon.persist.interfaces.IPersona;

/**
 *
 * @author Walter
 */
public class DaoPersonaImpl extends Conexion implements IPersona {

    @Override
    public void registrar(Persona persona) throws Exception {
        this.conectar();
        try {
            if (Contexto.tipoUser < 3) {
                PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Personas(idPersona,nombrePersona,apellido,dni,telefono,fechaNacimiento,domicilio,user,pass,sexo,activo,edad,tipoUser,email)"
                        + " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                st.setString(1, persona.getIdPersona());
                st.setString(2, persona.getNombre());
                st.setString(3, persona.getApellido());
                st.setInt(4, persona.getDni());
                st.setString(5, persona.getTelefono());
                st.setDate(6, persona.getFechaNacimiento());
                st.setString(7, persona.getDomicilio());
                st.setString(8, persona.getUser());
                st.setString(9, persona.getPass());
                st.setString(10, persona.getSexo());
                st.setInt(11, persona.getActivo());
                st.setInt(12, persona.getEdad());
                st.setInt(13, persona.getTipoUser());
                st.setString(14, persona.getEmail());
                st.executeUpdate();
                if (!persona.getNombreImg().isEmpty()) {
                    st = this.conexion.prepareStatement("INSERT INTO Personas(idPersona, nombreImg)"
                            + " Values(?,?)");
                    st.setString(1, persona.getIdPersona());
                    st.setString(2, persona.getNombreImg());
                    st.executeUpdate();
                }
            } else {
                PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Personas(idPersona,nombrePersona,apellido,dni,telefono,fechaNacimiento,domicilio,sexo,activo,edad,tipoUser,info,email)"
                        + " Values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                st.setString(1, persona.getIdPersona());
                st.setString(2, persona.getNombre());
                st.setString(3, persona.getApellido());
                st.setInt(4, persona.getDni());
                st.setString(5, persona.getTelefono());
                st.setDate(6, persona.getFechaNacimiento());
                st.setString(7, persona.getDomicilio());
                st.setString(8, persona.getSexo());
                st.setInt(9, persona.getActivo());
                st.setInt(10, persona.getEdad());
                st.setInt(11, persona.getTipoUser());
                st.setString(12, persona.getInfo());
                st.setString(13, persona.getEmail());
                st.executeUpdate();
                if (!persona.getNombreImg().isEmpty()) {
                    st = this.conexion.prepareStatement("INSERT INTO Personas(idPersona, nombreImg)"
                            + " Values(?,?)");
                    st.setString(1, persona.getIdPersona());
                    st.setString(2, persona.getNombreImg());
                    st.executeUpdate();
                }
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {

        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("UPDATE Personas SET nombrePersona = ?, apellido = ?, dni = ?, telefono = ?,"
                    + "fechaNacimiento = ?, domicilio = ?, sexo = ?, activo = ?, nombreImg=?, edad = ?, email = ?, info = ?"
                    + " WHERE idPersona = '" + persona.getIdPersona() + "';");
            st.setString(1, persona.getNombre());
            st.setString(2, persona.getApellido());
            st.setInt(3, persona.getDni());
            st.setString(4, persona.getTelefono());
            st.setDate(5, persona.getFechaNacimiento());
            st.setString(6, persona.getDomicilio());
            st.setString(7, persona.getSexo());
            st.setInt(8, persona.getActivo());
            st.setString(9, persona.getNombreImg());
            st.setInt(10, persona.getEdad());
            st.setString(11, persona.getEmail());
            st.setString(12, persona.getInfo());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(Persona persona) throws Exception {
        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Personas"
                    + " WHERE idPersona = '" + persona.getIdPersona() + "';");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Persona> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona obtenerPersonaByUser(String user) throws Exception {
        Persona aux = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idPersona, nombrePersona, apellido, dni, telefono, fechaNacimiento, domicilio, user, pass, sexo, tipoUser, nombreImg"
                    + " FROM Personas where user = '" + user + "' and activo = " + 1 + ";");

            while (rs.next()) {
                aux = new Persona();
                aux.setIdPersona(rs.getString("idPersona"));
                aux.setNombre(rs.getString("nombrePersona"));
                aux.setApellido(rs.getString("apellido"));
                aux.setDni(rs.getInt("dni"));
                aux.setTelefono(rs.getString("telefono"));
                aux.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                aux.setDomicilio(rs.getString("domicilio"));
                aux.setPass(rs.getString("pass"));
                aux.setUser(rs.getString("user"));
                aux.setSexo(rs.getString("sexo"));
                aux.setTipoUser(rs.getInt("tipoUser"));
                aux.setNombreImg(rs.getString("nombreImg"));
            }
          

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        return aux;
    }

    @Override
    public boolean comprobarUser(String user) throws Exception {
        try {
            this.conectar();

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT user FROM Personas");
            while (rs.next()) {
                if (rs.getString("user").equals(user)) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        return true;
    }

    /*Funcion que guarda la información de login si las checkboxes estan seleccionadas*/
    public void registrarLogin(String user, String pass) throws Exception {
        try {
            PreparedStatement st;
            this.conectar();
            if (!user.equals("no")) {
                st = this.conexion.prepareStatement("UPDATE Sistema SET user="
                        + " ?");
                st.setString(1, user);
            }
            if (!pass.equals("no")) {
                st = this.conexion.prepareStatement("UPDATE Sistema SET pass="
                        + " ?");
                st.setString(1, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String obtenerPassSistema() {
        String pass = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT pass FROM Sistema");
            pass = rs.getString("pass");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

    public String obtenerUserSistema() {
        String user = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT user FROM Sistema");
            user = rs.getString("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Persona buscarById(Integer id) throws Exception {

        Persona aux = null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idPersona, nombrePersona, apellido, dni, telefono, fechaNacimiento, domicilio, user, pass, sexo, tipoUser, nombreImg, activo, edad , email, info"
                    + " FROM Personas where idPersona = '" + id + "' ;");

            while (rs.next()) {
                aux = new Persona();
                aux.setIdPersona(rs.getString("idPersona"));
                aux.setNombre(rs.getString("nombrePersona"));
                aux.setApellido(rs.getString("apellido"));
                aux.setDni(rs.getInt("dni"));
                aux.setTelefono(rs.getString("telefono"));
                aux.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                aux.setDomicilio(rs.getString("domicilio"));
                aux.setPass(rs.getString("pass"));
                aux.setUser(rs.getString("user"));
                aux.setSexo(rs.getString("sexo"));
                aux.setTipoUser(rs.getInt("tipoUser"));
                aux.setNombreImg(rs.getString("nombreImg"));
                aux.setActivo(rs.getInt("activo"));
                aux.setEdad(rs.getInt("edad"));
                aux.setEmail(rs.getString("email"));
                aux.setInfo(rs.getString("info"));

            }
            this.cerrar();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        return aux;
    }

    @Override
    public Persona buscarByName(String name) throws Exception {
        return null;

    }

    public Map<Integer, String> obtenerTodos() throws Exception {

        Map<Integer, String> aux = new HashMap<>();
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT dni, nombrePersona, apellido"
                    + " FROM Personas order by nombrePersona, apellido;");
            /* Recorre el Result set y setea el mapa con idPersona como key y nombre - apellido como value*/
            while (rs.next()) {
                aux.put(rs.getInt("dni"), rs.getString("nombrePersona") + " " + rs.getString("apellido"));

            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return aux;

    }

    public void setInactivo(Persona persona) throws Exception {

        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("UPDATE Personas SET activo = ?"
                    + " WHERE idPersona = '" + persona.getIdPersona() + "';");
            st.setInt(1, 0);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setActivo(Persona persona) throws Exception {

        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("UPDATE Personas SET activo = ?"
                    + " WHERE idPersona = '" + persona.getIdPersona() + "';");
            st.setInt(1, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
