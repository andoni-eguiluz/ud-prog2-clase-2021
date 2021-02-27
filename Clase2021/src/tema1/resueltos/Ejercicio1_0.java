package tema1.resueltos;

public class Ejercicio1_0 {

	public static void main(String[] args) {
		ejercicioATablaMultiplicar();
		System.out.println();
		ejercicioBCDArrayNombres();
	}
	
	private static void ejercicioATablaMultiplicar() {
		cabecera();
		for (int i=0; i<10; i++) {
			System.out.print( "Tabla de " + i ); 
			for (int j=0; j<10; j++) {
				String espacios = " ";
				if (i*j<10) espacios = "  ";
				System.out.print( espacios + i*j ); 
			}
			System.out.println();
		}
	}
		// Saca a consola la cabecera de la tabla de multiplicar
		private static void cabecera() {
			System.out.print( "          " );
			for (int j=0; j<10; j++) {
				System.out.print( "  " + j ); 
			}
			System.out.println();
			System.out.println( "           -----------------------------" );
		}
	
	private static void ejercicioBCDArrayNombres() {
		String[] nombres = { "Andoni", "Sonia", "Mateo", "Aitziber", "Alberto", "Marta" };
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
		String nombreMenor = "zzz";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMenor)<0) {
				nombreMenor = nombres[i];
			}
		}
		System.out.println( "Nombre menor alfabético: " + nombreMenor );
		String nombreMayor = "A";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMayor)>0) {
				nombreMayor = nombres[i];
			}
		}
		System.out.println( "Nombre mayor alfabético: " + nombreMayor );
		ordenarNombres( nombres );
		System.out.println( "Lista ordenada:");
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
	}
	
		// Algoritmo de ordenación bubble sort
		private static void ordenarNombres( String[] nombres ) {
			for (int j=1; j<nombres.length; j++) {
				for (int i=0; i<nombres.length-j; i++) {
					if (nombres[i].compareTo(nombres[i+1])>0) {
						String temp = nombres[i];
						nombres[i] = nombres[i+1];
						nombres[i+1] = temp;
					}
				}
			}
		}
	


}
