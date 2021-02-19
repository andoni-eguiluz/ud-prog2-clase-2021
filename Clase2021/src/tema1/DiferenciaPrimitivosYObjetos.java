package tema1;

// import java.lang.*;   // Es como si lo hiciéramos siempre (no hace falta) - por eso tenemos acceso directo a String, System...
import java.util.Date;   // Para acceder a Date si hace falta este import, o si no siempre habría que llamarla java.util.Date

/** Ejemplo de Java para diferencia de tipos primitivos y objetos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class DiferenciaPrimitivosYObjetos {

	// Método principal
	public static void main(String[] a) {
		difsEntreValorYReferencia();
		pasoParsRef();
	}
	
	public static void difsEntreValorYReferencia() {
		int i;
		i = 5;
		int j = i;
		cambiaI( i );  // Paso de tipo primitivo a un método
		System.out.println( i );
		if (i==j) {
			System.out.println( "Los dos enteros Son iguales");
		} else {
			System.out.println( "Los dos enteros NO son iguales" );
		}
		// Referencias
		String string1;
		string1 = new String( "hola" ); // "hola";
		String string2 = "ho";
		string2 = string2 + "la";
		if (string1==string2) {
			System.out.println( "Los strings s1 y s2 son iguales");
		} else {
			System.out.println( "Los strings s1 y s2 NO son iguales" );
		}
		if (string1.equals(string2)) {
			System.out.println( "Los strings s1 y s2 contienen lo mismo");
		}
		String s3 = string1;
		if (s3==string1) {
			System.out.println( "Iguales s1 y s3");
		}
	}

	// Los tipos primitivos se pasan por valor: cambiar el parámetro no cambia la variable original (i)
	public static void cambiaI( int dato ) {
		dato = dato + 10;
		System.out.println( dato );
	}

	// Paso de objetos: por referencia. Obsérvese...
	public static void pasoParsRef() {
		Date fecha;
		fecha = new Date();
		System.out.println( "Fecha antes de llamada: " + fecha );
		cambiaFecha( fecha );  // ¿Puede cambiar el objeto esta llamada?
		System.out.println( "Fecha tras llamada: " + fecha );
	}
	
	public static void cambiaFecha( Date f ) {
		f.setTime( f.getTime() + 1000*60*60*24 );  // Sí, porque aquí f y fecha son alias - referencias sinónimas. Apuntan al mismo objeto
		System.out.println( "Fecha cambiada en llamada: " + f );
	}	
	
}
