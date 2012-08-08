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
public class Peso extends Medida {
    float valor;
    
    public Peso(Persona p){
        // nada de código, hay que declarar para el polimorfismo con abajo
        super(p);
    }
    public Peso(Persona p, Date fm, int origen, float f){
        super(p);
        this.setFechaMedida(fm);
        this.setOrigenMedida(origen); //VidaConst.MANUAL o VidaConst.WEBSERVICE
        this.setValor(f);
    }

    
    public void setValor(float f){
        valor=f;
    }
    public float getValor(){
        return(valor);
    }
}