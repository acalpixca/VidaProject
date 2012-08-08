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
public abstract class Medida implements Comparable<Medida> {
   protected Date fechaMedida; 
   protected int origenMedida;
   protected Persona persona;
   
   public Medida(Persona p){
       persona=p;
       fechaMedida=new Date();
   }
   
   public Persona getPersona(){
       return (persona);
   }
   public void setFechaMedida(Date fecha){
       fechaMedida=fecha;
   }
   
   public Date getFechaMedida(){
       return(fechaMedida);
   }
   
   void setOrigenMedida(int i) {
       //necesitas validar que el origen de la medida sea una de las constantes definidas
       origenMedida=i;
   }
   
   int getOrigenMedida(){
       return(origenMedida);
   }
   String getTipoMedida(){
       return(this.getClass().getName());
   }
  
    @Override
    public int compareTo(Medida m) {
        // ordenamos anti-cronológicamente, medida más reciente es la primera
        if (fechaMedida.equals(m.getFechaMedida())){
            return(0);
        }
        else if (fechaMedida.after(m.getFechaMedida())){
            return -1;
        }
        else {
            return 1;
        }
    }
}