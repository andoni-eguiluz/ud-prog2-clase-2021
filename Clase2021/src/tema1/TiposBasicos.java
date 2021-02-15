package tema1;

public class TiposBasicos {
	// STATIC - de clase - solo una vez - siempre
	public static int datoEstatico;
	public static void main(String[] params) {
		tiposPrimitivos( 3, '3' );
		expresiones();
		ambito(5);
	}
	
	public static void expresiones() {
		// Aritméticas
		int i = 2 * 5;
		int j = i * i + 3;  // Precedencias
		boolean logico = !(i < j) && (5 >= i || 3 >= j);
		int k = (i + j) * 3;
		datoEstatico = 7;
	}
	
	public static void ambito(int par1) {
		int varInt = 3; // Esta vble y el parámetro son diferentes a los de tiposPrimitivos
		int datoEstatico = 5; // 
		datoEstatico *= 7;  // Accede a la vble local, no al atributo
		TiposBasicos.datoEstatico = 7; // Accede al atributo
		System.out.println( datoEstatico );
	}
	
	public static void tiposPrimitivos( int par1, char par2 ) { 
		// Declarar tipos primitivos
		// 4 son numéricos enteros
		byte varByte1;  // Declaración - ámbito del método en el que se declara
			// byte = 8 bits = números en rango -128 a 127
		varByte1 = 5;
		// varByte1 = 129;  // NO cabe 129 en un byte
		short varShort;  // 2 bytes -32768 a 32767
		int varInt; // 4 bytes 
		long varLong; // 8 bytes
		varLong = 12345678901L;  // L sufijo para literal entero LONG
		int varInt2 = 17; // Declaración con inicialización
		System.out.println( Integer.MIN_VALUE );
		System.out.println( Integer.MAX_VALUE );
		System.out.println( Long.MIN_VALUE );
		System.out.println( Long.MAX_VALUE );
		{
			byte varByte2; // Declaración - ámbito del bloque en el que se declara
		}
		// 2 son numéricos reales
		float f1 = 3.2F;  // Por defecto 3.2 es double   (4 bytes)
		double d1 = 3.2;  // (8 bytes)
		float  f2 = (float) d1;  // conversión explícita
		System.out.println( Float.MIN_VALUE );
		System.out.println( Float.MAX_VALUE );
		System.out.println( Double.MIN_VALUE );
		System.out.println( Double.MAX_VALUE );
		// 2 otros tipos primitivos:
		boolean varLogica = true; // false solo hay dos literales
		char car = '5';  // char entre comillas simples y solo un car
		// Ámbito de variables accesibles: son todas las locales de este método, más los parámetros,
		// más los atributos de la clase (STATIC)
	}
	
	// NO STATIC - de objetos - ninguna, una, o muchas veces - solo cuando se crean objetos
	
	public int atributoEntero;  // Atributo de la clase
	public void metodoDePrueba(int datoHabil) {  // Parámetro del método
		this.atributoEntero = 3;
		System.out.println( "Hola mundo" );
	}

}
