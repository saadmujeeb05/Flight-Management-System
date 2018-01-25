package dao;

import model.Customer;
import util.DBUtil;
import org.json.simple.JSONObject;

import java.sql.*;

public class SignupDAO {

    public boolean customerExists(Customer customer)
    {
        JSONObject myobj = new JSONObject();
        int count = 0;
        String query = "SELECT * FROM Customers C WHERE C.Email = ?";

        try
        {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,customer.getEmail());
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

    public int getCustomerId(Customer customer)
    {
        int id = -1;

        if(customerExists(customer)) {
            String query = "SELECT C.Id FROM Customers C WHERE C.Email = ?";

            try {
                Connection connection = DBUtil.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setString(1, customer.getEmail());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    id = rs.getInt("Id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public JSONObject insertCustomer(Customer customer)
    {
        JSONObject myobj = new JSONObject();
        boolean rowInserted = false;
        String query = "INSERT INTO Customers VALUES(?,?,?,?,?)";

        try
        {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,customer.getName());
            ps.setString(2,customer.getEmail());
            ps.setString(3,customer.getPassword());
            ps.setString(4,customer.getContact());
            ps.setString(5,customer.getCountry());

            rowInserted = ps.executeUpdate() > 0;
            if(rowInserted)
                myobj.put("success",true);
            else
                myobj.put("success",false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myobj;
    }

}
