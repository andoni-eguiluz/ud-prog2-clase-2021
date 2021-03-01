package tema1.ejemplos;

import java.awt.Color;

public class GrupoVectores {
	private Vector2D[] arrayVects;
	private int indiceArray; // Nº de vectores guardados actualmente = 0;   inic. a cero por defecto

	public GrupoVectores( int tamanyo ) {
		arrayVects = new Vector2D[ tamanyo ];
		// indiceArray = 0;
	}
	
	/** Devuelve el número de vectores guardados en este grupo
	 * @return	Número actual de vectores
	 */
	public int size() {
		return indiceArray;
	}
	
	/** Añade un nuevo vector al final del grupo
	 * @param vec	Vector nuevo
	 */
	public void anyadir( Vector2D vec ) {
		arrayVects[indiceArray] = vec;
		indiceArray++;
	}
	
	/** Inserta un vector en el grupo (moviendo el resto de vectores de acuerdo al nuevo).
	 * Esta operación es errónea si el grupo ya está lleno, si el vector es nulo o si el índice está fuera de rango (menor que 0 o mayor que el size())
	 * @param indice	Posición en la que se va a insertar
	 * @param vec	Vector nuevo
	 * @return	true si la inserción ha sido correcta, false si no se ha podido hacer
	 */
	public boolean insertar( int indice, Vector2D vec ) {
		if (arrayVects.length==indiceArray) {
			return false;  // Error por array lleno
		}
		if (vec==null) {
			return false;  // Error por vector nulo
		}
		if (indice<0 || indice>indiceArray) {
			return false;  // Error por índice de inserción fuera de rango válido
		}
		for (int i=size(); i>indice; i--) {
			arrayVects[i] = arrayVects[i-1];
		}
		arrayVects[indice] = vec;
		indiceArray++;
		return true;
	}
	
	/** Borra un vector del grupo
	 * @param indice	Posición del elemento que queremos borrar
	 */
	public void borrar( int indice ) {
		for (int i=indice+1; i<indiceArray; i++) {
			arrayVects[i-1] = arrayVects[i];
		}
		arrayVects[indiceArray-1] = null;  // Opcional (más limpias las referencias del array)
		indiceArray--;
	}
	
	/** Comprueba que los vectores naranjas tengan la longitud correcta (en el rango [100, 250) píxels), 
	 * muestra información a consola y devuelve info de error
	 * @return	true si todos los vectores naranjas tienen la longitud correcta y todos los no naranjas no tienen esa longitud, false en caso contrario
	 */
	public boolean hayCorreccionNaranja() {
		boolean ret = true;
		for (int i=0; i<indiceArray; i++) {
			if (arrayVects[i].getModulo()>=100 && arrayVects[i].getModulo()<250) {
				System.out.println( "Debe ser naranja el " + arrayVects[i] );
				if (!arrayVects[i].getColor().equals(Color.ORANGE)) {
					ret = false;
				}
			} else {
				System.out.println( "NO debe ser naranja el " + arrayVects[i] );
				if (arrayVects[i].getColor().equals(Color.ORANGE)) {
					ret = false;
				}
			}
		}
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "[";
		for (int i=0; i<indiceArray; i++) {
			ret += (" " + arrayVects[i].toString());
		}
		return ret + " ]";
	}
	
}
