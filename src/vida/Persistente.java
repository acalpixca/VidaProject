/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

/**
 *
 * @author SANCHEE
 */



public interface Persistente {
    void grabaBD(Object o);
    Object leeBD(String[] params);
}
