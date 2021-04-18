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
import java.util.HashSet;

public class EjemploExcepcionesUrl {
	
	/** Busca en una dirección web un substring determinado, y devuelve todas las líneas que lo contengan
	 * @param url	Dirección web
	 * @param busqueda	String a buscar
	 * @param charset	Juego de caracteres (para tildes y caracteres especiales)
	 * @return	Lista de todas las líneas que contienen ese String (vacía si no hay ninguna)
	 * 			Devuelve null si hay algún error
	 */
	public static ArrayList<String> buscaEnWeb( String url, String busqueda, String charset )
	throws MalformedURLException, IOException, UnknownHostException, FileNotFoundException, ConnectException
	{
		// TODO Pendiente gestionar errores directos con devuelveTodasLasLineas
		ArrayList<String> lineas = devuelveTodasLasLineas( url, charset );
		ArrayList<String> lineasBuscadas = new ArrayList<>();
		for (String linea : lineas) {
			if (linea.contains( busqueda )) {
				lineasBuscadas.add( linea );
			}
		}
		return lineasBuscadas;
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
		try {
			return devuelveTodasLasLineas(url,charset);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			sacarTiempoEnCiudadesDePrimera();
		} catch (Exception e) {
			System.err.println( "Error desconocido en programa " );
			e.printStackTrace();
		}
	}
	
	private static void sacarTiempoEnCiudadesDePrimera() {
		// De partida hemos explorado la web www.marca.com y vemos que
		// En la página https://www.marca.com/futbol/primera-division/calendario.html
		// Siempre está el calendario de la liga, y en él hay muchas líneas con la forma
		// <img src="https://e00-marca.uecdn.es/assets/sports ... alt="Girona"/>
		// Y el charset es iso-8859-15   (<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15"/>)
		// Hipótesis: buscamos todas esas líneas y encontraremos los equipos
		try {
			ArrayList<String> lineas = buscaEnWeb( "https://www.marca.com/futbol/primera-division/calendario.html", 
					"<img src=\"https://e00-marca.uecdn.es/assets/sports", "iso-8859-15" );
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
		} catch (Exception e) {
			System.out.println( "No se ha podido establecer conexión con el listado de equipos." );
		}
	}
	
	private static void quitaRepetidos( ArrayList<String> equipos ) {
		HashSet<String> equiposNoRep = new HashSet<>();
		for (String equipo : equipos) equiposNoRep.add( equipo );
		equipos.clear();
		for (String equipo : equiposNoRep) equipos.add( equipo );
	}
	
	private static void visualizaEquipo( String equipo ) {
		// Aquí gestiono el error de acceso a web incorrecta
		try {
			int grados = getGradosDeCiudad( equipo );
			System.out.println( "Equipo " + equipo + " - temperatura " + grados + "º" );
		} catch (MalformedURLException e) {
			// Tratamiento una (para que veáis cómo se hacen varios catch)
			System.out.println( "Equipo " + equipo + " - no se ha encontrado temperatura" );
		} catch ( IOException | NullPointerException exc) { // Si hay error viene aquí
			// Solo se ejecuta cuando se produce un NPE en el código del try
			System.out.println( "Equipo " + equipo + " - no se ha encontrado temperatura" );
		}
		// Seguimos si no hay error y también si hay error
	}
	
	private static int getGradosDeCiudad( String ciudad ) 
	throws MalformedURLException, IOException, UnknownHostException, FileNotFoundException, ConnectException
	{
		// De partida hemos explorado la web eltiempo.es y vemos que
		// Suele haber una URL  https://www.eltiempo.es/madrid.html   (con el nombre de la ciudad)
		// Y suele haber una línea que indica los grados:
		//    <span data-temp="9" ... 
		// Y el charset es UTF-8
		ArrayList<String> lineasGrados = buscaEnWeb( "https://www.eltiempo.es/" + ciudad + ".html", "data-temp", "UTF-8" );
		// La primera es la que vale
		String linea = lineasGrados.get(0);
		// Buscamos las dobles comillas y lo que hay en medio son los grados
		int primeraComilla = linea.indexOf( "span data-temp=\"" );
		int segundaComilla = linea.indexOf( '"', primeraComilla+16 );
		int temperatura = Integer.parseInt( linea.substring( primeraComilla+16, segundaComilla ) );
		return temperatura;
	}
	
}
