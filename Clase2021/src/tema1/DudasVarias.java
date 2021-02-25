package tema1;

import java.util.Date;
import java.util.Scanner;

public class DudasVarias {
	public static void main(String[] args) {
		// pruebas1();
		recArray();
	}
	
	public static void recArray() {
		int[] arrayEnts = { 1, 2, 3, 7, 9, 11 };
		for (int i=0; i<arrayEnts.length; i++) {
			System.out.println( "Posición " + i + " valor " + arrayEnts[i] );
		}
		for (int val : arrayEnts) {
			System.out.println( "Valor " + val );
		}
	}
	
	public static void pruebas1() {
		int i = 5;
		int j = 3;
		Date d = new Date();
		System.out.println( " " + "  " + (i + j) + "   "  + d );  // Concatenación de strings convierte a string
		// Input de teclado
		System.out.println( "Introduce un dato:");
		Scanner sc = new Scanner(System.in);
		String valor = sc.next();
		System.out.println( "Dato introducido: " + valor );
		int valorEntero = Integer.parseInt( valor );
		System.out.println( "Dato introducido duplicado: " + (valorEntero * 2) );
	}
}
