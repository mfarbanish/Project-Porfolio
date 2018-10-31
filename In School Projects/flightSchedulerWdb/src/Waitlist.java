import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Dizzy
 */
public class Waitlist {
    private static Connection connection;
    private static PreparedStatement waitlist;
    private static PreparedStatement getCustomers;
    private static ResultSet rs;
    private static ResultSet rs2;
    private static java.sql.Timestamp currentTimestamp;
    private static ArrayList<String> waitlistDay;
    private static PreparedStatement delete;
    private static PreparedStatement delete2;
    private static ArrayList<String> customers;
    private static PreparedStatement getNext;
    private static ArrayList customerinfo;
    private static PreparedStatement getCustomerInf;
    private static ResultSet rs3;
    private static PreparedStatement getCustomerDay;
    private static ResultSet rs4;
    
    
    
        public static void addWaitlist(String customer, String flight, String day){
            try
            {
                currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                connection = DBconnection.getconnection();
                waitlist = connection.prepareStatement("insert into waitlist (customer, flight, day, timestamp) values (?, ?, ?, ?)");
                waitlist.setString(1, customer);
                waitlist.setString(2, flight);
                waitlist.setString(3, day);
                waitlist.setString(4, currentTimestamp.toString());
                waitlist.executeUpdate();

            } catch (SQLException ex)
            {
                //ex.printStackTrace();
            }
        }
        
        public static ArrayList<String> getWaitlist(String day){
            try
            {
                connection = DBconnection.getconnection();
                waitlistDay = new ArrayList();
                getCustomers = connection.prepareStatement("select customer from waitlist where day = ?");
                getCustomers.setString(1, day);
                rs = getCustomers.executeQuery();
                while (rs.next())
                {
                    waitlistDay.add(rs.getString(1));
                }
            } catch (SQLException ex)
            {
                //ex.printStackTrace();
            }
            return waitlistDay;
    }
        
        public static ArrayList<String> getNextCustomers(String flight, String day)
    {
        try
        {
            connection = DBconnection.getconnection();
            customers = new ArrayList();
            getNext = connection.prepareStatement("select customer from waitlist where flight = ? and day = ?");
            getNext.setString(1, flight);
            getNext.setString(2, day);
            rs2 = getNext.executeQuery();
            while (rs2.next())
            {
                customers.add(rs2.getString(1));
            }
        } catch (SQLException ex)
        {
        }
        return customers;

    }    
        
    public static void deleteEntry(String customer, String flight, String day) throws SQLException{
                connection = DBconnection.getconnection();
                delete = connection.prepareStatement("DELETE from waitlist where customer = ? and flight = ? and day = ?");
                delete.setString(1, customer);
                delete.setString(2, flight);
                delete.setString(3, day);
                delete.executeUpdate();       
    }
    
    public static ArrayList<String> getCustomerInfo(String customer) throws SQLException{
        connection = DBconnection.getconnection();
        customerinfo = new ArrayList();
        getCustomerInf = connection.prepareStatement("select flight from waitlist where customer = ?");
        getCustomerInf.setString(1, customer);
        rs3 = getCustomerInf.executeQuery();
        while (rs3.next())
            {
                customerinfo.add(rs3.getString(1));
            }
        //System.out.println(customerinfo);
        return customerinfo;

    }
    
    public static ArrayList<String> getCustomerDays(String customer) throws SQLException{
        connection = DBconnection.getconnection();
        ArrayList customerinfoDay = new ArrayList();
        getCustomerDay = connection.prepareStatement("select day from waitlist where customer = ?");
        getCustomerDay.setString(1, customer);
        rs4 = getCustomerDay.executeQuery();
        while (rs4.next())
            {
                customerinfoDay.add(rs4.getString(1));
            }
        //System.out.println(customerinfo);
        return customerinfoDay;
    } 
    
    public static void deleteCustomersFlight(String flight) throws SQLException{
        connection = DBconnection.getconnection();
        delete2 = connection.prepareStatement("delete from waitlist where flight = ?");
        delete2.setString(1, flight);
        delete2.executeUpdate();
        
    }

        
    

}
