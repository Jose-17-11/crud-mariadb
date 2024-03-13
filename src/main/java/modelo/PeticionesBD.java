package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class PeticionesBD {

	private static ConexionBD conexion;

	// Conexion a la base de datos
	public PeticionesBD() {
		conexion = new ConexionBD();
	}
	/* Método que crea un nuevo alumno */
	public String create(String noControl, String nombre, String apPaterno, String apMaterno)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM Alumnos");
			String consulta = "INSERT INTO Alumnos (noControl, nombre, apPaterno, apMaterno) VALUES (?, ?, ?, ?)";

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, noControl);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apPaterno);
			pstmt.setString(4, apMaterno);
			
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
	public void read() {
	    Connection cn = null;
	    Statement stm = null;
	    ResultSet rs = null;

	    try {
	        cn = conexion.conectar();
	        stm = cn.createStatement();
	        rs = stm.executeQuery("SELECT * FROM Alumnos");

	        while (rs.next()) {
	            String matricula = rs.getString(1);
	            String nombre = rs.getString(2);
	            String apellidoP = rs.getString(3);
	            String apellidoM = rs.getString(4);
	            System.out.println(matricula + " | " + nombre + " | " + apellidoP + " | " + apellidoM);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar(cn, stm, rs);
	    }
	}

	// Pendiente
	public void update(String noControl, String nombre, String apPaterno, String apMaterno) {
	    Connection cn = null;
	    PreparedStatement statement = null;

	    try {
	        cn = conexion.conectar();

	        // Construir la consulta SQL de actualización
	        String consulta = "UPDATE Alumnos SET ";
	        boolean hayCambios = false;

	        if (nombre != null) {
	            consulta += "nombre = ?, ";
	            hayCambios = true;
	        }
	        if (apPaterno != null) {
	            consulta += "apPaterno = ?, ";
	            hayCambios = true;
	        }
	        if (apMaterno != null) {
	            consulta += "apMaterno = ?, ";
	            hayCambios = true;
	        }

	        // Eliminar la coma y el espacio finales de la consulta
	        consulta = consulta.substring(0, consulta.length() - 2);

	        // Agregar la condición WHERE
	        consulta += " WHERE noControl = ?";
	        if (!hayCambios) {
	            // Si no se proporcionaron atributos para actualizar, no hacer nada
	            return;
	        }

	        statement = cn.prepareStatement(consulta);

	        // Establecer los parámetros de la consulta
	        int parametroIndex = 1;
	        if (nombre != null) {
	            statement.setString(parametroIndex++, nombre);
	        }
	        if (apPaterno != null) {
	            statement.setString(parametroIndex++, apPaterno);
	        }
	        if (apMaterno != null) {
	            statement.setString(parametroIndex++, apMaterno);
	        }
	        statement.setString(parametroIndex, noControl);

	        // Ejecutar la consulta
	        statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Asegúrate de cerrar la conexión y el statement
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
	public void delete(String noControl) throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		PreparedStatement pstmtEliminarMaestro = null;
		PreparedStatement pstmtEliminarHorarios = null;

		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false); // Desactivar la confirmación automática

			// Eliminar al maestro
			String consultaEliminarMaestro = "DELETE FROM Alumnos WHERE noControl = ?";
			pstmtEliminarMaestro = cn.prepareStatement(consultaEliminarMaestro);
			pstmtEliminarMaestro.setString(1, noControl);
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
//		conexion.create("21680045", "Juan", "Gomez", "Hernandez");
		
		/* Lectura */
		conexion.read();
		
		conexion.update("22680047", null, "Perez", null);
		
		conexion.read();
		
		/* Eliminación */
//		conexion.delete("21680072");
	}
}
