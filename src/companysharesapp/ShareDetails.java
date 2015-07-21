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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matth_000
 */
public class ShareDetails 
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
    int id; int shareholderID;int sharetotal;boolean shareSell;String date;
    public ShareDetails(int id)
    {
        try {
            Connection();
            String sql = "select * from \"Shares_Details\"\n WHERE \"Share_ID\" = "+id;//sql query
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();//skips first line
            this.id =id;//populates class variables
            shareholderID = rs.getInt(2);
            sharetotal = rs.getInt(3);
            shareSell = rs.getBoolean(4);
            date = ""+rs.getDate(5);
        } catch (SQLException ex) {
            System.out.println("Error with ID-ShareDetails method");
        }
    }
    public void addRecord()
    {
        
    }
    public int getLastID()
    {
        try {
            Connection();
            String sql = "select MAX(\"Share_ID\") from NBUSER.\"Shares_Details\"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Error with getLastID");
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public int getShareholderID() {
        return shareholderID;
    }

    public void setShareholderID(int shareholderID) {
        this.shareholderID = shareholderID;
        Connection();
        String sql = "UPDATE \"Shares_Details\"\n" + //sql querry
                    "SET \"Shareholder_ID\" = '"+shareholderID+"'\n" +
                    "WHERE \"Share_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public int getSharetotal() {
        return sharetotal;
    }

    public void setSharetotal(int sharetotal) {
        this.sharetotal = sharetotal;
        Connection();
        String sql = "UPDATE \"Shares_Details\"\n" + //sql querry
                    "SET \"Share_Total\" = '"+sharetotal+"'\n" +
                    "WHERE \"Share_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public boolean isShareSell() {
        return shareSell;
    }

    public void setShareSell(boolean shareSell) {
        this.shareSell = shareSell;
        Connection();
        String sql = "UPDATE \"Shares_Details\"\n" + //sql querry
                    "SET \"Share_Sell\" = '"+shareSell+"'\n" +
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
                    "SET \"Share_Date\" = '"+date+"'\n" +
                    "WHERE \"Share_ID\" = "+id;
        ExecuteSQL(sql);
    }
    
}
