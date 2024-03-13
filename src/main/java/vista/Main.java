package vista;

import javax.swing.JFrame;

public class Main {
	/********************************************************
	 * Metodo main que crea el frame principal *
	 ********************************************************/
	public static void main(String[] args) {
		VentanaPrincipal sm = new VentanaPrincipal();
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.setVisible(true);
		sm.setBounds(0, 0, 1250, 500);
	}
}