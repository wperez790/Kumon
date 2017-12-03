/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.Calendar;
import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Asistencia;
import org.kumon.persist.DaoAsistenciaImpl;

/**
 *
 * @author walt
 */
public class AsistenciaBO {

    private DaoAsistenciaImpl asistenciaDB = Contexto.construirDaoAsistenciaImpl();

    public String getHoraExacta(Calendar cal) {
        String time = "" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return time;
    }

    public void registrar(Asistencia asistencia) throws Exception {
        asistenciaDB.registrar(asistencia);
    }

    public List obtenerTodas() throws Exception {
        return asistenciaDB.obtenerTodas();
    }
}
