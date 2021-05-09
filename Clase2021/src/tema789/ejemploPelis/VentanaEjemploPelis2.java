package tema789.ejemploPelis;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/** Ejemplo de ventana de pelis trabajado en clase
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaEjemploPelis2 extends JFrame {

	private static ArrayList<Peli> listaPelis = new ArrayList<Peli>();
	
	// Main de prueba
	public static void main(String[] args) {
		initDatosEjemplo();
		VentanaEjemploPelis2 vent = new VentanaEjemploPelis2();
		vent.setVisible( true );
		System.out.println( "Final de main" );
	}

	private static void initDatosEjemplo() {
		listaPelis.add( new Peli( "Avatar", 2_847_246_203L, "avatar.jpg" ) );
		listaPelis.add( new Peli( "Avengers: Endgame", 2_797_501_328L, "endgame.jpg" ) );
		listaPelis.add( new Peli( "Titanic", 2_194_439_542L, "titanic.jpg" ) );
		listaPelis.add( new Peli( "Star Wars: The Force Awakens", 2_068_223_624L, "starwarsTFA.jpg" ) );
	}
	
	// No static 
	
	private JComboBox<Peli> cbPelis;
	private JTextField tfNombrePeli;
	private JTextField tfIngresosPeli;
	private JLabel lFoto;
	private JButton bNueva;
	private JButton bEditar;
	private JButton bConfirmar;
	private JButton bCancelar;
	private JTextArea taComentarios;

	public VentanaEjemploPelis2() {
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 800, 600 );
		setLocation( 2000, 0 );
		// this.setLocationRelativeTo( null );

		// Crear contenedores
		JPanel pSuperior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pCentralIzq = new JPanel();
		JPanel pInferior = new JPanel();

		// Crear componentes
		JLabel lTitulo = new JLabel( "Catálogo de películas" );
		bNueva = new JButton( "Nueva peli" );
		bEditar = new JButton( "Editar peli" );
		bConfirmar = new JButton( "Confirmar" );
		bCancelar = new JButton( "Cancelar" );
		JLabel lPelicula = new JLabel( "Película:" );
		JLabel lIngresos = new JLabel( "Ingresos brutos:" );
		tfNombrePeli = new JTextField( 15 );
		tfIngresosPeli = new JTextField( 8 );
		lFoto = new JLabel( "Foto" ); // new JLabel( new ImageIcon( "src/tema789/ejemploPelis/endgame.jpg" ) );
		// JLabelGrafico lFoto = new JLabelGrafico( "endgame.jpg", 600, 400 );  // Foto escalable
		taComentarios = new JTextArea();
		cbPelis = new JComboBox<>();
		for (Peli peli : listaPelis) {
			cbPelis.addItem( peli );
		}

		// Formato a contenedores
		pSuperior.setBackground ( Color.CYAN );
		pInferior.setBackground( Color.PINK );
		pInferior.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pCentral.setLayout( new BorderLayout() );
		pCentralIzq.setLayout( new BoxLayout( pCentralIzq, BoxLayout.Y_AXIS ) );

		// Formato de componentes
		lTitulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		bConfirmar.setEnabled( false );
		bCancelar.setEnabled( false );
		// tfNombrePeli.setMaximumSize( new Dimension( 1000, 25 ) );   // Si no se quiere que escalen en alto más de una altura
		// tfIngresosPeli.setMaximumSize( new Dimension( 1000, 25 ) );
		lFoto.setPreferredSize( new Dimension( 200, 400 ) );  // Para que la foto ocupe 400 píxeles de alto y 200 de ancho

		// Añadiendo componentes a contenedores
		add( pSuperior, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pSuperior.add( lTitulo );
		pInferior.add( bNueva );
		pInferior.add( bEditar );
		pInferior.add( bConfirmar );
		pInferior.add( bCancelar );
		pCentral.add( pCentralIzq, BorderLayout.WEST );
		pCentralIzq.add( cbPelis );
		pCentralIzq.add( lPelicula );
		pCentralIzq.add( tfNombrePeli );
		pCentralIzq.add( lIngresos );
		pCentralIzq.add( tfIngresosPeli );
		pCentralIzq.add( lFoto );
		pCentral.add( new JScrollPane(taComentarios), BorderLayout.CENTER );
		
		// Asignación de eventos
		// Opción 1 - clase externa
		// ActionListener escuchadorCombo = new EscuchadorCombo();
		// cbPelis.addActionListener( escuchadorCombo );
		// Opción 2 - clase interna
		// ActionListener escuchadorCombo = new EscuchadorComboInterna();
		// cbPelis.addActionListener( escuchadorCombo );
		// Opción 3 - clase interna anónima
		cbPelis.addActionListener( new ActionListener() {
			// Atributos
			public void actionPerformed(ActionEvent e) {
				// Actualizar nombre, importe, foto y comentarios de la peli seleccionada
				// Saber cuál es la peli seleccionada
				System.out.println( "actionPerformed " + e.getSource() );
				System.out.println( cbPelis.getSelectedItem() );
				Peli peliSel = (Peli) cbPelis.getSelectedItem();
				tfNombrePeli.setText( peliSel.getNombre() );
				tfIngresosPeli.setText( peliSel.getIngresos() + "" );
				taComentarios.setText( peliSel.getComentarios() );
				lFoto.setIcon( new ImageIcon( "bin/tema789/ejemploPelis/" + peliSel.getPoster() ) );
			}
		} ); 
		
		lFoto.addMouseListener( new MouseAdapter() {
//			Point pInicio;
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				System.out.println( "Released " + e );
//				System.out.println( "Drag de " + pInicio + " a " + e.getPoint() );
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				System.out.println( "Pressed " + e );
//				pInicio = e.getPoint();
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//				System.out.println( "Exited " + e );
//			}
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				System.out.println( "Entered " + e );
//			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println( "Clicked " + e );
			}
		});
		
		lFoto.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println( "Moved " + e );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println( "Drag " + e );
			}
		});
		
		tfNombrePeli.addKeyListener( new KeyListener() {
			boolean ctrlPulsado = false;
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println( "Typed " + e );
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println( "Released " + e );
				if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
					ctrlPulsado = false;
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println( "Pressed " + e );
				// Y si queremos controlar dos teclas?
				// Algo a no hacer nunca...  0:-)
				// while (!released) {
				// 	// esperar a segunda tecla
				// }
				// porque esto "congela" a Swing que ya no puede hacer otra cosa (probarlo y ver cómo la ventana se queda frita)
				if (e.getKeyCode()==KeyEvent.VK_CONTROL) {  // Si queremos controlar dos teclas no se puede hacer en un evento - hay que memorizar estado
					ctrlPulsado = true;
				}
				if (e.getKeyCode()==KeyEvent.VK_DELETE && ctrlPulsado) {  // Ejemplo: borrar cuadro de texto con Ctrl+Suprimir
					System.out.println( "Ctrl+Delete" );
					tfNombrePeli.setText( "" );
				}
			}
		});
		
	}

	// 2. Escuchador interno - CLASE INTERNA
	//   Acceso a sus propios atributos y también a los de la clase externa: 
	class EscuchadorComboInterna implements ActionListener {
		// Atributos de clase "interna"
		@Override
		public void actionPerformed(ActionEvent e) {
			// Actualizar nombre, importe, foto y comentarios de la peli seleccionada
			// Saber cuál es la peli seleccionada
			System.out.println( "actionPerformed " + e.getSource() );
			System.out.println( cbPelis.getSelectedItem() );
			Peli peliSel = (Peli) cbPelis.getSelectedItem();
			tfNombrePeli.setText( peliSel.getNombre() );
		}
	}


}

// Opción 1: Escuchador externo
class EscuchadorCombo implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// Actualizar nombre, importe, foto y comentarios de la peli seleccionada
		// Saber cuál es la peli seleccionada
		System.out.println( "actionPerformed " + e.getSource() );
	}
}
