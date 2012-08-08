/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vidaException;

/**
 *
 * @author eva
 */

public class paramExcepcion extends Exception {
    public paramExcepcion(){
        super();
    }
    public paramExcepcion(String s){
        super(s);
    }
}