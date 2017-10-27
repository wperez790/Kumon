/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

/**
 *
 * @author walt
 */
public class Bitshifter {

    public static String encriptar(String text) {
        char cArray[] = text.toCharArray();
        String encrypt = null;
        for (int i = 0; i < cArray.length; i++) {
            cArray[i] = (char) (cArray[i] + (char) 5);

        }
        encrypt = String.valueOf(cArray);
        return encrypt;
    }

    public static String desencriptar(String text) {
        char cArray[] = text.toCharArray();
        String decrypt = null;
        for (int i = 0; i < cArray.length; i++) {
            cArray[i] = (char) (cArray[i] - (char) 5);

        }
        decrypt = String.valueOf(cArray);
        return decrypt;
    }

}
