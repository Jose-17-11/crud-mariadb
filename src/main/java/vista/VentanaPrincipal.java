package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class VentanaPrincipal extends JFrame {
	/**
	 * 
	 */
	JLabel titulo, description, description2, denegado;
	JButton create, read, update, delete;

	public VentanaPrincipal() {
//		ImageIcon icono = new ImageIcon(tecCuautla);
//		setIconImage(icono.getImage());
		
		setTitle("Administrador");
		setResizable(false);

		/*********************************
		 * Contenido del panel principal *
		 *********************************/

		titulo = new JLabel("Bienvenido");
		titulo.setBounds(570, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		description = new JLabel("Seleccione la accion que necesite realizar");
		description.setBounds(420, 60, 700, 100);
		description.setFont(new Font("Courier New", Font.BOLD, 16));
		description.setForeground(Color.WHITE);

		create = new JButton("Crear una fila");
		create.setBounds(320, 135, 150, 30);

		read = new JButton("Leer datos");
		read.setBounds(550, 135, 150, 30);

		update = new JButton("Actualizar datos");
		update.setBounds(780, 135, 150, 30);
		
		delete = new JButton("Eliminar producto");
		delete.setBounds(525, 265, 200, 30);
		

		denegado = new JLabel();
		denegado.setBounds(270, 330, 400, 50);
		denegado.setForeground(Color.WHITE);

		/********************************
		 * Creacion del panel principal *
		 ********************************/
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(description);
		panel.add(create);
		panel.add(read);
		panel.add(update);
		panel.add(delete);
//		Color de fondo del panel
		panel.setBackground(new Color(45, 45, 45));

		/*************************************************************************
		 * Conjunto de eventos que ocurren al precionar cualquier boton del menu *
		 *************************************************************************/

		/******* Agregar un nuevo maestro a la base de datos ***************/
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Create sm = new Create();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(15, 0, 700, 800);
			}
		});

		/******* Obtiene en una tabla todos los datos de las flores ***************/
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Read sm = new Read();
				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sm.setVisible(true);
				sm.setBounds(80, 250, 950, 400);
			}
		});
		/******* Eliminar un maestro de la base de datos ***************/
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				EliminarMaestro sm = new EliminarMaestro();
//				sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				sm.setVisible(true);
//				sm.setBounds(15, 0, 920, 400);
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					Delete sm = new Delete();
					sm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					sm.setVisible(true);
					sm.setBounds(15, 0, 650, 500);
				});
			}
		});
	}
}
