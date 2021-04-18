package tema56.resueltos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/** Ejemplo de excepciones con salida de cadena de llamadas a consola
 * @author andoni.eguiluz at deusto.es
 */
public class EjemploExcepcionesUrlCadenaLlamadas {

	private static int nivel = 0;  // Auxiliar para visualizar la profundidad de la llamada
	private static void llamada( String met, String pars ) {  // Auxiliar para visualizar llamadas
		nivel++;
		System.out.println( String.format( "%" + nivel*4 + "s", " ") + met + " (" + pars + ")" );
	}
	private static void retorno( String... ret ) {  // Auxiliar para visualizar llamadas
		if (ret.length>0) {
			System.out.println( String.format( "%" + nivel*4 + "s", " ") + "<- " + ret[0] );
		}
		nivel--;
	}
	
	/** Busca en una dirección web un substring determinado, y devuelve todas las líneas que lo contengan
	 * @param url	Dirección web
	 * @param busqueda	String a buscar
	 * @param charset	Juego de caracteres (para tildes y caracteres especiales)
	 * @return	Lista de todas las líneas que contienen ese String (vacía si no hay ninguna)
	 */
	public static ArrayList<String> buscaEnWeb( String url, String busqueda, String charset ) {
		llamada( "buscaEnWeb", url + "," + busqueda + "," + charset );
		try {
				// TODO Pendiente gestionar errores directos con devuelveTodasLasLineas
				ArrayList<String> lineas = devuelveTodasLasLineasSinExc( url, charset );
				ArrayList<String> lineasBuscadas = new ArrayList<>();
				for (String linea : lineas) {
					if (linea.contains( busqueda )) {
						lineasBuscadas.add( linea );
					}
				}
				retorno( "ArrayList de " + lineasBuscadas.size() + " líneas" );
				return lineasBuscadas;
		} catch (Exception e) {
			retorno( "AL-ERROR" );
			return new ArrayList<String>();
		}
	}
	
	/** Devuelve todas las líneas de texto de una página web
	 * @param url	Dirección web
	 * @param charset	Juego de caracteres (para las tildes)
	 * @return	Lista de líneas de esa dirección (en texto)
	 * @throws MalformedURLException	Error si la URL es incorrecta
	 * @throws IOException	Error al abrir la conexión
	 * @throws UnknownHostException	Error de servidor inexistente
	 * @throws FileNotFoundException	Error de acceso a página inexistente (en algunos servidores)
	 * @throws ConnectException	Error de timeout
	 */
	public static ArrayList<String> devuelveTodasLasLineas( String url, String charset )
	throws MalformedURLException, IOException, UnknownHostException, FileNotFoundException, ConnectException
	{
		ArrayList<String> ret = new ArrayList<>();
		BufferedReader input = null;
		InputStream inStream = null;
		URLConnection connection = (new URL(url)).openConnection();
		connection.setDoInput(true);
		inStream = connection.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream,charset));
		String line = "";
		while ((line = input.readLine()) != null) {
			ret.add( line );
		}
		inStream.close();
		input.close();
		return ret;
	}
	
	// Versión sin excepciones (para probar las diferencias)
	private static ArrayList<String> devuelveTodasLasLineasSinExc( String url, String charset ) {
		llamada( "devuelveTodasLasLineasSinExc", url + "," + charset );
		try {
			ArrayList<String> ret = devuelveTodasLasLineas(url,charset);
			retorno( "ArrayList de " + ret.size() + " líneas" );
			return ret;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		retorno( "null" );
		return null;
	}

	public static void main(String[] args) {
		llamada( "main", "" );
		sacarTiempoEnCiudadesDePrimera();
		retorno();
	}
	
	private static void sacarTiempoEnCiudadesDePrimera() {
		llamada( "sacarTiempoEnCiudadesDePrimera", "" );
		// De partida hemos explorado la web www.marca.com y vemos que
		// En la página https://www.marca.com/futbol/primera-division/calendario.html
		// Siempre está el calendario de la liga, y en él hay muchas líneas con la forma
		// <img src="https://e00-marca.uecdn.es/assets/sports ... alt="Girona"/>
		// Y el charset es iso-8859-15   (<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15"/>)
		// Hipótesis: buscamos todas esas líneas y encontraremos los equipos
		ArrayList<String> lineas = buscaEnWeb( "https://www.marca.com/futbol/primera-division/calendario.html", "<img src=\"https://e00-marca.uecdn.es/assets/sports", "iso-8859-15" );
		ArrayList<String> equipos = new ArrayList<>();
		for (String linea : lineas) {
			int pos1 = linea.indexOf( "alt=" );
			int pos2 = linea.indexOf( "\"", pos1+5 );
			if (pos1!=-1 && pos2!=-1) {
				equipos.add( linea.substring( pos1+5, pos2 ) );
			}
		}
		quitaRepetidos( equipos );
		System.out.println( "Lista de equipos: " + equipos );
		for (String equipo : equipos) {
			visualizaEquipo( equipo );
		}
		retorno();
	}
	
	private static void quitaRepetidos( ArrayList<String> equipos ) {
		HashSet<String> equiposNoRep = new HashSet<>();
		for (String equipo : equipos) equiposNoRep.add( equipo );
		equipos.clear();
		for (String equipo : equiposNoRep) equipos.add( equipo );
	}
	
	private static void visualizaEquipo( String equipo ) {
		llamada( "visualizaEquipo", equipo );
		try {
			int grados = getGradosDeCiudad( equipo );
			System.out.println( "Equipo " + equipo + " - temperatura " + grados + "º" );
		} catch (Exception e) {
			System.out.println( "[ATRAPADA EXCEPCIÓN DE ERROR EN GRADOS DE CIUDAD]" );
			System.out.println( "Equipo " + equipo + " - temperatura no encontrada" );
		}
		retorno();
	}
	
	private static int getGradosDeCiudad( String ciudad ) {
		llamada( "getGradosDeCiudad", ciudad );
		// De partida hemos explorado la web eltiempo.es y vemos que
		// Suele haber una URL  https://www.eltiempo.es/madrid.html   (con el nombre de la ciudad)
		// Y suele haber una línea que indica los grados:
		//    <span data-temp="9" ... 
		// Y el charset es UTF-8
		ArrayList<String> lineasGrados = buscaEnWeb( "https://www.eltiempo.es/" + ciudad + ".html", "span data-temp=\"", "UTF-8" );
		// La primera es la que vale
		String linea = lineasGrados.get(0);
		// Buscamos las dobles comillas y lo que hay en medio son los grados
		int primeraComilla = linea.indexOf( "span data-temp=\"" );
		int segundaComilla = linea.indexOf( '"', primeraComilla+16 );
		int temperatura = Integer.parseInt( linea.substring( primeraComilla+16, segundaComilla ) );
		retorno( "" + temperatura );
		return temperatura;
	}
	
}
