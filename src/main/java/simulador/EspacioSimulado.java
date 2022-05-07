package simulador;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

/**
 * Clase destinada para la simulación de un cohete
 * @author rodol
 */
public class EspacioSimulado extends JPanel
{
    private final JComponent context;
    
    public EspacioSimulado(JComponent context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Inicializa los componentes necesarios para la clase.
     */
    private void initComponents()
    {
        //Propiedades del objeto
        this.setBackground(Color.WHITE);
        this.setLayout(null);  
    }

    /**
     * Método para el dibujado del componente.
     * @param g 
     */
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        
        Graphics2D graphics = (Graphics2D)g;
        
        graphics.setStroke(new BasicStroke(1));
        
        if(((Panel)this.context).getIndexCombo() != 0)
            graphics.setColor(new Color(255, 255, 255, 40));
        else
            graphics.setColor(new Color(0, 0, 0, 40));
              
        for (int i = 0; i < this.getSize().width; i+= 50)
            graphics.drawLine(i, 0, i, this.getSize().height);
        for (int i = 0; i < this.getSize().height; i+= 50)
            graphics.drawLine(0, i, this.getSize().width, i); 
    }
    
}
