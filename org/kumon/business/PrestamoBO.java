/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.sql.Date;
import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Prestamo;
import org.kumon.persist.DaoPrestamoImpl;

/**
 *
 * @author walt
 */
public class PrestamoBO {

    private DaoPrestamoImpl prestamoDB = Contexto.construirDaoPrestamoImpl();

    public boolean registrarPrestamo(Prestamo prestamo) throws Exception {
        int cantidad = Contexto.libro.getStock() - 1;
        if (cantidad >= 0) {
            LibroBO libroBO = Contexto.construirLibroBO();
            libroBO.modificarStockLibro(cantidad, prestamo.getIdLibro());
            prestamoDB.registrarPrestamo(prestamo);
            return true;
        } else {
            return false;
        }
    }

    public void anularPrestamo(String idLibro, String idPersona, String fechaDesde) throws Exception {
        prestamoDB.anularPrestamo(idLibro, idPersona, fechaDesde);
    }

    public void marcarDevuelto(String idLibro, String idPersona, String fechaDesde) throws Exception {
        prestamoDB.marcarDevuelto(idLibro, idPersona, fechaDesde);
    }

    public List obtenerPrestamosNoDevueltos() throws Exception {
        return prestamoDB.obtenerPrestamosNoDevueltos();
    }

    public List obtenerTodos() throws Exception {
        return prestamoDB.obtenerTodos();
    }

    public List obtenerPrestamosVencidos() throws Exception {
        return prestamoDB.obtenerPrestamosVencidos();
    }

}
