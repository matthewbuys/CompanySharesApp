package companysharesapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Shareholder 
{
    private Connection conn;
    int id;int address;String name;String number;String dateOfBirth;int totalShares;
    public Shareholder(int id)//for when the ID is known
    {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CompanySharesDB", "nbuser", "nbuser");//Connection to DB
            System.out.println("Connection to CompanySharesDB Database Established");
            Statement stmt;
            stmt = conn.createStatement();
            String sql = "select * from NBUSER.\"Shareholder_Details\"\n WHERE \"Shareholder_ID\" = "+id;//sql query
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
        this.name = name;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressID() {
        return address;
    }

    public void setAddressID(int address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }
    
}
