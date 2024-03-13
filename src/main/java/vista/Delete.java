package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import modelo.PeticionesBD;

public class Delete extends JFrame {
	JLabel titulo, n1, denegado;
	JTextField text1;
	JButton delete;
	String id;

	public Delete() {
//		ImageIcon icono = new ImageIcon(tecCuautla);
//		setIconImage(icono.getImage());
		
		setTitle("Administrador");
		setResizable(false);

		/*********************************
		 * Contenido del panel principal *
		 *********************************/

		titulo = new JLabel("Seccion de borrado");
		titulo.setBounds(270, 0, 400, 100);
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setForeground(Color.WHITE);
		
		n1 = new JLabel("Id");
		n1.setBounds(330, 45, 200, 100);
		n1.setForeground(Color.WHITE);
		text1 = new JTextField(10);
		text1.setBounds(240, 120, 220, 30);
		
		delete = new JButton("Eliminar producto");
		delete.setBounds(250, 175, 200, 30);
		
		Container panel = getContentPane();
		LineBorder borde = new LineBorder(Color.BLACK, 1);
		((JComponent) panel).setBorder(borde);
		panel.setLayout(null);
		panel.add(titulo);
		panel.add(delete);
		panel.add(n1);
		panel.add(text1);
		panel.setBackground(new Color(45, 45, 45));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = text1.getText();
				PeticionesBD delete = new PeticionesBD();
				try {
					delete.delete(id);
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
