package tema3.ejemplos.runner;
import java.awt.Color;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase que permite crear y gestionar naves y dibujarlas en una ventana gráfica
 */
public class Nave extends ObjetoEspacial {
	
	// =================================================
	// PARTE DE OBJETO (NO STATIC)
	// =================================================
	
	private boolean subiendo;      // true si la nave está subiendo
	private boolean bajando;       // false si la nave está bajando
	private int canal;             // Número de canal en el que está la nave
	private double segsProteccion; // Segundos que tiene de "escudo" protector la nave

	/** Crea una nueva nave
	 * @param x	Coordenada x del centro de la nave (en píxels)
	 * @param y	Coordenada y del centro de la nave (en píxels)
	 */
	public Nave( double x, double y ) {
		super( x, y );
		subiendo = false;
		bajando = false;
		segsProteccion = 0.0;
	}
	
	/** Devuelve el tiempo de protección que le queda a la nave
	 * @return	tiempo de protección en segundos
	 */
	public double getProteccion() {
		return segsProteccion;
	}

	/** Cambia la información de canal de la nave
	 * @param proteccion	Tiempo de protección a añadir (en segundos)
	 */
	public void addProteccion( double proteccion ) {
		this.segsProteccion += proteccion;
	}
	
	/** Mueve la nave y modifica el tiempo de protección (si procede)
	 * @param	segs	Tiempo de movimiento (se resta al tiempo de protección)
	 * @see tema3.ejemplos.runner.ObjetoEspacial#mueve(double)
	 */
	@Override
	public void mueve(double segs) {
		super.mueve(segs);
		if (segsProteccion>0.0) {
			segsProteccion -= segs;
			if (segsProteccion<0.0) segsProteccion = 0.0;
		}
	}
	
	/** Devuelve el canal de la nave
	 * @return	canal de la nave (0-4)
	 */
	public int getCanal() {
		return canal;
	}

	/** Cambia la información de canal de la nave
	 * @param canal	Nuevo canal de la nave (0-4)
	 */
	public void setCanal(int canal ) {
		this.canal = canal;
	}
	
	/** Devuelve la información de si la nave está subiendo
	 * @return	true si está subiendo, false si no está subiendo
	 */
	public boolean isSubiendo() {
		return subiendo;
	}

	/** Cambia la información de si la nave está subiendo
	 * @param subiendo	true si está subiendo, false si no está subiendo
	 */
	public void setSubiendo( boolean subiendo ) {
		this.subiendo = subiendo;
	}

	/** Devuelve la información de si la nave está bajando
	 * @return	true si está bajando, false si no está bajando
	 */
	public boolean isBajando() {
		return bajando;
	}

	/** Cambia la información de si la nave está bajando
	 * @param subiendo	true si está bajando, false si no está bajando
	 */
	public void setBajando( boolean bajando ) {
		this.bajando = bajando;
	}

	/** Dibuja la nave en una ventana, con inclinación hacia arriba si está subiendo, hacia abajo si está bajando, o sin inclinación si ni sube ni baja
	 * @param v	Ventana en la que dibujar la nave
	 */
	@Override
	public void dibuja( VentanaGrafica v ) {
		double angulo = 0.0;
		if (subiendo) {
			angulo = -0.2;  // la nave quiere dibujarse subiendo 0.2 radianes
		} else if (bajando) {
			angulo = +0.2;  // la nave quiere dibujarse bajando 0.2 radianes
		}
		String imagen = "/tema3/img/nave.png";    // Dibujo de nave normal
		if (vX>0.0) {
			imagen = "/tema3/img/nave-prop.png";  // Si la nave avanza gráfico con propulsores traseros
		} else if (vX<0.0) {
			imagen = "/tema3/img/nave-frena.png"; // Si la nave frena gráfico con propulsores delanteros
		}
		v.dibujaImagen( imagen, x, y, 50.0/500.0, angulo, 1.0f );  // el gráfico tiene 500 píxels y la nave quiere dibujarse con 50
		if (segsProteccion>0.0) {
			float transp = 1.0f;
			imagen = "/tema3/img/escudo.png";
			if (segsProteccion<2.0) transp = (float) (segsProteccion/2.0);  // Los últimos 2 segundos decrece visualmente la opacidad del escudo
			v.dibujaImagen( imagen, x, y, 50.0/500.0, 0.0, transp );  // burbuja escudo (transparente creciente en el último segundo)
		}
		if (DIBUJA_ENVOLVENTE) v.dibujaCirculo( x, y, 25, 2f, Color.orange );  // Pintado a título de referencia de prueba
	}
	
	@Override
	public String toString() {
		return "Nave " + x + "," + y + " (" + (subiendo?"subiendo":"") + (bajando?"bajando":"") + ")";
	}
	
	/** Comprueba si la nave es igual a otro objeto dado. Se entiende que dos naves son iguales
	 * cuando las coordenadas de sus centros (redondeadas a enteros) son iguales
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Nave) {
			Nave p2 = (Nave) obj;  // Cast de obj a nave2 (lo entenderemos mejor al ver herencia)
			return Math.round(p2.x)==Math.round(x) && Math.round(p2.y)==Math.round(y); // Devuelve true o false dependiendo de las coordenadas de las naves this y p2
		} else {
			return false;
		}
	}
	
}
