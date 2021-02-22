package tema1.ejemplos;

public class PruebaVector {
	public static void main(String[] args) {
		// Vector2D.x = 5.0;
		Vector2D v = new Vector2D();
		v.x = 3.0;
		v.y = 5.0;
		// System.out.println( v.x + "," + v.y );
		System.out.println( v );
		System.out.println( v.getModulo() );
		Vector2D v2 = new Vector2D();
		v2.x = 2.0;
		v2.y = 7.0;
		System.out.println( v2 );
		System.out.println( v );
		Vector2D v3 = v2;
		v3.x = 15.0;
		System.out.println( v3 );
		System.out.println( v2 );
	}
}
