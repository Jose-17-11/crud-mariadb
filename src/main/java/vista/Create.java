package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import modelo.PeticionesBD;


//Clase funcional con la base de datos, solo falta renombrar variables
public class Create extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id, nombre, precio, descripcion, disponibilidad, idVentas;
	JLabel titulo, n1, n2, n3, n4, n5, n6, denegado;
	JTextField text1, text2, text3, text4, text5, text6;
	JButton crear;

	// Metodo constructor principal que mostrara todo el formulario
	public Create() {
//		ImageIcon icono = new ImageIcon(tecCuautla);
//		setIconImage(icono.getImage());
		setTitle("Login");
		// Logo del tec de cuautla
//		JLabel imagenLabel = new JLabel();
//		ImageIcon imagenIcon = new ImageIcon(tecCuautla);
//		imagenLabel.setIcon(imagenIcon);
//		imagenLabel.setBounds(75, 50, 100, 79);

		// Logo del Instituto Tecnologico Nacional de Mexico
//		JLabel imagenLabel2 = new JLabel();
//		ImageIcon imagenIcon2 = new ImageIcon(tecNM);
//		imagenLabel2.setIcon(imagenIcon2);
//		imagenLabel2.setBounds(500, 50, 190, 79);

		/***********************
		 * Inputs de Create *
		 ***********************/

		titulo = new JLabel("Crear nueva planta");
		titulo.setBounds(280, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);

		n1 = new JLabel("Id");
		n1.setBounds(330, 45, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(240, 120, 220, 30);

		n2 = new JLabel("Nombre");
		n2.setForeground(Color.WHITE);
		n2.setBounds(320, 135, 200, 100);
		text2 = new JTextField(10);
		text2.setBounds(240, 210, 220, 30);

		n3 = new JLabel("Descripción");
		n3.setForeground(Color.WHITE);
		n3.setBounds(320, 225, 200, 100);
		text3 = new JTextField(10);
		text3.setBounds(240, 300, 220, 30);

		n4 = new JLabel("Precio");
		n4.setForeground(Color.WHITE);
		n4.setBounds(320, 315, 200, 100);
		text4 = new JTextField(10);
		text4.setBounds(240, 390, 220, 30);

		n5 = new JLabel("Disponibilidad");
		n5.setForeground(Color.WHITE);
		n5.setBounds(320, 405, 200, 100);
		text5 = new JTextField(10);
		text5.setBounds(240, 480, 220, 30);

		n6 = new JLabel("Id Ventas");
		n6.setForeground(Color.WHITE);
		n6.setBounds(320, 495, 200, 100);
		text6 = new JTextField(10);
		text6.setBounds(240, 570, 220, 30);
		
		crear = new JButton("Crear");
		crear.setBounds(275, 660, 150, 30);
		
		denegado = new JLabel();
		denegado.setBounds(250, 260, 400, 50);
		denegado.setFont(new Font("Arial", Font.BOLD, 12));
		denegado.setForeground(new Color(0, 0, 0));

		/********************************************************
		 * Creacion del panel y agregacion de todo el contenido *
		 ********************************************************/

		Container panel = getContentPane();
		panel.setBackground(new Color(45, 45, 45));
		LineBorder borde = new LineBorder(Color.BLACK, 2);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(n1);
		panel.add(n2);
		panel.add(n3);
		panel.add(n4);
		panel.add(n5);
		panel.add(n6);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(text5);
		panel.add(text6);
		panel.add(denegado);
		panel.add(crear);

		/****************************************
		 * Evento del boton para iniciar sesion *
		 ****************************************/

		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = text1.getText();
				nombre = text2.getText();
				descripcion = text3.getText();
				precio = text4.getText();
				disponibilidad = text5.getText();
				idVentas= text6.getText();
				
				PeticionesBD create = new PeticionesBD();
				try {
					create.create(id, nombre, descripcion, precio, disponibilidad, idVentas);
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
