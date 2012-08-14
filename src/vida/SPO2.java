/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

/**
 *
 * @author SANCHEE
 */
public class SPO2 extends Medida {
    private float valor;
    
    public SPO2(Persona p){
        super(p);
        valor=0;
    }
    
    public void setValor(float v){
        valor=v;
    }
    
    public float getValor(){
        return(valor);
    }
}