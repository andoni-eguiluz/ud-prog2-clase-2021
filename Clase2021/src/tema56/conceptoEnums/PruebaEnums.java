package tema56.conceptoEnums;

public class PruebaEnums {
	// Ejemplos
	// gama - alta, media, baja   (0, 1, 2)
	// zona - norte, sur, este, oeste
	// dia - lunes, martes, .... viernes
	// talla - xs, s, m, l, xl 
	// ...
	
	// Clásico: codificarlo en un int
	public static void main(String[] args) {
		Talla talla1 = Talla.L;  // referencia a un objeto constante
		Talla talla2 = Talla.S;
		System.out.println( talla1 + " - " + talla2 );
		// Enums - permiten conversión desde string
		String ts = "L";
		Talla talla3 = Talla.valueOf( ts );
		System.out.println( talla3 );
		// Es recorrible (Iterable)
		// values() -> array con todos los valores en el orden definido
		for (Talla talla : Talla.values()) {
			System.out.println( talla );
		}
		// Es Comparable
		if (talla1.compareTo(talla2)>0) {
			System.out.println( talla1 + " es mayor que " + talla2 );
		}
  	}
}
