package tema1.ejemplos;

/** Clase de prueba de Vector2D
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaVector {
	
	/** Método principal de prueba de la clase
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// Vector2D.x = 5.0;
		Vector2D v = new Vector2D( 3.0, 5.0 );
		// v.setX( 3.0 );
		// v.setY( 5.0 );
		System.out.println( v.getX() + "," + v.getY() );
		System.out.println( v );
		System.out.println( v.getModulo() );
		Vector2D v2 = new Vector2D();
		v2.setX( 2.0 );
		v2.setY( 7.0 );
		System.out.println( v2 );
		System.out.println( v );
		Vector2D v3 = v2;
		v3.setX( 15.0 );
		System.out.println( v3 );
		System.out.println( v2 );
	}
	
}
