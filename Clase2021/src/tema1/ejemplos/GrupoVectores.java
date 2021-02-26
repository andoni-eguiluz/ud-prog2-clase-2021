package tema1.ejemplos;

import java.awt.Color;

public class GrupoVectores {
	private Vector2D[] arrayVects;
	private int indiceArray; // = 0;   inic. a cero por defecto

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
	
	/** Comprueba que los vectores naranjas tengan la longitud correcta, 
	 * muestra información a consola y devuelve info de error
	 * @return	true si todos los vectores naranjas tienen la longitud correcta, false en caso contrario
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
	
}
