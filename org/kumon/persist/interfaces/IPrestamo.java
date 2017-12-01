/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.sql.Date;
import java.util.List;
import org.kumon.model.Prestamo;

/**
 *
 * @author walt
 */
public interface IPrestamo {
     public void registrarPrestamo(Prestamo prestamo) throws Exception;
     public void anularPrestamo(String idLibro, String idPersona, String fechaDesde) throws Exception;
     public List obtenerPrestamosNoDevueltos() throws Exception;
     public List obtenerTodos()throws Exception;
     public void marcarDevuelto(String idLibro, String idPersona, String fechaDesde) throws Exception;
}
