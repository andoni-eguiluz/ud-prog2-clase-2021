package tema1.ejemplos;

public class OrdenarStrings {

	public static void main( String[] pars ) {
		java.lang.System.out.println( "Inicio de programa" );
		// String[] array1 = new String[5];
		String[] array1 = { "x", "b", "c", "hola", "bb" };  // Llamando al constructor new String[5]
		System.out.println( java.util.Arrays.toString( array1 ) );
		ordenar( array1 );
	}
	
	public static void ordenar( String[] array ) {
		// Tengo 2 arrays o tengo 1?
		for (int j=0; j<array.length-1; j++) {
			for (int i=0; i<array.length-1; i+=1) { // TODO ¿Se puede optimizar este bucle? (que haga las comparaciones necesarias, no más)
				// System.out.println( "i = " + i );
				if (array[i].compareTo(array[i+1]) > 0) {
					// System.out.println( "Dos elementos mal ordenados " + i );
					String temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
			System.out.println( java.util.Arrays.toString( array ) );
		}
	}
	
}
