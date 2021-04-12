package tema56.javaCollections;

import java.util.*;

/** Ejemplo de Java Collections
 * Colecciones de datos más esenciales
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoJC {
	public static void main(String[] args) {
		// Lista con genericidad (desde 1.5)
		ArrayList<String> l = new ArrayList<String>();
		l.add( "hola" );
		l.add( "adiós" );
		for (String s : l) {
			System.out.println( s.charAt(0) );
		}
		ArrayList<Integer> li = new ArrayList<Integer>();
		ArrayList<Object> lo = new ArrayList<Object>();
		lo.add( 5 );
		lo.add( "cinco" );
		ArrayList<Coloreable> lc = new ArrayList<Coloreable>();
		// Otras cosas son
		lo.add( 1, new Integer(2) );
		lo.remove( 0 ); // quita el elemento en la posición 0
		// Comparaciones se hacen con equals
		lo.remove( new Integer(5) );  // quita la primera aparición del elemento 5
		System.out.println( lo.contains( 2 ) );
		System.out.println( lo.indexOf( 2 ) );
		System.out.println( lo.size() );
		
		// Todas las JC se pueden combinar - cómo? con la genericidad
		ArrayList<ArrayList<Integer>> lli = new ArrayList<>();
		ArrayList<Integer> li2 = new ArrayList<>( Arrays.asList( 2, 3, 4 ) );
		li2.add( 7 );
		lli.add( li2 );
		
		
		// SETs - conjuntos
		int[] valores = { 1, 5, 7, 5, 109, 5, 13, 207, 7, 3640 };
		ArrayList<Integer> lis = new ArrayList<Integer>();
		HashSet<Integer> hs = new HashSet<>();
		TreeSet<Integer> ts = new TreeSet<>(); // Clases que sean Comparable
		for (int v : valores) {
			lis.add( v );
			hs.add( v );
			ts.add( v );
		}
		System.out.println( lis );
		System.out.println( hs );
		System.out.println( ts );
		
		// MAPAS - diccionarios - clave / valor
		HashMap<String,Integer> m1 = new HashMap<>();
		TreeMap<String,Integer> m2 = new TreeMap<>();
		
	}
}

interface Coloreable {
	void setColor();
}
