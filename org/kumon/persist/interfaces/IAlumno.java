/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Alumno;
import org.kumon.model.RelacionFamiliarAlumno;

/**
 *
 * @author Walter
 */
public interface IAlumno {
    
     public boolean registrar(Alumno alumno) throws Exception;
    public void modificar(Alumno alumno) throws Exception;
    public void eliminar(String idAlumno) throws Exception;
    public List<Alumno> listar() throws Exception;
    
}
