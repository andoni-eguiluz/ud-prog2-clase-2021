package tema789;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaEjemplo extends JFrame {
	// Main de prueba
	public static void main(String[] args) {
		VentanaEjemplo vent = new VentanaEjemplo();
		vent.setVisible( true );
	}
	
	// No static 
	public VentanaEjemplo() {
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 600, 400 );
		setLocation( 2000, 100 );
		// this.setLocationRelativeTo( null );
		// Crear contenedores
		JPanel pSuperior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pInferior = new JPanel();
		// Crear componentes
		JLabel lTitulo = new JLabel( "HAWAI" );
		JButton bPeligro = new JButton( "Peligro!" );
		JButton bFalsaAlarma = new JButton( "Falsa alarma" );
		JLabel lUsuario = new JLabel( "Usuario:" );
		JLabel lPass = new JLabel( "Password:" );
		JTextField tfUsuario = new JTextField( 15 );
		JTextField tfPass = new JTextField( 8 );
		// Formato a contenedores
		pSuperior.setBackground ( Color.CYAN );
		pCentral.setBackground( Color.YELLOW );
		pInferior.setBackground( Color.PINK );
		pInferior.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pCentral.setLayout( new GridLayout( 2, 2 ) );
		// Formato de componentes
		lTitulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		bPeligro.setBackground( Color.RED );
		bFalsaAlarma.setBackground( Color.GREEN );
		bFalsaAlarma.setEnabled( false );
		lUsuario.setOpaque( true );
		lUsuario.setBackground( Color.BLUE );
		// Añadiendo componentes a contenedores
		add( pSuperior, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pSuperior.add( lTitulo );
		pInferior.add( bPeligro );
		pInferior.add( bFalsaAlarma );
		pCentral.add( lUsuario );
		pCentral.add( tfUsuario );
		pCentral.add( lPass );
		pCentral.add( tfPass );
	}
}
