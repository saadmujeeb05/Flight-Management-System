package dao;

import model.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean customerExists(String email, String pwd)
    {
        int count = 0;
        String query = "SELECT * FROM Customers C WHERE C.Email = ? AND C.Password = ?";

        try
        {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,email);
            ps.setString(2,pwd);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        if(count > 0)
            return true;
        else
            return false;
    }

    public JSONArray getCustomerDetails(String email, String pwd)
    {
        int id = -1;
        String name = "-";
        String contactNumber = "-";
        String country = "-";

        if(customerExists(email, pwd)) {
            String query = "SELECT * FROM Customers C WHERE C.Email = ?";

            try {
                Connection connection = DBUtil.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    id = rs.getInt("Id");
                    name = rs.getString("Name");
                    contactNumber = rs.getString("ContactNo");
                    country = rs.getString("Country");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Customer customer = new Customer(name,email,pwd,contactNumber,country);
        JSONArray details = new JSONArray();
        JSONObject cid = new JSONObject();
        cid.put("id",id);
        details.add(cid);
        JSONObject customerDetails = new JSONObject();
        customerDetails.put("customer",customer);
        details.add(customerDetails);
        return details;
    }
}
