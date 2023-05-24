package edu.fpdual.persistence.connector;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author : José M. Prieto Villar
 * @version : 1.0
 * Clase que establece la conexión con la BBDD de MySQL.
 */
@Getter
@Setter
public class MySQLConnector {

    /**
     * Archivo de propiedades para la conexión de la BBDD.
     */
    Properties prop = new Properties();

    /**
     * Constructor de la clase, que carga la configuración para la conexión con la BBDD.
     */
    public MySQLConnector() {
        try {
            //Loads all the properties of file "config.properties".
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection object for a MySQL DDBB
     *
     * @return a {@link Connection}
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {

            //Indicates which driver is going to be used.
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));

            //Creates the connection based on the obtained URL.
            return DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtains the URL to connect to a MySQL DDBB.
     *
     * @return an URL
     */
    private String getURL() {
        //jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false;
        return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }
}
