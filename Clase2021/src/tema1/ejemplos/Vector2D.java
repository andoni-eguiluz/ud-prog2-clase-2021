package tema1.ejemplos;

import java.awt.Color;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase para crear objetos de tipo vector en 2 dimensiones
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Vector2D {
	private double x; //  = 0.0; por omisión (valor 0 para los numéricos, char 0, false para los booleans)
	private double y;
	private Color color;

	// Este es el constructor implícito, el que hace Java si no hacemos constructor. En este caso también lo programamos
	/** Construye un nuevo vector 0,0
	 */
	public Vector2D() {
		// Constructor por defecto x y = 0
		// setX( 0.0 );  Podría hacerse pero en este caso no hace falta al ser el valor por defecto y no haber control de error en el setX
		// setY( 0.0 );  Podría hacerse pero en este caso no hace falta al ser el valor por defecto y no haber control de error en el setY
	}
	
	/** Construye un vector en coords cartesianas
	 * @param x	Coord x
	 * @param y	Coord y
	 */
	public Vector2D( double x, double y ) {
		this.setX( x ); // this.x = x;
		this.setY( y ); // this.y = y;
	}
	
	// Constructor indirecto 
	// TODO ¿Debería ser static?
	/** Construye un vector en coords polares
	 * @param modulo	Longitud del vector
	 * @param argumento	Ángulo del vector con respecto al eje OX positivo
	 * @return	Devuelve el vector creado
	 */
	public Vector2D crearVectorPolar( double modulo, double argumento ) {
		Vector2D ret = new Vector2D();
		ret.setX( modulo * Math.cos(argumento) );
		ret.setY( modulo * Math.sin(argumento) );
		return ret;
	}
	
	/** Calcula y devuelve el módulo del vector (longitud)
	 * @return	Devuelve el módulo del vector calculado por pitágoras
	 */
	public double getModulo() {
		return Math.sqrt( x*x + y*y );
	}
	
	/** Calcula y devuelve el argumento del vector (ángulo sobre el OX positivo hacia el OY positivo)
	 * @return	Ángulo de argumento del vector en radianes
	 */
	public double getArgumento() {
		return Math.atan2(y, x);
	}
	
	/** Devuelve la coordenada x
	 * @return	Coordenada x
	 */
	public double getX() {
		return this.x;
	}
	
	/** Cambia la coordenada x
	 * @param x	Nuevo valor de coordenada x
	 */
	public void setX( double x ) {
		this.x = x;
	}
	
	/** Devuelve la coordenada y
	 * @return	Coordenada y
	 */
	public double getY() {
		return y;
	}

	/** Cambia la coordenada y
	 * @param x	Nuevo valor de coordenada y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/** Convierte el vector a String, en formato (coord.x,coord.y)
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/** Escala un vector
	 * @param escala	Escala a aplicar, siendo 1.0 el 100%, menores que 1 más pequeños (0.5 la mitad), mayores que 1 más grandes (2.0 el doble)
	 */
	public void escala( double escala ) {
		x *= escala;
		y *= escala;
	}
	
	/** Dibuja un vector en una ventana gráfica, como una flecha desde el origen hasta sus coordenadas
	 * @param vent	Ventana en la que dibujar el vector
	 * @param color	Color de dibujado del vector
	 */
	public void dibujar( VentanaGrafica vent, Color color ) {
		vent.dibujaFlecha(0.0, 0.0, this.getX(), this.getY(), 1.0f, color, 12 );
		this.color = color;
	}
	
	/** Devuelve el último color en el que se ha dibujado el vector
	 * @return	color del último dibujado, null si nunca se ha dibujado
	 */
	public Color getColor() {
		return color;
	}
}
