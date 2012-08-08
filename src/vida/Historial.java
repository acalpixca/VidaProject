/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.util.HashMap;
import vidaException.paramExcepcion;
/**
 *
 * @author SANCHEE
 */
public class Historial {
    
    private HashMap llm = new HashMap();
    protected Persona persona;
    
    public Historial(Persona p){
        persona=p;
        llm.put((Integer)VidaConst.ALTURA,new ListaMedida(persona));
        llm.put((Integer)VidaConst.PESO,new ListaMedida(persona));
    }
    
   public Persona getPersona(){
       return (persona);
   }
    public ListaMedida getListaMedida(int tipoMedida) throws paramExcepcion {
        if (tipoMedida==VidaConst.PESO || tipoMedida==VidaConst.ALTURA) return ((ListaMedida)llm.get((Integer)tipoMedida));
        else throw (new paramExcepcion("Historial>getListaMedida: el parámetro tipoMedida es incorrecto"));
    }

    public void ordenaHistorial() {
        // HAY QUE HACER LLAMADA A keys y recorrer el SET resultante, llamando al
        //valor de cada una de las keys
        ((ListaMedida)(llm.get((Integer)VidaConst.PESO))).ordenaListaMedida();
        ((ListaMedida)(llm.get((Integer)VidaConst.ALTURA))).ordenaListaMedida();
   }
}