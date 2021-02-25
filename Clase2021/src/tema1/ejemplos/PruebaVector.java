package tema1.ejemplos;

import java.awt.Color;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de prueba de Vector2D
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaVector {
	
	/** Método principal de prueba de la clase
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// pruebasIniciales();
		creandoVectores();
	}
	
	private static void creandoVectores() {
		VentanaGrafica vent = new VentanaGrafica(600, 400, "Dibujo de vectores");
		Vector2D[] arrayVects = new Vector2D[200];
		int indiceArray = 0;
		Point clickInicial = null;
		// BOOLEANAS en cortocircuito
		// log1 || log2 || log3 ...   cuando cualquiera sea TRUE la condición es TRUE
		// log1 && log2 && log3 ... cuando cualquiera sea FALSE la condición es FALSE
		while (clickInicial==null || (clickInicial.getX() < 580 || clickInicial.getY() < 390)) {
			clickInicial = vent.getRatonPulsado();
			Point clickFinal = clickInicial;
			boolean puedeSerClick = true;
			while (clickFinal!=null) {
				if (!clickInicial.equals(clickFinal)) {
					puedeSerClick = false;
				}
				clickFinal = vent.getRatonPulsado();
				// System.out.println( clickInicial + " - " + clickFinal );
			}
			if (puedeSerClick && clickInicial!=null) {
				Vector2D vec = new Vector2D( clickInicial.getX(), clickInicial.getY() );
				arrayVects[indiceArray] = vec;
				indiceArray++;
				if (vec.getModulo()<100) {
					vec.dibujar( vent, Color.RED );
				} else if (vec.getModulo()<250) {
					vec.dibujar( vent, Color.ORANGE );
				} else {
					vec.dibujar( vent, Color.GREEN );
				}
				System.out.println( "Click en " + clickInicial );
				System.out.println( "Hay guardados " + indiceArray + " vectores" );
			}
			// System.out.println( clickInicial );
		}
		// Hacer algo con los vectores?
		// Visualizar solo los vectores que tengan longitud entre >= 100 y < 250
		for (int i=0; i<indiceArray; i++) {
			if (arrayVects[i].getModulo()>=100 && arrayVects[i].getModulo()<250) {
				System.out.println( "Debe ser naranja el " + arrayVects[i] );
			} else {
				System.out.println( "NO debe ser naranja el " + arrayVects[i] );
			}
		}
		System.out.println( "Fin!" );
	}
	
	// Pruebas iniciales con vectores
	private static void pruebasIniciales() {
		// Vector2D.x = 5.0;
		Vector2D v = new Vector2D( 300.0, 200.0 );
		//  v.setX( 3.0 );
		//  v.setY( 5.0 );
		System.out.println( v.getX() + "," + v.getY() );
		System.out.println( v );
		System.out.println( v.getModulo() );
		Vector2D v2 = new Vector2D();
		v2.setX( 200.0 );
		v2.setY( 70.0 );
		System.out.println( v2 );
		System.out.println( v );
		Vector2D v3 = v2;
		v3.setX( 15.0 );
		System.out.println( v3 );
		System.out.println( v2 );
		
		VentanaGrafica vent = new VentanaGrafica(600, 400, "Dibujo de vectores");
		v.dibujar( vent, Color.BLUE );
		v2.dibujar( vent, Color.RED );
		v3.dibujar( vent, Color.ORANGE );
		vent.espera( 5000 );  // Espera 5 segundos
		vent.acaba(); // Hacer esto cuando se quiera acabar la ventana (si se hace muy pronto casi ni se llegará a ver)
	}
	
}
