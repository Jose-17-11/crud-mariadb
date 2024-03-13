package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeticionesBD {

	private static ConexionBD conexion;

	// Conexion a la base de datos
	public PeticionesBD() {
		conexion = new ConexionBD();
	}
	/* Método que crea un nuevo alumno */
	public String create(String Id_Producto, String cNombre_Producto, String cDescripcion, String cPrecio, String cStock_Disponible, String idVentas)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM productos");
			String consulta = "INSERT INTO productos (Id_Producto, cNombreProducto, cDescripcion, cPrecio, cStock_Disponible, idVentas) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, Id_Producto);
			pstmt.setString(2, cNombre_Producto);
			pstmt.setString(3, cDescripcion);
			pstmt.setString(4, cPrecio);
			pstmt.setString(5, cStock_Disponible);
			pstmt.setString(6, idVentas);
			
			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Alumno agregado con éxito.");
			} else {
				return ("No se pudo agregar el Alumno.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			conexion.desconectar(cn, stm, rs);
			return ("El alumno que intenta ingresar ya se encuentra registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return "";
	}
	
	/* Método que obtiene todos los alumnos un nuevo alumno */
	public List<String[]>  read() {
	    Connection cn = null;
	    Statement stm = null;
	    ResultSet rs = null;

	    try {
	        cn = conexion.conectar();
	        stm = cn.createStatement();
	        rs = stm.executeQuery("SELECT * FROM productos");
	        List<String[]> listaDatos = new ArrayList<>();
	        while (rs.next()) {
	            String id = rs.getString(1);
	            String nombre = rs.getString(2);
	            String descripcion = rs.getString(3);
	            String precio = rs.getString(4);
	            String esxistencias = rs.getString(5);
	            String idVentas = rs.getString(6);
//	            System.out.println(matricula + " | " + nombre + " | " + apellidoP + " | " + apellidoM);
	            String tabla[] = { id, nombre, descripcion, precio, esxistencias, idVentas};

				// Agregar el array tabla a la lista
				listaDatos.add(tabla);
	        }
	        return listaDatos;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar(cn, stm, rs);
	    }
	    return null;
	}

	/* Método que actualiza los datos de un alumno especifico */
	public void update(String Id_Producto, String cNombre_Producto, String cDescripcion, String cPrecio, String cStock_Disponible, String idVentas) {
	    Connection cn = null;
	    PreparedStatement statement = null;

	    try {
	        cn = conexion.conectar();

	        // Construir la consulta SQL de actualización
	        String consulta = "UPDATE productos SET ";
	        boolean hayCambios = false;
//	        cNombre_Producto, cDescripcion, cPrecio, cStock_Disponible, idVentas
	        if (cNombre_Producto != null) {
	            consulta += "cNombre_Producto = ?, ";
	            hayCambios = true;
	        }
	        if (cDescripcion != null) {
	            consulta += "cDescripcion = ?, ";
	            hayCambios = true;
	        }
	        if (cPrecio != null) {
	            consulta += "cPrecio = ?, ";
	            hayCambios = true;
	        }
	        if (cStock_Disponible != null) {
	            consulta += "cStock_Disponible = ?, ";
	            hayCambios = true;
	        }
	        if (idVentas != null) {
	            consulta += "idVentas = ?, ";
	            hayCambios = true;
	        }

	        // Eliminar la coma y el espacio finales de la consulta
	        consulta = consulta.substring(0, consulta.length() - 2);

	        // Agregar la condición WHERE
	        consulta += " WHERE Id_Producto = ?";
	        if (!hayCambios) {
	            // Si no se proporcionaron atributos para actualizar, no hacer nada
	            return;
	        }

	        statement = cn.prepareStatement(consulta);

	        // Establecer los parámetros de la consulta
	        int parametroIndex = 1;
//	        Id_Producto, cNombre_Producto, cDescripcion, cPrecio, cStock_Disponible, idVentas
	        if (cNombre_Producto != null) {
	            statement.setString(parametroIndex++, cNombre_Producto);
	        }
	        if (cDescripcion != null) {
	            statement.setString(parametroIndex++, cDescripcion);
	        }
	        if (cPrecio != null) {
	            statement.setString(parametroIndex++, cPrecio);
	        }
	        if (cStock_Disponible != null) {
	            statement.setString(parametroIndex++, cStock_Disponible);
	        }
	        if (idVentas != null) {
	            statement.setString(parametroIndex++, idVentas);
	        }
	        statement.setString(parametroIndex, Id_Producto);

	        // Ejecutar la consulta
	        statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (cn != null) {
	                cn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/* Método que elimina un alumno */ 
	public void delete(String Id_Producto) throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		PreparedStatement pstmtEliminarMaestro = null;
		PreparedStatement pstmtEliminarHorarios = null;

		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false); // Desactivar la confirmación automática

			// Eliminar al maestro
			String consultaEliminarMaestro = "DELETE FROM productos WHERE Id_Producto = ?";
			pstmtEliminarMaestro = cn.prepareStatement(consultaEliminarMaestro);
			pstmtEliminarMaestro.setString(1, Id_Producto);
			int filasAfectadas = pstmtEliminarMaestro.executeUpdate();

			// Confirmar la transacción
			cn.commit();

			if (filasAfectadas > 0) {
				System.out.println("Alumno y sus horarios eliminados con éxito.");
			} else {
				System.out.println("No se pudo encontrar al alumno con la matrícula proporcionada.");
			}

		} catch (SQLException e) {
			try {
				if (cn != null) {
					cn.rollback(); // Deshacer la transacción en caso de error
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("Error al eliminar al maestro.");
		} finally {
			try {
				if (cn != null) {
					cn.setAutoCommit(true); // Restaurar la confirmación automática
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conexion.desconectar(cn, null, null); // Cerrar las conexiones
		}
	}

	
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException {
		PeticionesBD conexion = new PeticionesBD();
		/* Cración */
//		conexion.create("26", "Juan", "Gomez", "48", "30", "2");
		
		/* Lectura */
//		conexion.read();
		
		/* Actualización */
		conexion.update("95", null, null, "20", null, null);
		
		/* Eliminación */
//		conexion.delete("26");
	}
}
