/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.model;

import java.sql.Date;

/**
 *
 * @author Walter
 */
public class Persona {
    private String idPersona;
    private String nombrePersona;
    private String apellido;
    private Integer dni;
    private String telefono;
    private Date fechaNacimiento;
    private String user;
    private String pass;
    private String domicilio;
    private String sexo;
    private Integer tipoUser;
    private int activo;
    private String nombreImg;
    private int edad;
    private String info;
    private String email;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    

    public String getNombreImg() {
        return nombreImg;
    }

    public void setNombreImg(String getNombreImg) {
        this.nombreImg = getNombreImg;
    }
    
    

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(Integer tipoUser) {
        this.tipoUser = tipoUser;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
    
    

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String id) {
        this.idPersona = id;
    }

    public String getNombre() {
        return nombrePersona;
    }

    public void setNombre(String nombre) {
        this.nombrePersona = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setInfo(String text) {
        this.info = text;
    }

    public String getInfo() {
        return info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
 
    
}
