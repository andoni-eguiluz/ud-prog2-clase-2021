package tema789;

import java.awt.*;
import javax.swing.*;

public class EjemploVentanas {
	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana.setSize(600, 400);
		ventana.setLocation(2000, 100);  // Pone la coordenada de la esquina sup izquierda
		ventana.setTitle( "Primera ventana");

		JFrame ventana2 = new JFrame();
		ventana2.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana2.setSize(600, 400);
		ventana2.setLocation(2200, 100);  // Pone la coordenada de la esquina sup izquierda
		ventana2.setTitle( "Segunda ventana");
		ventana2.setVisible( true );  // MAGIA!!!

		// Objetos contenedores (JFrame) y componentes (JButton)
		JPanel pPrincipal = new JPanel();
		ventana.add( pPrincipal );
		JButton boton = new JButton("Pulsa aquí!");
		pPrincipal.add( boton );
		JTextField tfPrueba = new JTextField( "Texto que se puede cambiar", 20 );
		pPrincipal.add( tfPrueba );
		
		ventana.setVisible( true );  // MAGIA!!!
		
		System.out.println( "fin" );
		
	}
}
