package simulador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

/**
 *Esta clase hereda de JPanel.
 * Su objetivo es ser el panel de la unidad 2 
 * @author rodol
 */
public class Panel extends JPanel
{
    private Thread thread;
    private JLabel lblMensaje, lblV0, lblAngulo, lblG, lblH, component, lblAlturaMax, lblAcelacion;
    private JTextField txtV0, txtAngulo, txtG, txtH;
    private JButton btnAnimar;
    private EspacioSimulado espacioSimulado;
    private Simulacion simulacion;
    private JComboBox<String> comboEscenarios;
    private Fondo fondo;
    
    public Panel() 
    {
        super();
        initComponents();
    }
    
    /**
     * Inicializa los componentes necesarios para la clase.
     */
    private void initComponents()
    {
        //Propiedades del panel.
        this.setBackground(new Color(40, 40, 40));
        this.setLocation(0,0);
        this.setSize(1100,800);
        this.setLayout(null);
        
        //Label con una breve descripción de la actividad.
        this.lblMensaje = new JLabel();
        this.lblMensaje.setSize(850,100);
        this.lblMensaje.setLocation(20, -15);
        this.lblMensaje.setText("<html><b>Simulación de un proyectil</b><br>"
                +"Resulta divertido aplicar matemáticas en programación, este "
                + "es un sencillo ejemplo de ello.</html>");
        this.lblMensaje.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        //this.lblMensaje.setOpaque(true);
        this.lblMensaje.setForeground(Color.WHITE);
        
        //Creación del ComboBox
        this.comboEscenarios = new JComboBox();
        this.comboEscenarios.setSize(150, 25);
        this.comboEscenarios.setLocation(910, 40);
        this.comboEscenarios.setFont(new Font("Calibri", Font.BOLD, 16));
        this.comboEscenarios.setForeground(Color.WHITE);
        this.comboEscenarios.setBackground(new Color(50, 100, 200));
        this.comboEscenarios.addItem("Default");
        this.comboEscenarios.addItem("Tierra");
        this.comboEscenarios.addItem("Luna");
        this.comboEscenarios.addItem("Sol");
        this.comboEscenarios.addActionListener((ActionEvent e) -> 
        {
            this.fondo.repaint();
            
            //Aceleración gravitatoria
            switch(this.comboEscenarios.getSelectedIndex())
            {
                //Default
                case 0:
                    this.lblAcelacion.setText("");
                    this.txtG.setText("0");
                    this.txtG.setEnabled(true);
                    break;
                    
                //Tierra    
                case 1:
                    this.lblAcelacion.setText("Aceleración: 32.0 ft/s²");
                    this.txtG.setText("32");
                    this.txtG.setEnabled(false);
                    break;
                    
                //Luna    
                case 2:
                    this.lblAcelacion.setText("Aceleración: 5.0 ft/s²");
                    this.txtG.setText("5");
                    this.txtG.setEnabled(false);
                    break;
                
                //Sol    
                case 3:
                    this.lblAcelacion.setText("Aceleración: 899.0 ft/s²");
                    this.txtG.setText("899");
                    this.txtG.setEnabled(false);
                    break;
            }
        });
        
        //Creación del espacio simulado.
        this.espacioSimulado = new EspacioSimulado(this);
        this.espacioSimulado.setSize(1050, 600);
        this.espacioSimulado.setLocation(10, 80);
        this.espacioSimulado.addMouseMotionListener(new MouseMotionAdapter() 
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                super.mouseDragged(e);
                component.setLocation(e.getPoint().x, component.getLocation().y);
            }
        });
        
        //Label que muestra la aceleración gravitacional
        this.lblAcelacion = new JLabel();
        this.lblAcelacion.setSize(400, 40);
        this.lblAcelacion.setLocation(380, 10);
        this.lblAcelacion.setText("Aceleración: 0.0 ft/s²");
        this.lblAcelacion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
        this.lblAcelacion.setForeground(new Color(255, 255, 255, 150));
        this.espacioSimulado.add(this.lblAcelacion);
        
        //Label para la velocidad inicial
        this.lblV0 = new JLabel();
        this.lblV0.setSize(60, 30);
        this.lblV0.setLocation(5, 700);
        this.lblV0.setText("Velocidad:");
        this.lblV0.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        this.lblV0.setForeground(Color.WHITE);
        
        //TextBox para la velocidad inicial
        this.txtV0 = new JTextField();
        this.txtV0.setSize(50, 30);
        this.txtV0.setLocation(70, 700);
        this.txtV0.setText("0");
        
        //Label para el ángulo.
        this.lblAngulo = new JLabel();
        this.lblAngulo.setSize(60, 30);
        this.lblAngulo.setLocation(130, 700);
        this.lblAngulo.setText("Ángulo:");
        this.lblAngulo.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        this.lblAngulo.setForeground(Color.WHITE);
        
        //TextBox para el ángulo.
        this.txtAngulo = new JTextField();
        this.txtAngulo.setSize(50, 30);
        this.txtAngulo.setLocation(185, 700);
        this.txtAngulo.setText("0");
        
        //Label para la gravedad.
        this.lblG = new JLabel();
        this.lblG.setSize(60, 30);
        this.lblG.setLocation(245, 700);
        this.lblG.setText("Gravedad:");
        this.lblG.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        this.lblG.setForeground(Color.WHITE);
        
        //TextBox para la gravedad.
        this.txtG = new JTextField();
        this.txtG.setSize(50, 30);
        this.txtG.setLocation(310, 700);
        this.txtG.setText("0");
        
        //Label para la altura.
        this.lblH = new JLabel();
        this.lblH.setSize(60, 30);
        this.lblH.setLocation(380, 700);
        this.lblH.setText("Altura:");
        this.lblH.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        this.lblH.setForeground(Color.WHITE);
        
        //TextBox para la altura.
        this.txtH = new JTextField();
        this.txtH.setSize(50, 30);
        this.txtH.setLocation(425, 700);
        this.txtH.setText("0");
        
        //Componente que se animara
        this.component = new JLabel()
        {
            @Override
            public void paint(Graphics g) 
            {
                Graphics2D graphics = (Graphics2D)g;
                graphics.setColor(Color.BLUE);
                graphics.fillOval(0, 0, 20, 20);
            }
        };
        this.component.setBackground(Color.BLUE);
        this.component.setSize(20, 20);
        this.component.setLocation(10, (this.espacioSimulado.getSize().height - this.component.getHeight()));
        this.espacioSimulado.add(this.component);
        
        //Creación del fondo
        this.fondo = new Fondo(this);
        this.fondo.setLocation(0, 0);
        this.fondo.setSize(this.espacioSimulado.getWidth(), this.espacioSimulado.getHeight());
        this.espacioSimulado.add(this.fondo);
        
        //Botón para iniciar con la animación
        this.btnAnimar = new JButton();
        this.btnAnimar.setSize(60, 30);
        this.btnAnimar.setLocation(490, 700);
        this.btnAnimar.setText("Animar");
        this.btnAnimar.setForeground(Color.WHITE);
        this.btnAnimar.setFont(new Font("Tahoma",Font.PLAIN, 12));
        this.btnAnimar.setBorder(null);
        this.btnAnimar.setBackground(new Color(0, 180, 0));
        this.btnAnimar.addActionListener((ActionEvent e) -> 
        {
            thread = new Thread(()->
            {
                synchronized(this)
                {
                    try
                    {
                        //Objeto que se encargara de la animación
                        this.simulacion = new Simulacion(this.espacioSimulado, this.component,
                                Double.parseDouble(this.txtV0.getText()), Double.parseDouble(this.txtAngulo.getText()),
                                Double.parseDouble(this.txtH.getText()), Double.parseDouble(this.txtG.getText()));
                        
                        this.lblAlturaMax.setText("Altura Max: "+this.simulacion.calcularAlturaMax()+" ft");
                        
                        this.simulacion.iniciarSimulacion();
                    }
                    catch (InterruptedException | NumberFormatException ex)
                    {
                        System.out.println("Houston, tenemos un problema");
                        System.err.println(ex.toString());
                    }
                    
                    finally
                    {
                        this.lblAlturaMax.setText("Altura Max: 0.0 ft");
                    }
                }
            });
            
            thread.start();
        });
        
        //Label para visualizar la altura máxima.
        this.lblAlturaMax = new JLabel();
        this.lblAlturaMax.setSize(260, 30);
        this.lblAlturaMax.setLocation(580, 700);
        this.lblAlturaMax.setText("Altura Max: 0.0 ft");
        this.lblAlturaMax.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 24));
        this.lblAlturaMax.setForeground(Color.WHITE);
        
        //Integración de de los componentes al panel.
        this.add(this.lblMensaje);
        this.add(this.comboEscenarios);
        this.add(this.espacioSimulado);
        this.add(this.lblV0);
        this.add(this.txtV0);
        this.add(this.lblAngulo);
        this.add(this.txtAngulo);
        this.add(this.lblG);
        this.add(this.txtG);
        this.add(this.lblH);
        this.add(this.txtH);
        this.add(this.lblAlturaMax);
        this.add(this.btnAnimar);
    }
    
    /**
     * Método que retorna la posición seleccionada en el JComboBox
     * @return 
     */
    public int getIndexCombo()
    {
        return this.comboEscenarios.getSelectedIndex();
    } 
}
