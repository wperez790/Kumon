/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author walt
 */
public class EscribirLeerArchivo {

    public static void escribir(String dato, boolean indicadorUser) {
        FileWriter fichero = null;
        FileWriter fichero2 = null;
        PrintWriter pw = null;
        String direccion;
        if(indicadorUser == false){
            direccion ="/home/walt/NetBeansProjects/org.Kumon.main/src/org/kumon/main/pass.txt";
        }
        else{
            direccion ="/home/walt/NetBeansProjects/org.Kumon.main/src/org/kumon/main/user.txt";
        
        try {
            fichero = new FileWriter(direccion);
            pw = new PrintWriter(fichero);
            pw.print(dato);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero && null != fichero2) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        }
    }

    public static String leer(boolean indicadorUser) throws FileNotFoundException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = null;
        String t = null;
        String direccion; 
        if (indicadorUser == false) {
            direccion="/home/walt/NetBeansProjects/org.Kumon.main/src/org/kumon/main/pass.txt";

        } else {

            direccion="/home/walt/NetBeansProjects/org.Kumon.main/src/org/kumon/main/user.txt";
        }
            try {
                // Apertura del fichero y creacion de BufferedReader para poder
                // hacer una lectura comoda (disponer del metodo readLine()).
                archivo = new File(direccion);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero
                while ((texto = br.readLine()) != null) {
                    t = texto;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta 
                // una excepcion.
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        

        return t;

    }
}
