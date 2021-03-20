package tema3.ejemplos.runner;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Juego runner ejercicio - con desplazamiento lateral de las naves añadido
 * Con bonus que funcionan como los asteroides pero en lugar de explotar la nave se gana tiempo de protección
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class RunnerNaves {

	private static final long MSGS_POR_FRAME = 20;    // 20 msgs por frame = 50 frames por segundo aprox
	private static final double RADIO_NAVE = 25;      // Radio de la nave (círculo imaginario)
	private static final int TAM_NAVE = 50;           // Píxel de tamaño de cada nave
	private static final double RADIO_MIN_ASTER = 25; // Radio mínimo del asteroide (círculo imaginario)
	private static final double RADIO_MAX_ASTER = 50; // Radio máximo del asteroide (círculo imaginario)
	private static final double VEL_Y_NAVES = 500;    // Velocidad (vertical) de desplazamiento de las naves
	private static final double VEL_X_NAVES = 400;    // Velocidad (horizontal) de desplazamiento de las naves
	private static final int X_MIN_NAVES = 0;         // Mínima x por la izquierda para las naves
	private static final int X_MAX_NAVES = 800;       // Máxima x por la derecha para las naves
	private static final double INC_VEL_NAVES = 50;   // Cada nave es un poquito más rápida que la anterior
	private static final int NUM_NAVES = 5;           // Número de naves del juego
	private static final int DIST_NAVES = 60;         // Píxels de distancia entre naves

	private static final int[] CANAL = { 100, 200, 300, 400, 500 };  // Píxels y de cada canal

	private static double VEL_MIN_ASTER = 200;        // Velocidad mínima del asteroide (píxels por segundo)
	private static double VEL_MAX_ASTER = 300;        // Velocidad máxima del asteroide (píxels por segundo)
	private static double PROB_NUEVO_AST = 0.02;      // Probabilidad de que aparezca un nuevo asteroide cada frame (2%)
	private static double PROB_NUEVO_BONUS = 0.02;    // Probabilidad de que aparezca un nuevo bonus cada frame (%)
	private static double VEL_JUEGO = 1.0;            // 1.0 = tiempo real. Cuando mayor, más rápido pasa el tiempo y viceversa 
	private static Random random;                     // Generador de aleatorios para creación de asteroides
	
	public static void main(String[] args) {
		RunnerNaves juego = new RunnerNaves();
		juego.jugar();
	}
	
	// =================================
	// ATRIBUTOS Y MÉTODOS DE INSTANCIA (no static)
	// =================================
	
	private VentanaGrafica vent;                  // Ventana del juego
	private ArrayList<ObjetoEspacial> elementos;  // Elementos del juego (tanto naves como asteroides)
	private ArrayList<Nave> naves;                // Naves del juego (solo las naves)
	private long tiempoNivel;                     // Tiempo inicial del nivel actual (milisegundos)
	
	public void jugar() {
		System.out.println( String.format( "Inicio juego. Asteroide %1$1.1f%% - Bonus %2$1.1f%%", PROB_NUEVO_AST*100.0, PROB_NUEVO_BONUS*100.0 ) );
		random = new Random();
		vent = new VentanaGrafica( 1200, 600, "Runner de naves" );
		crearFondos();
		crearNaves();
		mover();
	}

	// Crea los 3 fondos decorativos
	private void crearFondos() {
		elementos = new ArrayList<>();
		Fondo fondo1 = new Fondo( "/tema3/img/UD-roller.jpg", 0, 333, 1.0, 0.35f, 1000 );   // Imagen 1000x666 píxels
		Fondo fondo2 = new Fondo( "/tema3/img/UD-roller.jpg", 1000, 333, 1.0, 0.35f, 1000 );
		Fondo fondo3 = new Fondo( "/tema3/img/UD-roller.jpg", 2000, 333, 1.0, 0.35f, 1000 );
		fondo1.setVX( -200 );
		fondo2.setVX( -200 );
		fondo3.setVX( -200 );
		elementos.add( 0, fondo1 );
		elementos.add( 1, fondo2 );
		elementos.add( 2, fondo3 );  // Los tres primeros elementos son los fondos (se dibujan al principio de todo)
	}

	// Crea las 5 naves de inicio
	private void crearNaves() {
		int posNave = TAM_NAVE;
		naves = new ArrayList<>();
		for (int numNave=0; numNave<NUM_NAVES; numNave++) {
			Nave nave = new Nave( posNave, CANAL[2] );  // Nueva nave en canal intermedio
			nave.setCanal( 2 );
			elementos.add( nave );
			naves.add( nave );
			posNave += DIST_NAVES;
		}
	}
	
	// Bucle de movimiento
	private void mover() {
		long tiempoInicial = System.currentTimeMillis();
		tiempoNivel = System.currentTimeMillis();
		vent.setDibujadoInmediato( false );
		while (!vent.estaCerrada() && naves.size()>0) {
			// Manejo de teclado
			gestionTecladoYMovtoNaves();
			// Creación aleatoria de nuevo asteroide
			if (random.nextDouble()<PROB_NUEVO_AST) {
				creaNuevoAsteroide();
			}
			// Creación aleatoria de nuevo bonus
			if (random.nextDouble()<PROB_NUEVO_BONUS) {
				creaNuevoBonus();
			}
			// Movimiento de todos los elementos
			for (ObjetoEspacial objeto : elementos) {
				objeto.mueve( MSGS_POR_FRAME/1000.0 * VEL_JUEGO );
			}
			// Rotación de los bonus y los asteroides
			for (ObjetoEspacial objeto : elementos) {
				if (objeto instanceof Asteroide) {
					((Asteroide)objeto).rota( 0.1 );
				} else if (objeto instanceof Bonus) {
					((Bonus)objeto).rota( 0.1 );
				}
			}
			// Comprobación de parada de naves (ver si han llegado a su canal)
			for (Nave nave : naves) {
				miraSiEstaEnSuCanal(nave);
			}
			// Control de salida de pantalla de los asteroides y los bonus (y cambio de los fondos)
			for (int i=elementos.size()-1; i>=0; i--) {
				ObjetoEspacial oe = elementos.get(i);
				if (oe instanceof Asteroide) {
					Asteroide a = (Asteroide) oe;
					if (a.seSalePorLaIzquierda( vent )) {
						elementos.remove( a );
					}
				} else if (oe instanceof Bonus) {
					Bonus b = (Bonus) oe;
					if (b.seSalePorLaIzquierda( vent )) {
						elementos.remove( b );
					}
				} else if (oe instanceof Fondo) {
					Fondo f = (Fondo) oe;
					if (f.seSalePorLaIzquierda( vent )) {
						f.setX( f.getX() + 3000 );  // 1000 píxels de ancho cada fondo
					}
				}
			}
			// Choque naves con asteroides (todos con todos)
			for (int i=naves.size()-1; i>=0; i--) {  // OJO: Como se pueden borrar hay que recorrerlo en sentido inverso
				ObjetoEspacial oe = naves.get(i);
				if (oe instanceof Nave) {
					Nave nave = (Nave) oe;
					boolean choque = false;
					double tiempoBonus = 0.0;
					boolean cogeBonus = false;
					ArrayList<Bonus> lBonusChocan = new ArrayList<Bonus>();
					for (ObjetoEspacial oe2 : elementos) {
						if (oe2 instanceof Asteroide) {
							Asteroide a = (Asteroide) oe2;
							if (hayChoque(nave, a)) {
								choque = true;
								// break; No acabar bucle (miramos más colisiones por si también hay un bonus que protege)
							}
						} else if (oe2 instanceof Bonus) {
							Bonus b = (Bonus) oe2;
							if (hayChoque(nave, b)) {
								cogeBonus = true;
								tiempoBonus += b.getTiempoProteccion();
								lBonusChocan.add( b );
								// break; No acabar bucle (puede haber varias colisiones)
							}
						}
					}
					for (Bonus b : lBonusChocan) elementos.remove( b );  // Quitar los bonus que chocan
					if (cogeBonus) {
						nave.addProteccion( tiempoBonus );
					}
					if (choque && nave.getProteccion()<=0.0) {
						elementos.remove( nave );  // Fuera nave
						naves.remove( nave );  // Da igual aquí naves.remove( i );
					}
				}
			}
			// Dibujado
			vent.borra();
			int fondos = 0;
			for (ObjetoEspacial oe : elementos) {
				oe.dibuja( vent );
				fondos++;
				if (fondos==3) {
					dibujaBordes();
				}
			}
			vent.setMensaje( "Tiempo de juego: " + (System.currentTimeMillis() - tiempoInicial) );
			vent.repaint();
			// Ciclo de espera en cada bucle
			vent.espera( MSGS_POR_FRAME );
			// Subida de nivel
			subeNivel();
		}
	}
	
	private void gestionTecladoYMovtoNaves() {
		if (vent.isTeclaPulsada( KeyEvent.VK_PLUS )) {
			VEL_JUEGO *= 1.05;
			vent.setMensaje( "Nueva velocidad de juego: " + VEL_JUEGO );
		}
		if (vent.isTeclaPulsada( KeyEvent.VK_MINUS )) {
			VEL_JUEGO /= 1.05;
			vent.setMensaje( "Nueva velocidad de juego: " + VEL_JUEGO );
		}
		// Movimiento vertical jugador - mira a ver si están subiendo o bajando...
		boolean estanSubiendo = false;
		boolean estanBajando = false;
		for (Nave nave : naves) {
			if (nave.isSubiendo()) estanSubiendo = true;
			if (nave.isBajando()) estanBajando = true;
		}
		if (!estanSubiendo && !estanBajando) {  // Si están bajando o subiendo las naves siguen su camino hasta que lleguen al canal
			// Si ni están subiendo ni bajando entonces...
			if (vent.isTeclaPulsada( KeyEvent.VK_UP )) {
				double velocidad = -VEL_Y_NAVES;
				for (Nave nave : naves) {
					if (nave.getCanal()>0) {  // Si no está en el canal superior
						nave.setVY( velocidad );
						nave.setSubiendo( true );
						nave.setCanal( nave.getCanal()-1 );
					}
					velocidad -= INC_VEL_NAVES;
				}
			} else if (vent.isTeclaPulsada( KeyEvent.VK_DOWN )) {
				double velocidad = +VEL_Y_NAVES;
				for (Nave nave : naves) {
					if (nave.getCanal()<CANAL.length-1) {  // Si no está en el canal inferior
						nave.setVY( velocidad );
						nave.setBajando( true );
						nave.setCanal( nave.getCanal()+1 );
					}
					velocidad += INC_VEL_NAVES;
				}
			}
		}
		// Movimiento horizontal jugador
		double velX = 0.0;
		if (vent.isTeclaPulsada( KeyEvent.VK_LEFT )) {
			velX = -VEL_X_NAVES;  // Velocidad hacia la izquierda (negativa)
		} else if (vent.isTeclaPulsada( KeyEvent.VK_RIGHT )) {
			velX = VEL_X_NAVES;  // Velocidad hacia la derecha (positiva)
		}
		// Comprobación de parada en los extremos
		if (naves.get(0).getX() <= X_MIN_NAVES && velX<0) velX = 0.0;
		if (naves.get(naves.size()-1).getX() >= X_MAX_NAVES && velX>0) velX = 0.0;
		for (Nave nave : naves) {
			nave.setVX( velX );
		}
	}
	
	private void dibujaBordes() {
		int distCanales = (CANAL[1] - CANAL[0]) / 2;
		vent.dibujaRect( 0, distCanales, vent.getAnchura(), 2.0*CANAL.length*distCanales, 1.5f, Color.black );
		for (int canal=0; canal<CANAL.length-1; canal++) {
			int vertical = CANAL[canal] + distCanales;
			vent.dibujaLinea( 0, vertical, vent.getAnchura(), vertical, 0.5f, Color.blue );
		}
	}
	
	private void creaNuevoAsteroide() {
		double radioAleatorio = random.nextDouble() * (RADIO_MAX_ASTER - RADIO_MIN_ASTER ) + RADIO_MIN_ASTER;
		int canalAleatorio = random.nextInt( CANAL.length );
		Asteroide asteroide = new Asteroide( radioAleatorio, vent.getAnchura() + radioAleatorio, CANAL[canalAleatorio] );
		double velAleatoria = random.nextDouble() * (VEL_MAX_ASTER - VEL_MIN_ASTER ) + VEL_MIN_ASTER;
		asteroide.setVX( -velAleatoria );
		elementos.add( 3, asteroide );  // Se añade al principio para que las naves sean lo último que se dibujen (0,1,2 son los fondos)
	}
	
	private void creaNuevoBonus() {
		double tiempo = random.nextDouble() * 5; // Entre 0 y 5 segundos de protección - podríamos ponerlo configurable
		double radio = tiempo*10; // Proporcional al tiempo del bonus
		int canalAleatorio = random.nextInt( CANAL.length );
		Bonus bonus = new Bonus( radio, vent.getAnchura() + radio, CANAL[canalAleatorio], tiempo );
		double velAleatoria = random.nextDouble() * (VEL_MAX_ASTER - VEL_MIN_ASTER ) + VEL_MIN_ASTER;
		bonus.setVX( -velAleatoria );
		elementos.add( bonus );
	}
	
	// Choque entre nave y asteroide (simplificado con envolventes círculos)
	private boolean hayChoque( Nave nave, Asteroide asteroide ) {
		double dist = Fisica.distancia(nave.x, nave.y, asteroide.x, asteroide.y );
		return (dist < RADIO_NAVE + asteroide.getRadio());
	}
	
	// Choque entre nave y bonus (simplificado con envolventes círculos)
	private boolean hayChoque( Nave nave, Bonus bonus ) {
		double dist = Fisica.distancia(nave.x, nave.y, bonus.x, bonus.y );
		return (dist < RADIO_NAVE + bonus.getRadio());
	}
	
	// Determina si una nave ha llegado ya a su canal y la para si es el caso
	private void miraSiEstaEnSuCanal( Nave nave ) {
		if (nave.isSubiendo()) {
			if (nave.getY() <= CANAL[nave.getCanal()]) {
				nave.setY( CANAL[nave.getCanal()]);
				nave.setVY( 0.0 );
				nave.setSubiendo( false );
			}
		} else if (nave.isBajando()) {
			if (nave.getY() >= CANAL[nave.getCanal()]) {
				nave.setY( CANAL[nave.getCanal()]);
				nave.setVY( 0.0 );
				nave.setBajando( false );
			}
		}
	}
	
	// Sube el nivel cada 15 segundos (mayor dificultad)
	private void subeNivel() {
		if (System.currentTimeMillis() - tiempoNivel > 15000) {
			PROB_NUEVO_AST += 0.01;   // Sube un 1% la probabilidad de asteroide
			PROB_NUEVO_BONUS *= 0.8;  // Se baja un 20% la probabilidad de nuevo bonus
			VEL_MAX_ASTER += 10;      // Sube 10 píxels por segundo la velocidad máxima de asteroide
			tiempoNivel = System.currentTimeMillis();  // Nueva cuenta de tiempo
			System.out.println( String.format( "Nuevo nivel. Asteroide %1$1.1f%% - Bonus %2$1.1f%%", PROB_NUEVO_AST*100.0, PROB_NUEVO_BONUS*100.0 ) );
		}
	}

}
