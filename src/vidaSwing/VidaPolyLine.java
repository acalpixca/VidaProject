/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vidaSwing;

/**
 *
 * @author SANCHEE
 */
import java.awt.Graphics;

public class VidaPolyLine {
    int[] xPoints;
    int[] yPoints;
    Graphics g;
    
    public VidaPolyLine(int[] xP,int[] yP, Graphics gra){
        xPoints=xP;
        yPoints=yP;
        g=gra;
    }
    
    public void dibujaPolyLine(){
        g.drawPolyline(xPoints, yPoints, xPoints.length);
    }
    public boolean incluyePunto(int x, int y, float h) {
        int minX, maxX, minY, maxY, a, b;
        boolean incluido=false;
        float epsilon=2f;
        System.out.println("Incluye Punto. Recibidos x=" + x + ", y=" +y);
        for (int i=0;i<xPoints.length-1;i++){
            System.out.println("Iteracion num "+ i);
            if (xPoints[i]<xPoints[i+1]){
                minX=xPoints[i];
                maxX=xPoints[i+1];
            }
            else {
                minX=xPoints[i+1];
                maxX=xPoints[i];
            }
            if (yPoints[i]<yPoints[i+1]){
                minY=yPoints[i];
                maxY=xPoints[i+1];
            }
            else {
                minY=yPoints[i+1];
                maxY=yPoints[i];
            }
            System.out.println("Minpoints y Maxpoints de ("+xPoints[i]+","+yPoints[i]+
                    ") y (" + xPoints[i+1]+ ","+yPoints[i+1]+") son:");
            System.out.println("max (" +maxX+","+maxY +"), min (" +minX+","+minY + ")");
            if (x>=minX && x<=maxX && y>=minY && y<=maxY){
                System.out.println("El segmento " + i + " candidato para ser intersección");
                //b=(yPoints[i]-yPoints[i+1])/(xPoints[i+1]-xPoints[i]);
                b=(yPoints[i+1]-yPoints[i])/(xPoints[i+1]-xPoints[i]);
                //a=yPoints[i]-(b*xPoints[i]);
                a=(int)(h+yPoints[i]-(b*xPoints[i]));
                if (Math.abs(y-(a+b*x))<epsilon) {
                    incluido=true;
                    //break;
                }
            }
        }
        return (incluido);
    }
}