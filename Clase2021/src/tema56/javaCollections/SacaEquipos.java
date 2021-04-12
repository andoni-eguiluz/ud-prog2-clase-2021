package tema56.javaCollections;

import java.util.ArrayList;

/** Clase de ejemplo de sacar equipos de liga de fútbol desde una web (marca).
 * Utilizado para practicar con Java Collections
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class SacaEquipos {

	public static void main(String[] args) {
		// Saca el código html del calendario de liga que coincide con una parte de los nombres de equipos
		ArrayList<String> l = ProcesaURLs.buscaEnWeb( "https://www.marca.com/futbol/primera-division/calendario.html", 
				"<img src=\"https://e00-marca.uecdn.es/assets/", 
				"iso-8859-15" );
		procesa( l );
	}

	// Método para hacer pruebas de Java Collections con los equipos de la liga de fútbol
	private static void pruebasDeJC( String equipo ) {
		
	}
	
	private static void procesa( ArrayList<String> lHtml ) {
		System.out.println( "Html encontrado:" );
		for (String html : lHtml) {
			System.out.println( html );
		}
		// Proceso de cada nombre de equipo
		System.out.println();
		System.out.println( "Equipos encontrados:" );
		for (String html : lHtml) {
			String equipo = sacaEquipoDeHtml( html );
			System.out.println( equipo );
			pruebasDeJC( equipo );
		}
	}
	
	private static String sacaEquipoDeHtml( String html ) {
		// Buscar esta parte: alt="Barcelona"/>   (o cualquier otro equipo)
		int posi1 = html.indexOf( "alt=\"" );
		int posi2 = html.indexOf( "\"", posi1+5 );
		// System.out.println( posi1 + " - " + posi2 );
		String equipo = html.substring( posi1+5, posi2 );
		return equipo;
	}

}
