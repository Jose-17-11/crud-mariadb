package controlador;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import modelo.PeticionesBD;

// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
public class ModeloDatos extends AbstractTableModel {
	String[] columnNames = { "Id", "Nombre", "Descripcion", "Precio", "Disponibilidad, id_Ventas"};
	PeticionesBD read = new PeticionesBD();
	List<String[]> listaDatos = read.read();

	// Esta clase imprime los datos en la consola
	class TablaListener implements TableModelListener {
		public void tableChanged(TableModelEvent evt) {
		}
	}

	// Constructor
	public ModeloDatos() {
		addTableModelListener(new TablaListener());
	}

	public int getColumnCount() {
		return columnNames.length; // Devuelve la cantidad de columnas
	}

	// Devuelve el número de filas de la tabla
	public int getRowCount() {
		return listaDatos.size(); // Usa el tamaño de la lista en lugar del array
	}

	// Devuelve el valor de una determinada casilla de la tabla
	// identificada mediante fila y columna
	public Object getValueAt(int fila, int col) {
		String[] filaDatos = listaDatos.get(fila); // Obtén la fila de la lista
		return filaDatos[col]; // Obtiene el valor de la columna específica
	}

	// Cambia el valor que contiene una determinada casilla de la tabla
	public void setValueAt(Object valor, int fila, int col) {
		String[] filaDatos = listaDatos.get(fila); // Obtén la fila de la lista
		filaDatos[col] = (String) valor; // Actualiza el valor en la fila
		// Indica que se ha cambiado
		fireTableDataChanged();
	}

	// Indica si la casilla identificada por fila y columna es editable
	public boolean isCellEditable(int fila, int col) {
		return true;
	}

	// Sobreescribe el método getColumnName para devolver nombres personalizados
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public List<String[]> getListaDatos() {
		return listaDatos;
	}
}