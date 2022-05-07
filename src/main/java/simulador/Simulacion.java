package simulador;

import javax.swing.*;

/**
 * Clase que tiene como objetivo controlar la lógica de la simulación.
 * @author rodol
 */
class Simulacion
{
    private final JPanel panel;
    private final JComponent component;
    private double v0, angulo, h, g;
    
    /**
     * Único método constructor de la clase.
     * @param panel Contenedor del objeto animar.
     * @param component Componente (JButton, JLabel, etc. que se animara.)
     * @param v0 Velocidad inicial del objeto.
     * @param angulo Ángulo de inclinación del objeto.
     * @param h Altura del nivel del suelo.
     * @param g Gravedad. Se recomienda 32ft
     */
    public Simulacion(JPanel panel, JComponent component, double v0, double angulo, double h, double g) 
    {
        this.panel = panel;
        this.component = component;
        this.v0 = v0;
        this.angulo = angulo;
        this.h = h;
        this.g = g;
    }
    
    /**
     * Método que inicializa la simulación del objeto.
     * Se recomienda ejecutarlo en un hilo separado.
     * @throws InterruptedException 
     */
    public void iniciarSimulacion() throws InterruptedException
    {
        //variable de apoyo
        int posicionX = this.component.getLocation().x;
        int posicionY = this.component.getLocation().y;
        
        int nuevaX;
        int nuevaY;
        
        double t = 0;
        
        do 
        {
            nuevaX = (int)calcularPosicionX(v0, angulo, t);
            nuevaY = (int)calcularPosicionY(v0, angulo, h, g, t);
            
            this.component.setLocation(posicionX + nuevaX, posicionY - nuevaY);
            
            //Aumenta 0.1 segundos
            t += 0.1;  
            
            //Esto se tiene que corregir
            Thread.sleep(18);
            
        } while ((posicionY - nuevaY) <= posicionY);
        
        this.component.setLocation(this.component.getLocation().x, posicionY);
    }
    
    /**
     * Calcular la altura maxima.
     * @param v0 Velocidad inicial.
     * @param angulo Ángulo de inclinación
     * @param h Altura sobre el piso.
     * @param g Gravedad.
     * @return  La altura máxima.
     */
    public double calcularAlturaMax()
    {
        //Se calcula el tiempo 
        double tiempo = (v0 * Math.sin(Math.toRadians(angulo))) / g;
        
        double tem = (h + (v0 * Math.sin(Math.toRadians(angulo)) * tiempo) - ((g * 0.5) * Math.pow(tiempo, 2)));
        
        return Math.round(tem);
    }
    
    /**
     * Calcular la posición exacta en X.
     * @param v0 Velocidad inicial.
     * @param angulo Ángulo de inclinación.
     * @param t Tiempo que quiere calcular.
     * @return La posición exacta respecto al tiempo.
     */
    private double calcularPosicionX(double v0, double angulo, double t)
    {
        double tem = (v0 * Math.cos(Math.toRadians(angulo)) * t);
        
        return Math.round(tem);
    }
    
    /**
     * Calcular la posición exacta en Y.
     * @param v0 Velocidad inicial.
     * @param angulo Ángulo de inclinación.
     * @param h Altura sobre el piso.
     * @param g Gravedad.
     * @param t Tiempo que quiera calcular.
     * @return La posición exacta respecto al tiempo.
     */
    private double calcularPosicionY(double v0, double angulo, double h, double g, double t)
    {
        double tem =   (h + (v0 * Math.sin(Math.toRadians(angulo)) * t) - ((g * 0.5) * Math.pow(t, 2)));
        
        return Math.round(tem);
    }
    
    /**
     * Método para actualizar los parámetros de la simulación.
     * @param v0 Velocidad inicial.
     * @param angulo Ángulo de inclinación.
     * @param h Altura sobre el piso.
     * @param g Gravedad.
     */
    public void actualizarDatos(double v0, double angulo, double h, double g)
    {
        this.v0 = v0;
        this.angulo = angulo;
        this.h = h;
        this.g = g;
    }
}
