package tema789;

import java.awt.*;
import javax.swing.*;

/** Ejemplo de ventana de login
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class VentanaLogin extends JFrame {
	// Static - método de prueba
	public static void main(String[] args) {
		VentanaLogin vl = new VentanaLogin( "Primer login" );
		vl.setLocation( 2000, 100 );
		vl.setVisible( true );
	}
	
	// No static - ventana que quiero crear
	public VentanaLogin( String titulo ) {
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 600, 400 );
		setTitle( titulo );
		// Creación de contenedores
		JPanel pSuperior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pInferior = new JPanel();
		// Creación de componentes
		JLabel lTitulo = new JLabel( "Identifícate" );
		JButton bAceptar = new JButton( "Aceptar" );
		JButton bCancelar = new JButton( "Cancelar" );
		JLabel lNick = new JLabel( "Nombre:" );
		JLabel lPassword = new JLabel( "Password:" );
		JTextField tfNick = new JTextField( 15 );
		JTextField tfPass = new JTextField( 10 );
		// Configuración de layouts
		pSuperior.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pCentral.setLayout( new GridLayout( 2, 2 ) );
		// Configuración de contenedores y componentes
		pSuperior.setBackground( Color.CYAN );
		pCentral.setBackground( Color.YELLOW );
		pInferior.setBackground( new Color( 235, 235, 235 ) );
		lTitulo.setFont( new Font( "Arial", Font.ITALIC, 26 ) );
		lTitulo.setBackground( Color.RED );
		lTitulo.setOpaque( true );
		// Asignación de componentes y contenedores a la ventana
		add( pSuperior, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pSuperior.add( lTitulo );
		pInferior.add( bAceptar );
		pInferior.add( bCancelar );
		pCentral.add( lNick );
		pCentral.add( tfNick );
		pCentral.add( lPassword );
		pCentral.add( tfPass );
	}
}
