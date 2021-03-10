package tema3.ejemplos;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de prueba de Vector2D - incluye pequeño sistema de edición de vectores
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EditorDeGraficos {

	private static final int ANCHO_VENTANA = 600;
	private static final int ALTO_VENTANA = 400;
	private static final int NUM_MAX_VECTORES = 200;
	
	/** Método principal de prueba de la clase
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		edicionDeVectores();
	}
	
	private static void edicionDeVectores() {
		VentanaGrafica vent = new VentanaGrafica( ANCHO_VENTANA, ALTO_VENTANA, "Dibujo de vectores");
		// Poner esto si se quiere cambiar la posición inicial de la ventana:
		// vent.getJFrame().setLocation( 2000, 0 );
		GrupoVectores grupo = new GrupoVectores( NUM_MAX_VECTORES );
		Point clickInicial = null;
		vent.setMensaje( "Ctrl-click para añadir vectores, Alt-click para borrar, Click selecciona y otro click mueve" );
		vent.setDibujadoInmediato( false ); // Preparación de doble buffer
		Vector2D vSel = null;
		// BOOLEANAS en cortocircuito
		// log1 || log2 || log3 ...   cuando cualquiera sea TRUE la condición es TRUE
		// log1 && log2 && log3 ... cuando cualquiera sea FALSE la condición es FALSE
		while (!vent.estaCerrada() && (clickInicial==null || (clickInicial.getX() < 580 || clickInicial.getY() < 390))) {
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
				// Click
				// 2 ops distintas Ctrl o con Alt
				if (vent.isTeclaPulsada(KeyEvent.VK_ALT)) {
					// Comprobar si hay un vector en ese punto
					int vABorrar = comprobarVectorEnClick( grupo, clickInicial );
					if (vABorrar!=-1) {
						System.out.println( "Borrado el vector " + vABorrar );
						grupo.borrar(vABorrar);
						vSel = null;
					}
				} else if (vent.isTeclaPulsada( KeyEvent.VK_CONTROL)) {
					Vector2D vec = new Vector2D( clickInicial.getX(), clickInicial.getY() );
					grupo.anyadir( vec );
					if (vec.getModulo()<100) {
						vec.dibujar( vent, Color.RED );
					} else if (vec.getModulo()<250) {
						vec.dibujar( vent, Color.ORANGE );
					} else {
						vec.dibujar( vent, Color.GREEN );
					}
					vSel = null;
					System.out.println( "Click en " + clickInicial );
					System.out.println( "Hay guardados " + grupo.size() + " vectores" );
				} else {
					// Click sin ctrl ni alt
					int vASeleccionar = comprobarVectorEnClick( grupo, clickInicial );
					if (vASeleccionar!=-1) { // Cuando se hace sobre un vector - se selecciona
						vSel = grupo.get(vASeleccionar);
					} else {  // Se hace sobre el fondo
						if (vSel!=null) {  // Mover el vector ya seleccionado
							vSel.setXY( clickInicial );
						}
					}
				}
				// System.out.println( clickInicial );
			}
			// Repintar la ventana en cada frame:
			vent.borra();
			for (int i=0; i<grupo.size(); i++) {
				grupo.get(i).dibujar(vent, grupo.get(i).getColor() );
			}
			if (vSel!=null) {
				vSel.dibujar( vent, vSel.getColor(), 3.0f );
			}
			vent.repaint();  // Volcado de doble buffer (para pintar sin flickering -el cambio de pantalla es suave entre fotogramas-)
			vent.espera(20);  // Espera hasta el siguiente frame (20 msgs = 50 frames por segundo aprox.)  (1000/20 = 50)
		}
		// Hacer algo con los vectores?
		// Visualizar solo los vectores que tengan longitud entre >= 100 y < 250
		if (grupo.hayCorreccionNaranja()) {
			System.out.println( "Correcto! Los vectores naranjas son los que tienen que ser");
		} else {
			System.out.println( "Error: hay vectores incorrectos" );
		}
		System.out.println( "Fin!" );
		vent.acaba();
	}
	
	private static int comprobarVectorEnClick( GrupoVectores grupo, Point clickInicial ) {
		int vEnClick = -1;
		for (int i=0; i<grupo.size(); i++) {
			Vector2D temp = new Vector2D( clickInicial.getX(), clickInicial.getY() );
			// System.out.println( grupo.get(i).distancia( temp ) + " a " + clickInicial );
			// System.out.println( grupo.get(i).toca( temp, 5.0 ) + " a " + clickInicial );
			if (grupo.get(i).toca( temp, 5.0 )) {
				vEnClick = i;
			}
		}
		return vEnClick;
	}
	
}
