package paquete;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import  simulador.Panel;

/**
 * Clase que sirve como contenedor del programa
 * @author rodol
 */
public class Frame extends JFrame
{
    private Panel panel;
    
    public Frame()
    {
        this.initComponents();
    }
    
    //Inicializa los componentes de la clase necesarios.
    private void initComponents()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        //Propiedades del componente
        this.setSize(1100,800);
        this.setLocation((dimension.width - this.getWidth())/2, (dimension.height - this.getHeight())/2);
        this.setResizable(false);
        this.setTitle("Proyecto de cálculo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.panel = new Panel();
        
        this.add(this.panel);
    } 
}
