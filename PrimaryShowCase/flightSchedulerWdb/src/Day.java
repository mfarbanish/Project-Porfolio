import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Dizzy
 */
public class Day {
    private static Connection connection;
    private static PreparedStatement getDays;
    private static PreparedStatement insertNewDay;
    private static ResultSet rs;
    private static ArrayList<String> days;
    private static SimpleDateFormat formater;
    private static String formatDate;
    
    
        public static ArrayList<String> getDays(){
        try
        {
            connection = DBconnection.getconnection();
            days = new ArrayList();
            getDays = connection.prepareStatement("select date from day");
            rs = getDays.executeQuery();
            while (rs.next())
            {
                days.add(rs.getString(1));
            }
        } catch (SQLException ex)
        {
            //ex.printStackTrace();
        }
        return days;
    }

        public static void addNewDay(Date date)
    {
        formater = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = formater.format(date); 
        
        try
        {
            connection = DBconnection.getconnection();
            insertNewDay = connection.prepareStatement("insert into day (date) values (?)");
            insertNewDay.setString(1, formatDate);
            insertNewDay.executeUpdate();

        } catch (SQLException ex)
        {
            //ex.printStackTrace();
        }

    }
        
}
