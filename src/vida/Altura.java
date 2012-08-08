/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.util.Date;

/**
 *
 * @author SANCHEE
 */
public class Altura extends Medida {
    float valor;
    
    public Altura(Persona p){
        super(p);
        //nada, hay que declararlo por el polimorfismo con el constructor de abajo
    }
    public Altura(Persona p, Date fm, int origen, float f){
        super(p);
        this.setFechaMedida(fm);
        this.setOrigenMedida(origen); //VidaConst.MANUAL o VidaConst.WEBSERVICE
        this.setValor(f);
    }


    void setValor(float f){
        valor=f;
    }
    float getValor(){
        return(valor);
    }
}