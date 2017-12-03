/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.persist.DaoAdministradorImpl;

/**
 *
 * @author walt
 */
public class AdministradorBO {

    DaoAdministradorImpl adminsDB = Contexto.construirDaoAdministradorImpl();

    public void agregarAdmin(String idPersona) throws Exception {
        adminsDB.agregarAdmin(idPersona);
    }

    public List getAll() throws Exception {
        return adminsDB.getAll();
    }

}
