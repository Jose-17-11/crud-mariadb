package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    // Se crean las constantes que almacenarán el controlador, URL, usuario y contraseña
    private static final String CONTROLADOR = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:3306/controlEscolar";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1q2w3e4r5t0p9o8i7u6y";

    static {
        try {
            // Se carga el controlador
            Class.forName(CONTROLADOR);
        } catch (ClassNotFoundException e) {
            // Si hay un error al cargar el controlador, se muestra un mensaje de error
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
    }

    // Método que realiza la conexión a la base de datos
    public Connection conectar() {
        Connection conexion = null;

        try {
            // Se establece la conexión con la base de datos utilizando los datos de acceso
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            // Si la conexión es exitosa, se muestra un mensaje
            System.out.println("Conexión establecida correctamente");
        } catch (SQLException e) {
            // Si hay un error en la conexión, se muestra un mensaje de error
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        // Retorna la conexión establecida
        return conexion;
    }

    // Método para cerrar la conexión, el statement y el resultSet
    public void desconectar(Connection cn, Statement stm, ResultSet rs) {
        try {
            // Se verifica que los objetos no sean nulos antes de cerrarlos
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e2) {
            // Si hay un error al cerrar los objetos, se muestra un mensaje de error
            e2.printStackTrace();
        }
    }
}
