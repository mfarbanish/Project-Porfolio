import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dizzy
 */



//My sql delete command wasnt working even though it did it exactly as shown in the book and on oracles website.
//I dont know 


public class BookingEntry {
    private static Connection connection;
    private static PreparedStatement book;
    private static PreparedStatement getbook;
    private static PreparedStatement getFlightPassengers;
    private static PreparedStatement getCustomerInf;
    private static java.sql.Timestamp currentTimestamp;
    private static PreparedStatement getcapacity;
    private static ResultSet rs;
    private static ResultSet rs2; 
    private static ResultSet rs3;
    private static ResultSet rs4;
    private static int cap;
    private static int bookspa;
    private static PreparedStatement delete;
    private static ArrayList<String> customers;

    private static int bookingcount;
    
    private static ArrayList<String> customerinfo;
    private static ArrayList<String> customerinfoDay;
    private static PreparedStatement getCustomerDay;
    private static ResultSet rs5;
    
    
    public static ArrayList<String> getFlightCustomers(String flight, String day)
    {
        try
        {
            connection = DBconnection.getconnection();
            customers = new ArrayList();
            getFlightPassengers = connection.prepareStatement("select customer from bookings where flight = ? and day = ?");
            getFlightPassengers.setString(1, flight);
            getFlightPassengers.setString(2, day);
            rs3 = getFlightPassengers.executeQuery();
            while (rs3.next())
            {
                customers.add(rs3.getString(1));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return customers;

    }
    
    
    public static int bookingCounter(String flightName, String day){
        bookingcount = 0;
        try{
            connection = DBconnection.getconnection();
            getbook = connection.prepareStatement("select * from bookings");
            rs = getbook.executeQuery();
            while(rs.next()){
                if (rs.getString(2).equalsIgnoreCase(flightName) && rs.getString(3).equals(day)){
                    bookingcount = bookingcount + 1;
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return bookingcount;
    }
       
    public static int seatCounter(String flight){

        try {
            /*
            bookingspace = connection.prepareStatement("select count(flight) from bookings where flight = ? and day = ?"); 
            bookingspace.setString(1, flight); bookingspace.setDate(2, (java.sql.Date) sqlDate); 
            rs = bookingspace.executeQuery(); 
            rs.next(); 
            bookspa = rs.getInt(1);
            */
            connection = DBconnection.getconnection();
            bookspa = 0;
            getcapacity = connection.prepareStatement("select seats from FLIGHT where NAME = ?"); 
            getcapacity.setString(1, flight);
            rs2 = getcapacity.executeQuery(); 
            rs2.next(); 
            cap = rs2.getInt(1);
            //System.out.println(cap);
        }
        catch (SQLException ex){
            bookspa = 0;
        }
        return cap;
    }
    
    
    public static void addBook(String customer, String flight, String day){
            try
            {
                currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                connection = DBconnection.getconnection();
                book = connection.prepareStatement("insert into bookings (customer, flight, day, timestamp) values (?, ?, ?, ?)");
                book.setString(1, customer);
                book.setString(2, flight);
                book.setString(3, day);
                book.setString(4, currentTimestamp.toString());
                book.executeUpdate();

            } catch (SQLException ex)
            {
                //ex.printStackTrace();
            }
        }
    public static void deleteEntry(String customer, String day) throws SQLException{
                connection = DBconnection.getconnection();
                delete = connection.prepareStatement("DELETE from bookings where customer = ? and day = ?");
                delete.setString(1, customer);
                delete.setString(2, day);
                delete.executeUpdate();       
    }
    
    public static ArrayList<String> getCustomerInfo(String customer) throws SQLException{
        connection = DBconnection.getconnection();
        customerinfo = new ArrayList();
        getCustomerInf = connection.prepareStatement("select flight from bookings where customer = ?");
        getCustomerInf.setString(1, customer);
        rs4 = getCustomerInf.executeQuery();
        while (rs4.next())
            {
                customerinfo.add(rs4.getString(1));
            }
        //System.out.println(customerinfo);
        return customerinfo;

    }
    
    public static ArrayList<String> getCustomerDays(String customer) throws SQLException{
        connection = DBconnection.getconnection();
        customerinfoDay = new ArrayList();
        getCustomerDay = connection.prepareStatement("select day from bookings where customer = ?");
        getCustomerDay.setString(1, customer);
        rs5 = getCustomerDay.executeQuery();
        while (rs5.next())
            {
                customerinfoDay.add(rs5.getString(1));
            }
        //System.out.println(customerinfo);
        return customerinfoDay;

    }
    
    }
