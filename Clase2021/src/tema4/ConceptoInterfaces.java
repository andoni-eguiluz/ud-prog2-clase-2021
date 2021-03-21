package tema4;

import java.util.ArrayList;

/** Ejemplo para entender el concepto de interfaces
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoInterfaces {

	public static void main(String[] args) {
		ArrayList<Mecanismo> listaMecs = new ArrayList<>();  // arraylist polimórfico
		listaMecs.add( new Sensor( "Temp" ) );
		listaMecs.add( new Sensor( "Luz" ) );
		listaMecs.add( new Blindaje( "Carcasa", 10 ) );
		listaMecs.add( new Micro( "Intel Core 2 Quad Q6600", 2.4*1024 ) );
		System.out.println( listaMecs );
		// ¿Cómo añado un test de reparación electrónica a mi lista de mecanismos? ¿Con muchos ifs o con unos pocos?
	}

}

abstract class Mecanismo {
	protected String nombre;
	public Mecanismo( String nombre ) {
		this.nombre = nombre;
	}
}

class Sensor extends Mecanismo {
	double medicion;
	public Sensor( String nombre ) {
		super( nombre );
		medicion = Math.random();  // Simulamos la medición con un aleatorio
	}
	public double getMedicion() { return medicion; }
	@Override
	public String toString() { 
		return "Sensor " + nombre + "=" + String.format( "%4.2f", getMedicion() ); 
	}
}

class Blindaje extends Mecanismo {
	int dureza;
	public Blindaje( String nombre, int dureza ) {
		super( nombre );
		this.dureza = dureza;
	}
	public int getDureza() {
		return dureza;
	}
	public void setDureza(int dureza) {
		this.dureza = dureza;
	}
	@Override
	public String toString() { 
		return "Blindaje con dureza " + dureza; 
	}
}

class Micro extends Mecanismo {
	double mHz;
	public Micro( String nombre, double mHz ) {
		super( nombre );
		this.mHz = mHz;
	}
	public double getmHz() {
		return mHz;
	}
	@Override
	public String toString() { 
		return "Micro a " + getmHz() + " MHz"; 
	}
}

