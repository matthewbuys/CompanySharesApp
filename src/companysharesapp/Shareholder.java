//Matthew Buys
package companysharesapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Shareholder 
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
            System.out.println("Could not estalish a Connection via Shareholder-Connection method");
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
    int id;int address;String name;String number;String dateOfBirth;int totalShares;
    public Shareholder(int id)//for when the ID is known
    {
        try {
            Connection();
            String sql = "select * from \"Shareholder_Details\"\n WHERE \"Shareholder_ID\" = "+id;//sql query
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();//skips first line
            this.id =id;//populates class variables
            address = rs.getInt(2);
            name = rs.getString(3);
            number = rs.getString(4);
            dateOfBirth = ""+rs.getDate(5);
            totalShares = rs.getInt(6);
        } catch (SQLException ex) 
        {
            System.out.println("Could not establish an ID-Shareholder connection");//to know where the error is.
        }
    }
    public Shareholder(String name)//for when the name is known
    {
        try {
            Connection();
            String sql = "select * from \"Shareholder_Details\"\n WHERE \"Shareholder_Name\" = '"+name+"'";//sql querry
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();//skips first line
            this.name = name;
            id = rs.getInt(1);
            address = rs.getInt(2);
            number = rs.getString(4);
            dateOfBirth = ""+rs.getDate(5);
            totalShares = rs.getInt(6);
        } catch (SQLException ex) 
        {
            System.out.println("Could not establish a Name-Shareholder connection");//to know where the error is.
        }
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
        Connection();
        String sql = "UPDATE \"Shareholder_Details\"\n" + //sql querry
                    "SET \"Shareholder_ID\" = "+id+"\n" +
                    "WHERE \"Shareholder_Name\" = '"+name+"'";
        ExecuteSQL(sql);
    }

    public int getAddressID() {
        return address;
    }

    public void setAddressID(int address) {
        this.address = address;
        String sql = "UPDATE \"Shareholder_Details\"\n" + //sql querry
                    "SET \"Address_ID\" = "+address+"\n" +
                    "WHERE \"Shareholder_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        String sql = "UPDATE \"Shareholder_Details\"\n" +
                    "SET \"Shareholder_Name\" = '"+name+"'\n" +
                    "WHERE \"Shareholder_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        String sql = "UPDATE \"Shareholder_Details\"\n" + //sql querry
                    "SET \"Shareholder_Number\" = '"+number+"'\n" +
                    "WHERE \"Shareholder_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        String sql = "UPDATE \"Shareholder_Details\"\n" + //sql querry
                    "SET \"Shareholder_DOB\" = '"+dateOfBirth+"'\n" +
                    "WHERE \"Shareholder_ID\" = "+id;
        ExecuteSQL(sql);
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
        String sql = "UPDATE \"Shareholder_Details\"\n" + //sql querry
                    "SET \"Shareholder_TotalShares\" = "+totalShares+"\n" +
                    "WHERE \"Shareholder_ID\" = "+id;
        ExecuteSQL(sql);
    }
    
}
