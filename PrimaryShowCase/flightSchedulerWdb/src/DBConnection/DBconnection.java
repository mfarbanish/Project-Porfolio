
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dizzy
 */
public class DBconnection
{
    private static Connection connection;
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBMahlonmdf";
    private static final String username = "java";
    private static final String password = "java";
    
    public static Connection getconnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(URL, username, password);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return connection;
        
    }
    
}
