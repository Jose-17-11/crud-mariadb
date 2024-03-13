package vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ModeloDatos;

public class Read extends JFrame {
	JLabel description, denegado;
	JTable tabla;
	ModeloDatos modelo;

	public Read() {
		setTitle("Administrador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(45, 45, 45));

		description = new JLabel("Tabla de inventario");
		description.setBounds(10, 10, 200, 30);
		description.setForeground(Color.WHITE);
		panel.add(description);

		// Crear el modelo de datos y la tabla
		modelo = new ModeloDatos();
		tabla = new JTable(modelo);
		tabla.setBounds(10, 50, 600, 300);

		// Agregar la tabla a un JScrollPane para poder hacer scroll
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 50, 900, 300);
		panel.add(scrollPane);

		getContentPane().add(panel);
		setSize(940, 400);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setVisible(true);
	}
}
