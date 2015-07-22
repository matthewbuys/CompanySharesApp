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
public class Addresses 
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
    private void executeSQL(String sql)//to create the stmt without having to have a try-catch at every setter
    {
        try {
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Error executing SQL via ExecuteSQL method");
        }
    }
    int id;String street;String neigbourhood;String city;String province;String country;
    public Addresses(){}
    public Addresses(int id)
    {
        try {
            Connection();
            String sql = "select * from \"Shareholder_Addresses\"\n WHERE \"Address_ID\" = "+id;//sql query
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();//skips first line
            this.id =id;//populates class variables
            street  = rs.getString(2);
            neigbourhood  = rs.getString(3);
            city = rs.getString(4);
            province = rs.getString(5);
            country = rs.getString(6);
        } catch (SQLException ex) 
        {
            System.out.println("Could not establish an ID-Addresses connection");//to know where the error is.
        }
    }

    public void addRecord(int id,String street,String neighbourhood,String city,String province,String country)
    {
     Connection();
     String sql = "INSERT INTO \"Shareholder_Addresses\"(\"Address_ID\",\"Street\",\"Neighbourhood\",\"City\",\"Province\",\"Country\")\n" +
"                   VALUES("+id+",'"+street+"','"+neighbourhood+"','"+city+"','"+province+"','"+country+"')";
     executeSQL(sql);
    }
    
    public int getLastID()
    {
        try {
            Connection();
            String sql = "select MAX(\"Address_ID\") from NBUSER.\"Shareholder_Addresses\"";
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        Connection();
        String sql = "UPDATE \"Shareholder_Addresses\"\n" + //sql querry
                    "SET \"Street\" = '"+street+"'\n" +
                    "WHERE \"Address_ID\" = "+id;
        executeSQL(sql);
    }

    public String getNeigbourhood() {
        return neigbourhood;
    }

    public void setNeigbourhood(String neigbourhood) {
        this.neigbourhood = neigbourhood;
        Connection();
        String sql = "UPDATE \"Shareholder_Addresses\"\n" + //sql querry
                    "SET \"Neighbourhood\" = '"+neigbourhood+"'\n" +
                    "WHERE \"Address_ID\" = "+id;
        executeSQL(sql);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        Connection();
        String sql = "UPDATE \"Shareholder_Addresses\"\n" + //sql querry
                    "SET \"City\" = '"+city+"'\n" +
                    "WHERE \"Address_ID\" = "+id;
        executeSQL(sql);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        Connection();
        String sql = "UPDATE \"Shareholder_Addresses\"\n" + //sql querry
                    "SET \"Province\" = '"+province+"'\n" +
                    "WHERE \"Address_ID\" = "+id;
        executeSQL(sql);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        Connection();
        String sql = "UPDATE \"Shareholder_Addresses\"\n" + //sql querry
                    "SET \"Country\" = '"+country+"'\n" +
                    "WHERE \"Address_ID\" = "+id;
        executeSQL(sql);
    }
}
