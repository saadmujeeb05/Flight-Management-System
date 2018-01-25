package handlers;

import dao.LoginDAO;
import dao.SignupDAO;
import model.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LoginHandler {

    public boolean customerExists(String email, String pwd)
    {
        boolean customerExists = new LoginDAO().customerExists(email,pwd);

        return customerExists;
    }

    public JSONArray getCustomerDetails(String email, String pwd)
    {
        JSONArray customer = new LoginDAO().getCustomerDetails(email,pwd);

        return customer;
    }
}

