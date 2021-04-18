package tema56.conceptoEnums;

/** Ejemplo de enum utilizado con switch (explicación de por qué hay que usar break; en los switch)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploEnumYSwitch {
	
	private static enum Mes { ENE, FEB, MAR, ABR, MAY, JUN, JUL, AGO, SEP, OCT, NOV, DIC };
	
	public static void main(String[] args) {
		cuantosDias( Mes.ENE );
		for (Mes mes : Mes.values()) {
			cuantosDias( mes );
		}
	}
	
	private static void cuantosDias( Mes mes ) {
		switch (mes) {
		    case ENE:
		    case MAR:
		    case MAY:
		    case JUL:
		    case AGO:
		    case OCT:
		    case DIC:
		        System.out.println( "El mes " + mes + " tiene 31 días" );
		        break;
		    case ABR:
		    case JUN:
		    case SEP:
		    case NOV:
		        System.out.println( "El mes " + mes + " tiene 30 días" );
		        break;
		    case FEB:
		        System.out.println( "Febrero tiene 28 o 29 días, depende." );
		        break;
		}
	}
}
