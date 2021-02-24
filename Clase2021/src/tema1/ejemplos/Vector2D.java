package tema1.ejemplos;

public class Vector2D {
	private double x = 0.0;  // valor 0 si son numéricos
	private double y;
	
	/** Construye un nuevo vector 0,0
	 */
	public Vector2D() {
		// Constructor por defecto x y = 0
		setX( 0.0 );
		setY( 0.0 );
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
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

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
}
