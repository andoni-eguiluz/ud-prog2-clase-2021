package tema789.ejemplos.panelesHerencia;

import java.awt.Color;
import java.awt.Font;

import utils.ventanas.ventanaConsola.VentanaConsolaConBotones;
import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase lanzadora del programa de ventas
 * Ejemplo de uso de paneles personalizados para gestión de herencia con ventanas
 * (Versión con ventanas de ejemplo de ventas de consola de tema 3)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class MainVentasMovil {
	
	public static void main(String[] args) {
		Tienda tienda = new Tienda( "Deustienda" );
		// Datos de tienda
		tienda.addTrabajador( new Trabajador( "Inés", 0.9 ) );
		tienda.addTrabajador( new Trabajador( "Alberto", 0.4 ) );
		tienda.addTrabajador( new Trabajador( "Ana", 2.0 ) );
		for (Producto producto : PRODS) {
			tienda.addProducto( producto );
		}
//		simulaProcesoTienda( tienda );
		VentanaVenta vent = new VentanaVenta( tienda );
		vent.setVisible( true );
	}
	
	private static final Producto[] PRODS = {
		new Movil( "Lo nuevo de Samsung", 1359.00, "s20.png", "Samsung", "Galaxy S20" ),
		new Servicio( "Instalación apps", Servicio.TipoServicio.INSTALACION_APP ),
		new Movil( "Móvil sin Coltán", 450.00, "fairphone3.png", "Fairphone", "3" ),
		new Movil( "El 11 de Apple", 1329.00, "iphone11.png", "Apple", "IPhone 11 Pro" ),
		new Servicio( "Limpieza virus", Servicio.TipoServicio.LIMPIEZA_VIRUS ),
	};

}
