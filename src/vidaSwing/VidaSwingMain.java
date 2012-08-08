/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vidaSwing;

/**
 *
 * @author SANCHEE
 */

import java.awt.GridLayout;
import java.awt.event.*;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import vida.*;
import java.util.List;

public class VidaSwingMain implements ActionListener {
    private JFrame jFrame;
    private JLabel jLabel;
    private JTextField jText;
    private JPanel jPanelPrincipal;
    private JButton jButton;
    private JMenuBar jMenuBar;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1s1,jMenuItem1s3;
    private JCheckBoxMenuItem jMenuItem1s2;

    private AccessibleContext accContext;
    
    private String labelPrefix="Number of button clicks: ";
    private int numClicks = 0;

    private VidaChart vc;
    
    public void go() throws Exception {
        
        // Creación del Frame principal
        jFrame = new JFrame("Proyecto Vida");
        
        //Creamos los elementos del menú. Ojo, esto es solamente un ejemplo
        jMenuBar=new JMenuBar();
        jMenu1=new JMenu("File");
        jMenuItem1s1=new JMenuItem("Open");
        jMenuItem1s1.addActionListener(this);
        jMenuItem1s3=new JMenuItem("Exit");
        jMenuItem1s3.addActionListener(this);
        jMenuItem1s2=new JCheckBoxMenuItem("Word Wrap",true);
        jMenu1.add(jMenuItem1s1);
        jMenu1.add(jMenuItem1s2);
        jMenu1.add(jMenuItem1s3);
        jMenuBar.add(jMenu1);
        jFrame.setJMenuBar(jMenuBar);
        
        // Montamos los componentes para interactuar con el usuario
        jLabel = new JLabel(labelPrefix + "0");
        jText = new JTextField("Escribe aquí",30);
        jButton = new JButton("Contador de clicks");

        //Atajo de teclado: Se hace ALT-I equivalente a
        //presionar el botón de la GUI con el ratón
        jButton.setMnemonic('i');
        jButton.addActionListener(this);

        //Se añade soporte para accesibilidad
        accContext = jButton.getAccessibleContext();
        accContext.setAccessibleDescription(
            "Pressing this button increments " +
            "the number of button clicks");

       
        //Creamos el panel principal de la aplicación
        //  así como 2subpaneles: 
        // 1. jPanelControles (en él colocaremos elementos (los creados arriba)), y
        // 2. Notablemente, el subpanel VidaChart vc, que es donde pintaremos las gráficas.
        
        jPanelPrincipal = new JPanel();
        jPanelPrincipal.setBorder(
        BorderFactory.createEmptyBorder(30,30,10,30));

        JPanel jPanelControles=new JPanel();
        jPanelControles.setBorder(
        BorderFactory.createEmptyBorder(30,30,30,30));
        jPanelControles.setLayout(new GridLayout(0, 1));
        
//Los componentes se colocan en una sola columna
        jPanelPrincipal.setLayout(new GridLayout(0, 1));

// Datos, datos! Creamos a la persona en cuestión
        
        List<Persona> lp=VidaStub.creaDatos();
        Persona eva=new Persona();
        eva=lp.get(0);
        Persona br=new Persona();
        br=lp.get(1);
        
        vc=new VidaChart((Persona)eva); // esto es una guarrada y no me gusta.
        vc.anadeMedida(eva.historial.getListaMedida(VidaConst.PESO).getMedidaIntervaloHoy(VidaConst.stringToFecha("2012-01-01"), 
                    VidaConst.PESO),VidaConst.PESO);
        //vc.anadeMedida(br.historial.getListaMedida(VidaConst.PESO).getMedidaIntervaloHoy(VidaConst.stringToFecha("2012-01-01"), 
        //            VidaConst.PESO),VidaConst.PESO);
        
//Los componentes van en el Panel, no en el JFrame
        
        jPanelControles.add(jButton);
        jPanelControles.add(jText);
        jPanelPrincipal.add(jPanelControles);
        jPanelPrincipal.add(vc); 
        jPanelPrincipal.add(jLabel);
        jFrame.setContentPane(jPanelPrincipal);
        jFrame.pack();
//WindowListener para manejar el evento de 
//cerrar la ventana
        WindowListener wl = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        jFrame.addWindowListener(wl);
        jFrame.pack();
        jFrame.setVisible(true);
    }
//Manejador de eventos para el botón de la GUI
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JMenuItem){
            if(source.equals(jMenuItem1s3)){ //Exit!
                System.exit(0);
            }
            else if (source.equals(jMenuItem1s1) && jMenuItem1s2.getState()){
                if (jText.getText().equals("")) {
                    jLabel.setText("Clicaste Open y Wrap tickado");
                }
                else {
                    jLabel.setText(jText.getText() + " Open y Wrap");
                }
           }
        }
        else {
            numClicks++;
            //jLabel.setText(labelPrefix + numClicks);
            String nomBoton=((JButton)e.getSource()).getText();
            jLabel.setText(nomBoton + " " + labelPrefix + " " + numClicks);
        }
    }
}