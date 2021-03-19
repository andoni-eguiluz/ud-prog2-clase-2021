package tema3.ejemplos;

import java.awt.Color;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Circulo extends Grafico {

	protected double radio;
	
	
	public Circulo( double x, double y, double radio, Color color ) {
		super(x,y);
		this.radio = radio;
		this.color = color;
	}
	
	@Override
	public void dibujar(VentanaGrafica vent, Color color, float grosor) {
		vent.dibujaCirculo( x, y, radio, grosor, color );
	}

	@Override
	public double distancia(Vector2D punto) {
		double dist = Math.sqrt( (punto.x-x)*(punto.x-x) + (punto.y-y)*(punto.y-y) );
		return dist - radio;  // Puede ser negativa
	}

	
}
