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
        char c;
        String encrypt = null;
        for(int i = 0; i<text.length() ; i++){
            c= text.charAt(i);
            c+=10;
            encrypt += c;
        }
        return encrypt;
    }
    public static String desencriptar(String text) {
        char c;
        String decrypt = null;
        for(int i = 0; i<text.length() ; i++){
            c= text.charAt(i);
            c-=10;
            decrypt += c;
        }
        return decrypt;
    }
    
}
