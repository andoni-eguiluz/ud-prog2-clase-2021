package tema1.ejemplos;

public class Vector2D {
	public double x;
	public double y;
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public double getModulo() {
		return Math.sqrt( x*x + y*y );
	}
}
