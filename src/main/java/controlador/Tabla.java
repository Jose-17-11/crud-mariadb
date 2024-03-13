package controlador;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabla extends JPanel {

	ModeloDatos modelo;
	JTable tabla;

	public Tabla() {
		setLayout(new BorderLayout());
		modelo = new ModeloDatos();
		tabla = new JTable(modelo);
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}
}
