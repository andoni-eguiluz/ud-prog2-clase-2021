package tema1;

/** Ejemplo de Java para revisión de tipos primitivos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class TiposBasicos {
	
	// Mundo STATIC - de clase - solo una vez - siempre
	
	public static int datoEstatico;  // Este atributo es una variable que existe todo el tiempo del programa y solo una vez
	
	// Método principal
	public static void main(String[] params) {
		tiposPrimitivos( 3, '3' );
		expresiones();
		ambito(5);
		System.out.println( potencia(3) );
		System.out.println( potencia(3,4) );
		return;
	}

	// Revisión de tipos primitivos
	public static void tiposPrimitivos( int par1, char par2 ) { 
		// Hay 8 tipos primitivos
		// 4 son numéricos enteros
		byte varByte1;  // Declaración - ámbito del método en el que se declara
			// byte = 8 bits = números en rango -128 a 127
		varByte1 = 5;
		// varByte1 = 129;  // NO cabe 129 en un byte
		short varShort;  // 2 bytes -32768 a 32767
		int varInt; // 4 bytes 
		System.out.println( Integer.MIN_VALUE );  // Sacar a consola los valores mínimo y máximo de un int
		System.out.println( Integer.MAX_VALUE );
		long varLong; // 8 bytes
		varLong = 12345678901L;  // L sufijo para literal entero LONG (los literales enteros por defecto son int)
		System.out.println( Long.MIN_VALUE );  // A consola los long mínimo y máximo
		System.out.println( Long.MAX_VALUE );
		
		// Las declaraciones pueden ser con inicialización (se hace a menudo, es muy cómodo)
		int varInt2 = 17;
		
		// 2 tipos son numéricos reales
		float f1 = 3.2F;  // Por defecto 3.2 es double   (4 bytes)
		double d1 = 3.2;  // (8 bytes)
		float  f2 = (float) d1;  // conversión explícita
		System.out.println( Float.MIN_VALUE );
		System.out.println( Float.MAX_VALUE );
		System.out.println( Double.MIN_VALUE );
		System.out.println( Double.MAX_VALUE );
		
		// Los últimos 2 tipos primitivos:
		boolean varLogica = true; // false solo hay dos literales
		char car = '5';  // char entre comillas simples y solo un car
	}

	// Un poquito de expresiones:
	public static void expresiones() {
		// Aritméticas
		int i = 2 * 5;
		int j = i * i + 3;  // Precedencias (* antes que +)
		int k = (i + j) * 3;
		// Precedencia general de operadores:
		// ++ -- !      (los de mayor precedencia - los primeros que se ejecutan)
		// * / %
		// + -          producto antes que suma
		// > < >= <=
		// == !=
		// &&           AND (&&) antes que OR (||)
		// ||
		// =            (el de menor precedencia - el último que se ejecuta)
		
		// Booleanas
		boolean logico = !(i < j) && (5 >= i || 3 >= j);
	}
	
	
	public static void ambito(int par1) {
		// Cuántos ámbitos hay? Dos principales
		// - Todas las vabiables locales de este método, más los parámetros (ámbito de método)
		// - Los atributos de la clase (static en este caso)
		int varInt = 3; // Atención al nombre: esta vble y el parámetro son diferentes a los de tiposPrimitivos aunque se llamen igual
		int datoEstatico = 5; // Se declara una variable que se llama igual que el atributo... "oculta" al atributo
		datoEstatico *= 7;  // Esto accede a la vble local, no al atributo
		TiposBasicos.datoEstatico = 7; // Se puede seguir accediendo al atributo, con el PREFIJO DE CLASE (al ser static)
		System.out.println( datoEstatico );  // Esto es de nuevo la local (sin prefijo)
		
		// Se pueden hacer ámbitos particulares más internos con bloques dentro de bloques, por ejemplo:
		{
			byte varByte2; // Declaración - ámbito del bloque en el que se declara
		}
		// varByte2 = 1;  // Fuera del bloque no se puede acceder
	}

	// Ejemplo de dos métodos sobrecargados. Se llaman igual pero tienen distinta signatura (diferentes parámetros)
	public static long potencia( int base, int exponente ) {
		long resul = 1;
		for (int i=0; i<exponente; i=i+1) {
			resul *= base;
		}
		int i=0;
		while (i<exponente) {
			int j = 5;
			resul *= base;
			i=i+1;
			System.out.println( j );
		}
		return resul;
	}
	
	public static long potencia( int base ) {
		// return Math.pow(dato, dato);
		boolean esError = base>0;
		if (esError) {
			return base*base;
		} else {
			return 0;
		}
	}

	
	
	// Mundo NO STATIC - de objetos - ninguna, una, o muchas veces - solo cuando se crean objetos
	// (En este ejemplo no lo utilizamos porque no creamos objetos)
	
	public int atributoEntero;  // Atributo de la clase
	public void metodoDePrueba(int datoHabil) {  // Parámetro del método
		this.atributoEntero = 3;
		System.out.println( "Hola mundo" );
	}

}
