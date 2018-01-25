package handlers;

import dao.SignupDAO;
import model.Customer;
import org.json.simple.JSONObject;

public class SignupHandler {

    public JSONObject insertCustomer(Customer customer)
    {
        JSONObject rowInserted = new SignupDAO().insertCustomer(customer);

        return rowInserted;
    }

    public boolean customerExists(Customer customer)
    {
        boolean customerExists = new SignupDAO().customerExists(customer);

        return customerExists;
    }

    public int getCustomerId(Customer customer)
    {
        int id = new SignupDAO().getCustomerId(customer);

        return id;
    }
}

