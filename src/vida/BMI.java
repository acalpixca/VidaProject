/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

/**
 *
 * @author SANCHEE
 */

import java.util.*;
import vidaException.*;

public class BMI extends Medida {
    
    /*
     * Peso(Persona p);
public Peso(Persona p, Date fm, int origen, float f)
void setValor(float f);
float getValor();
     */
    public BMI (Persona p){
        super(p); //toma la fecha actual por defecto
        super.origenMedida=VidaConst.CALCULADO;
    }
    public BMI(Persona p, Date f){
        super(p);
        super.fechaMedida=f;
        super.origenMedida=VidaConst.CALCULADO;
        //no se asigna un valor... pq BMI es calculado.
    }
    public float getValor() throws paramExcepcion {
        
        // bmi=peso en kilos /(altura en metros^2);
        Medida m=persona.getHistorial().getListaMedida(VidaConst.PESO).getMedidaAnterior(fechaMedida,VidaConst.PESO);
        float peso=((Peso)m).getValor();
        m=persona.getHistorial().getListaMedida(VidaConst.ALTURA).getMedidaAnterior(fechaMedida,VidaConst.ALTURA);
        float altura=((Altura)m).getValor();
        //System.out.println("Soy el objeto BMI peso=" +peso + ", altura="+altura);
        if (peso==0 || altura==0) {
            throw (new paramExcepcion("Método BMI>getValor: el peso o la altura son 0"));
        }
        return(peso/(altura*altura));
    }
}
