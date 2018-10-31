
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Dizzy
 */
public class Flight {
    private static Connection connection;
    private static PreparedStatement getFlightNames;
    private static PreparedStatement getFlightSeats;
    private static PreparedStatement addNewFlight;
    private static PreparedStatement delete;
    private static ResultSet rs;
    private static ResultSet rs2;
    private static ArrayList<String> flightNumbers;
    private static ArrayList<String> flightSeats;

    public static ArrayList<String> getFlightNames()
    {
        try
        {
            connection = DBconnection.getconnection();
            flightNumbers = new ArrayList();
            getFlightNames = connection.prepareStatement("select name from flight");
            rs = getFlightNames.executeQuery();
            while (rs.next())
            {
                flightNumbers.add(rs.getString(1));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return flightNumbers;

    }

    public static ArrayList<String> getFlightSeats()
    {
        try
        {
            connection = DBconnection.getconnection();
            flightSeats = new ArrayList();
            getFlightSeats = connection.prepareStatement("select seats from flight");
            rs2 = getFlightSeats.executeQuery();
            while (rs2.next())
            {
                flightSeats.add(rs2.getString(1));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return flightSeats;

    }    
    
    
        public static void addFlight(String flightNumber, String seatcount)
    {
        try
        {
            connection = DBconnection.getconnection();
            addNewFlight = connection.prepareStatement("insert into flight (name, seats) values (?, ?)");
            addNewFlight.setString(1, flightNumber);
            addNewFlight.setString(2, seatcount);
            addNewFlight.executeUpdate();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
        
        public static void removeFlight(String flightNumber) throws SQLException
    {
        try
        {
            connection = DBconnection.getconnection();
            delete = connection.prepareStatement("delete from flight where name = ?");
            delete.setString(1, flightNumber);
            delete.executeUpdate();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
