/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.util.*;
import vidaException.*;

/**
 *
 * @author SANCHEE
 */
public class ListaMedida {
    
private List<Medida> lista=new LinkedList<Medida>();
protected Persona persona;

public ListaMedida(Persona p){
    persona=p;
}

    public void anadeElemento(Medida m) {
        if (m.getClass().getName().equals("vida.Peso")){
            lista.add((Peso)m);
        }
        else if (m.getClass().getName().equals("vida.Altura")) {
            lista.add((Altura)m);
        }
    }

    public void eliminaElemento(Medida m){
        if (m.getClass().getName().equals("vida.Peso")){
            lista.remove((Peso)m);
        }
        else if (m.getClass().getName().equals("vida.Altura")) {
            lista.remove((Altura)m);
        }
    }
    
    public void ordenaListaMedida(){
        Collections.sort(lista);
    }
    
    public void iteraListaMedida(){
       Medida me;
       ListIterator it=lista.listIterator();
       if (it.hasNext() && lista.get(0).getClass().getName().equals("vida.Peso")) {
        while (it.hasNext()){
           me=(Peso)it.next();
           System.out.println(" A fecha " + VidaConst.fechaToString(me.getFechaMedida()) + " pesabas " + ((Peso)me).getValor() + " kg.");
        }
       }
       else if (it.hasNext() && lista.get(0).getClass().getName().equals("vida.Altura")){
           while (it.hasNext()){
            me=(Altura)it.next();
            System.out.println(" A fecha " + VidaConst.fechaToString(me.getFechaMedida()) + " tu altura era " + ((Altura)me).getValor() + " m.");
           }
       }
   }  
    
    public Iterator listIterator(){
        return(lista.listIterator());
    }
    
    public int size(){
        return lista.size();
    }

    public Medida getMedidaAnterior(Date d, int tipoMedida){
       this.ordenaListaMedida();
       Object ret=null;
       Iterator it=lista.listIterator();
       boolean encontrado=false;
       while(encontrado==false && it.hasNext()){
           if (tipoMedida==VidaConst.PESO) ret=(Peso)it.next();
           else if (tipoMedida==VidaConst.ALTURA) ret=(Altura)it.next();
           encontrado=(((Medida)ret).getFechaMedida().equals(d) || ((Medida)ret).getFechaMedida().before(d));
       }
       if (encontrado) return (Medida)ret;
       else return (null);      
   }
    
       public Medida getMedidaMasReciente(int tipoMedida){
       this.ordenaListaMedida();
       ListIterator it=lista.listIterator();
       if (it.hasNext()) {
           return (Medida)it.next();
       }
       else return(null);
   }
       public Medida getMedidaMasAntigua(int tipoMedida){
           this.ordenaListaMedida();
           return(lista.get(lista.size()-1));
       }
       
public ListaMedida getMedidaIntervalo(Date fechaInicio, Date fechaFinal, int tipoMedida){
       this.ordenaListaMedida();
       ListIterator it=lista.listIterator();
       List<Medida> lisRes=new LinkedList<Medida>();
       boolean fin=false;
       while (fin==false && it.hasNext()){
           Medida elem = (Medida)it.next();
           if ((elem.fechaMedida.equals(fechaFinal) || 
              elem.fechaMedida.before(fechaFinal)) && 
              (elem.fechaMedida.equals(fechaInicio) ||     
              elem.fechaMedida.after(fechaInicio))){
               lisRes.add(elem);
           }
           else if (elem.fechaMedida.before(fechaInicio)) { 
               fin=true;
           }
       }
       ListaMedida resul=new ListaMedida(persona);
       resul.lista=lisRes;
       return (resul);       
   }

    public ListaMedida getMedidaIntervaloHoy(Date fechaInicio, int tipoMedida){
       return (getMedidaIntervalo(fechaInicio,new Date(),tipoMedida));
    }
    
    public Medida maximoValor(int tipoMedida) throws paramExcepcion {
       
       ListIterator it=lista.listIterator();
       Medida maxTemp;
       Medida elem;
       if (tipoMedida==VidaConst.PESO) { 
           elem=new Peso(persona);
           maxTemp=new Peso(persona);
           ((Peso)maxTemp).setValor(0);
       }
       else if (tipoMedida==VidaConst.ALTURA) {
           elem=new Altura(persona);
           maxTemp=new Altura(persona);
           ((Altura)maxTemp).setValor(0);
       }
       else {
           throw (new paramExcepcion("ListaMedida>maximoValor: el parámetro tipoMedida es incorrecto"));
       }
       
       while (it.hasNext()){
           elem=(Medida)it.next();
           
           if (tipoMedida==VidaConst.PESO) {
               if (((Peso)elem).getValor()>((Peso)maxTemp).getValor()){
                    ((Peso)maxTemp).setValor(((Peso)elem).getValor());
               }
           }
           else if (tipoMedida==VidaConst.ALTURA) {
               if (((Altura)elem).getValor()>((Altura)maxTemp).getValor()){
                    ((Altura)maxTemp).setValor(((Altura)elem).getValor());
               }
           }
       }
       return(maxTemp);
   }
    
    public Medida minimoValor(int tipoMedida) throws paramExcepcion {
       
       ListIterator it=lista.listIterator();
       Medida maxTemp;
       Medida elem;
       if (tipoMedida==VidaConst.PESO) { 
           elem=new Peso(persona);
           maxTemp=new Peso(persona);
           ((Peso)maxTemp).setValor(1e10f); // un número muy grande
       }
       else if (tipoMedida==VidaConst.ALTURA) {
           elem=new Altura(persona);
           maxTemp=new Altura(persona);
           ((Altura)maxTemp).setValor(0);
       }
       else {
           throw (new paramExcepcion("ListaMedida>minimoValor: el parámetro tipoMedida es incorrecto"));
       }
       
       while (it.hasNext()){
           elem=(Medida)it.next();
           
           if (tipoMedida==VidaConst.PESO) {
               if (((Peso)elem).getValor()<((Peso)maxTemp).getValor()){
                    ((Peso)maxTemp).setValor(((Peso)elem).getValor());
               }
           }
           else if (tipoMedida==VidaConst.ALTURA) {
               if (((Altura)elem).getValor()<((Altura)maxTemp).getValor()){
                    ((Altura)maxTemp).setValor(((Altura)elem).getValor());
               }
           }
       }
       return(maxTemp);
   }
    
   public Persona getPersona(){
       return (persona);
   }
}