/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vidaSwing;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Graphics;
import vida.*;
import vidaSwing.VidaPolyLine;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import vidaException.paramExcepcion;
/**
 *
 * @author SANCHEE
 */
public class VidaChart extends JPanel {
    
    List metricas=new ArrayList();
    int colorin=1;
    Persona persona=new Persona();   
    int deltaH;
    int deltaV;
    int padding=20;
    int xRaton=-1,yRaton=-1;
    
    class PopUpDemo extends JPopupMenu {
        // http://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
        JMenuItem anItem;
        public PopUpDemo(){
            anItem = new JMenuItem("Click Me!");
            add(anItem);
        }
    }
    class PopClickListener extends MouseAdapter {
        // http://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
        public void mousePressed(MouseEvent e){
            if (e.isPopupTrigger()) {
                doPop(e);
            }
        }

        public void mouseReleased(MouseEvent e){
            if (e.isPopupTrigger()) {
                doPop(e);
            }
        }

        private void doPop(MouseEvent e){
            PopUpDemo menu = new PopUpDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }


    
    private int calculaDeltaV() throws paramExcepcion { 
        float max=0, min=0;
        int delta=0;
        try {
            max=((Peso)((ListaMedida)(metricas.get(0))).maximoValor(VidaConst.PESO)).getValor();
            min=((Peso)((ListaMedida)(metricas.get(0))).minimoValor(VidaConst.PESO)).getValor();
            delta=(int)((this.getHeight()-padding)/(max-min));
        }
        finally {
        System.out.println("Calculo delta V. Max es " + max +
                ". Min es " + min +
                ". Altura panel es " + this.getHeight() +
                ". DeltaV es " + delta);
        deltaV=delta;
        }
        return (delta);
    }
    
    private int calculaDeltaH(){
        Date max=((ListaMedida)(metricas.get(0))).getMedidaMasReciente(VidaConst.PESO).getFechaMedida(); 
        Date min=((ListaMedida)(metricas.get(0))).getMedidaMasAntigua(VidaConst.PESO).getFechaMedida();
        int delta=(int)((this.getWidth()-padding)/(VidaConst.restaFechas(max,min)));
        System.out.println("Calculo delta H. Diferencia fechas en días es " + 
                VidaConst.restaFechas(max, min) + 
                ". Ancho panel es " + this.getWidth() + 
                ". DeltaH es " + delta);
        deltaH=delta;
        return (delta);
    }
    
    MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            xRaton=e.getX();
            yRaton=e.getY();
            if(colorin==1) {
                  // código para gestionar clicazos
                colorin=0;
            }
            else { colorin=1; }
        repaint();
        }
    };
    
    public VidaChart(Persona p){
        persona=p;
        addMouseListener(ml);
        addMouseListener(new PopClickListener());
    }
    
    public VidaChart(ListaMedida lm) {
        persona=lm.getPersona();
        metricas.add(lm);
        addMouseListener(ml);
        // este MouseListener es el que permitiría tener menús contextuales right click
        addMouseListener(new PopClickListener());
    }
    
    public void anadeMedida(ListaMedida lm, int tipoMedida) {
        metricas.add(lm);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calculamos las escalas
        int kk;
        try {
            kk = calculaDeltaV();
        } catch (paramExcepcion ex) {
            Logger.getLogger(VidaChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        kk=calculaDeltaH();
        // Pintamos los ejes
        g.setColor(Color.BLACK);
        g.drawLine(0,0,0,this.getHeight()-padding);
        g.drawLine(0,this.getHeight()-padding,this.getWidth()-padding,this.getHeight()-padding);
        
        Iterator li2=metricas.listIterator();
        Iterator li3;
        ListaMedida lm2=new ListaMedida(persona);
        while (li2.hasNext()){
            lm2=(ListaMedida)li2.next();
            li3=lm2.listIterator();
            int [] xPoints = new int[lm2.size()];
            int [] yPoints = new int[lm2.size()];
            int i=0;
            Date fechaMin=lm2.getMedidaMasAntigua(VidaConst.PESO).getFechaMedida();
            int valorMin=0;
            int valorMax=0;
            try {
                valorMin = (int)((Peso)lm2.minimoValor(VidaConst.PESO)).getValor();
                valorMax=(int)((Peso)lm2.maximoValor(VidaConst.PESO)).getValor();
            } catch (paramExcepcion ex) {
                Logger.getLogger(VidaChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (li3.hasNext()){
                Medida mTemp=(Medida)li3.next();
                float floatX=VidaConst.restaFechas(mTemp.getFechaMedida(), fechaMin)*deltaH;
                xPoints[i]= (int)floatX;
                float floatY=(((Peso)mTemp).getValor()-valorMin)*deltaV;
                yPoints[i]=(int)((valorMax-valorMin)*deltaV-floatY);
                g.setColor(Color.GREEN);
                g.drawOval(xPoints[i]-5,yPoints[i]-5,10,10);
                
                System.out.println("Polyline. Fecha " + 
                        VidaConst.fechaToString(mTemp.getFechaMedida())+
                        " valor " + ((Peso)mTemp).getValor() +
                        ". Transformados da x = " + 
                        floatX +
                        ", y = " + floatY +
                        ", y pasado a int = " + (int)floatY + 
                        ", y guardado en array " + yPoints[i]);
                i=i+1;
            }
            if (colorin==1) g.setColor(Color.PINK);
            else g.setColor(Color.ORANGE);
            VidaPolyLine pl=new VidaPolyLine(xPoints,yPoints,g);
            if (pl.incluyePunto(xRaton, yRaton,(valorMax-valorMin)*deltaV)) g.setColor(Color.BLACK);
            pl.dibujaPolyLine();
            //g.drawPolyline(xPoints,yPoints,lm2.size());
        }
    }
}