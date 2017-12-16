/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.Calendar;
import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Tarea;
import org.kumon.persist.DaoTareaImpl;

/**
 *
 * @author walt
 */
public class TareaBO {

    private DaoTareaImpl tareaDB = Contexto.construirDaoTareaImpl();

    public boolean registrar(Tarea tarea) throws Exception {
        return tareaDB.registrar(tarea);
    }

    public boolean modificar(Tarea tarea) throws Exception {
        return tareaDB.modificar(tarea);
    }

    public boolean eliminar(Integer idTarea) throws Exception {
        return tareaDB.eliminar(idTarea);
    }

    public List<Tarea> buscarByDate(Calendar fecha) throws Exception {
        return tareaDB.buscarByDate(fecha);
    }

    public Integer[] getDaysOfMonth() {

        Calendar fecha = Calendar.getInstance();
        Integer[] day = new Integer[8];
        for (int i = 0; i < 8; i++) {
            day[i] = fecha.get(Calendar.DAY_OF_MONTH);
            fecha.add(Calendar.DATE, 1);
        }

        return day;
    }

    public Integer[] getDaysOfWeek() {
        Calendar fecha = Calendar.getInstance();
        Integer[] day = new Integer[8];
        for (int i = 0; i < 8; i++) {
            day[i] = fecha.get(Calendar.DAY_OF_WEEK);
            fecha.add(Calendar.DATE, 1);
        }
        return day;
    }
    
    public String[] getDaysOfWeekSpanish(){
        String[] day = new String[8];
        Integer[] days = getDaysOfWeek();
        for(int i = 0 ; i< days.length ; i++){
            switch(days[i]){
                case 1: day[i] = "Domingo";
                    break;
                case 2: day[i] = "Lunes";
                    break;
                case 3: day[i] = "Martes";
                    break;
                case 4: day[i] = "Miercoles";
                    break;
                case 5: day[i] = "Jueves";
                    break;
                case 6: day[i] = "Viernes";
                    break;
                case 7: day[i] = "Sabado";
                    break;
                default : day[i]= "Error";
                    break;
            }
        }
        return day;
    }
}
