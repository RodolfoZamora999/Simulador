package simulador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Clase destiana para la creación de los fondos de los escenarios con código.
 * @author Rodolfo Zamora.
 */
public class Fondo extends JLabel
{
    private final JComponent context;
    
    public Fondo(JComponent context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes necesarios para la clase.
     */
    private void initComponents()
    {
        this.setOpaque(true);
        this.setBackground(Color.GREEN);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g); 
        
        Graphics2D graphics = (Graphics2D)g;
        
        //Uso del switch
        switch(((Panel)this.context).getIndexCombo())
        {
            //Escenario por default
            case 0:
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                break;
                
            //Escenario de la tierra    
            case 1:
                //Creación del cielo
                graphics.setColor(new Color(61, 86, 173));
                graphics.fillRect(0, 0, 1050, 550);
                
                //Creación del sol
                graphics.setColor(new Color(255, 255, 255));
                graphics.fillOval(0, 0, 100, 100);
                graphics.setColor(new Color(255, 255, 255, 20));
                graphics.fillOval(-25, -25, 150, 150);
                graphics.fillOval(-50, -50, 200, 200);
                graphics.fillOval(-75, -75, 250, 250);
                graphics.fillOval(-100, -100, 300, 300);
                graphics.fillOval(-125, -125, 350, 350);
                
                //Creación de las estrellas
                Random random = new Random();
                graphics.setColor(new Color(255, 255, 255));
                for(int i = 0; i < 50; i++)
                    graphics.fillOval(random.nextInt(1050), random.nextInt(550), 2, 2);
                
                //Creación de las montañas (Sombras)
                graphics.setColor(new Color(0, 0, 0, 200));
                graphics.fillOval(2, 347, 300, 390);
                graphics.fillOval(698, 347, 300, 390);
                
                //Creación de las montañas
                graphics.setColor(new Color(37, 47, 39));
                graphics.fillOval(0, 350, 300, 390);
                graphics.fillOval(700, 350, 300, 390);
                
                //Creación del suelo
                graphics.setColor(new Color(30, 30, 30));
                graphics.fillRect(0, 550, 1050, 50);
                
                break;
            
            //Escenario de la luna    
            case 2:
                
                //Creación del cielo
                graphics.setColor(new Color(0, 0, 0));
                graphics.fillRect(0, 0, 1050, 550);
                
                //Creación del sol
                graphics.setColor(new Color(255, 255, 255));
                graphics.fillOval(1030, 0, 50, 50);
                
                graphics.setColor(new Color(255, 255, 255, 20));
                graphics.fillOval(1005, -25, 100, 100);
                graphics.fillOval(980, -50, 150, 150);
                graphics.fillOval(955, -75, 200, 200);
                
                //Creación de las estrellas
                Random random1 = new Random();
                graphics.setColor(new Color(255, 255, 255));
                for(int i = 0; i < 60; i++)
                    graphics.fillOval(random1.nextInt(1050), random1.nextInt(550), 3, 3);
                
                //Creación del mundo
                graphics.setColor(new Color(1, 116, 218));
                graphics.fillOval(0, 0, 200, 200);
                
                graphics.setColor(new Color(70, 57, 10));
                int[] pointsX = new int[] { 160, 170, 150, 160, 180, 160, 120, 110 };
                int[] pointsY = new int[] { 110,   120, 130, 140, 150, 160, 130, 160 };
                graphics.fillPolygon(pointsX, pointsY, 8);
                
                int[] pointsX1 = new int[] { 60, 70, 150, 60, 80, 60, 120, 10 };
                int[] pointsY1 = new int[]{ 10,   20, 30, 40, 50, 60, 130, 150 };
                graphics.fillPolygon(pointsX1, pointsY1, 8);
                
                //Creación de la sombrea de la tierra
                graphics.setColor(new Color(0, 0, 0, 220));
                graphics.fillOval(-60, 0, 200, 200);
                
                //Creación de la sombra de la luna
                graphics.setColor(new Color(50, 50, 50));
                graphics.fillRect(0, 550, 1050, 50);
                
                break;
            
            //Escenario del sol    
            case 3:
                
                //Creación del suelo
                graphics.setColor(new Color(232, 68, 2));
                graphics.fillRect(0, 0, 1050, 550);
                
                //Creación de las estrellas
                Random random2 = new Random();
                graphics.setColor(new Color(255, 255, 255));
                for(int i = 0; i < 60; i++)
                    graphics.fillOval(random2.nextInt(1050), random2.nextInt(550), 3, 3);
                
                //Creación del suelo
                graphics.setColor(new Color(255, 40, 10));
                graphics.fillRect(0, 550, 1050, 50);
                
                break;
        } 
    }
    
      
}
