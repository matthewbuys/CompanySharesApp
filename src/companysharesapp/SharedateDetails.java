/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package companysharesapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matth_000
 */
public class SharedateDetails 
{
    private Connection conn;
    private Statement stmt;
    private void Connection()//method for the connection to DB without having to have a try-catch at every setter/getter
    {
        try 
        {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CompanySharesDB", "nbuser", "nbuser");//Connection to DB
            stmt = conn.createStatement();
        } catch (SQLException ex) 
        {
            System.out.println("Could not estalish a Connection via Addresses-Connection method");
        }
    }
    private void ExecuteSQL(String sql)//to create the stmt without having to have a try-catch at every setter
    {
        try {
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Error executing SQL via ExecuteSQL method");
        }
    }
    int id; double shareValue;String date;
    public SharedateDetails(int id)
    {
        try {
            Connection();
            String sql = "select * from \"ShareDate_Details\"\n WHERE \"ShareDate_ID\" = "+id;//sql query
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();//skips first line
            this.id =id;//populates class variables
            shareValue = rs.getDouble(2);
            date = ""+rs.getDate(3);
        } catch (SQLException ex) {
            System.out.println("Error with ID-ShareDate_Details method");
        }
    }

    public int getId() {
        return id;
    }

    public double getShareValue() {
        return shareValue;
    }

    public void setShareValue(double shareValue) {
        this.shareValue = shareValue;
        Connection();
        String sql = "UPDATE \"Shares_Details\"\n" + //sql querry
                    "SET \"ShareDate_Value\" = '"+shareValue+"'\n" +
                    "WHERE \"Share_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        Connection();
        String sql = "UPDATE \"Shares_Details\"\n" + //sql querry
                    "SET \"ShareDate_Date\" = '"+date+"'\n" +
                    "WHERE \"Share_ID\" = "+id;
        ExecuteSQL(sql);
    }
    
}
