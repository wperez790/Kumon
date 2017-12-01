/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Persona;


/**
 *
 * @author Walter
 */
public interface IPersona {
    
    public void registrar(Persona persona) throws Exception;
    public void modificar(Persona persona) throws Exception;
    public void eliminar(Persona persona) throws Exception;
    public List<Persona> listar() throws Exception;
    public Persona obtenerPersonaByUser(String user)throws Exception;
    public boolean comprobarUser(String user) throws Exception;
    public Persona buscarById(Integer id) throws Exception;
    public Persona buscarByName(String name) throws Exception;
    
}
