/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.ArrayList;
import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Auxiliar;
import org.kumon.persist.DaoAuxiliarImpl;

/**
 *
 * @author Walter
 */
public class AuxiliarBO {

    private DaoAuxiliarImpl auxiliarDB = Contexto.construirDaoAuxiliarImpl();
    
    /*    List<Auxiliar> auxiliaresList = new ArrayList<Auxiliar>();
    public Auxiliar getAuxiliarbyUser(String user) {
    for (Auxiliar a : auxiliaresList) {
    if (a.getUser().equals(user)) {
    return a;
    }
    }
    return null;
    }*/
    public List getAll() throws Exception {
       return auxiliarDB.getAll();

    }

    public void agregarAuxiliar(String idPersona) throws Exception {
        auxiliarDB.agregarAuxiliar(idPersona);
    }

}
