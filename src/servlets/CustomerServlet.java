package servlets;

import handlers.LoginHandler;
import handlers.SignupHandler;
import model.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CustomerServlet")
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("request").equals("signup"))
        {
            signup(request,response);
        }
        else if(request.getParameter("request").equals("login"))
        {
            login(request,response);
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response)
    {
        Customer customer = new Customer(Jsoup.clean(request.getParameter("name"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("email"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("pwd"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("number"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("country"), Whitelist.basic()));

        boolean customerExists = new SignupHandler().customerExists(customer);
        JSONObject rowInserted = new JSONObject();

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        if(!customerExists)
        {
            rowInserted = new SignupHandler().insertCustomer(customer);
            HttpSession session = request.getSession();
            int id = new SignupHandler().getCustomerId(customer);
            session.setAttribute("id",id);
            session.setAttribute("name",request.getParameter("name"));
            session.setAttribute("email",request.getParameter("email"));
            session.setAttribute("number",request.getParameter("number"));
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }

        out.write(myobj.toJSONString());
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
    {

        String email = Jsoup.clean(request.getParameter("email"), Whitelist.basic());
        String pwd = Jsoup.clean(request.getParameter("pwd"), Whitelist.basic());
        boolean customerExists = new LoginHandler().customerExists(email,pwd);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        if(customerExists)
        {
            HttpSession session = request.getSession();
            JSONArray details = new LoginHandler().getCustomerDetails(email,pwd);
            JSONObject c = (JSONObject) details.get(0);
            int id = (int)c.get("id");
            JSONObject customer = (JSONObject) details.get(1);
            Customer customerDetails = (Customer)customer.get("customer");

            session.setAttribute("id",id);
            session.setAttribute("name",customerDetails.getName());
            session.setAttribute("email",customerDetails.getEmail());
            session.setAttribute("number",customerDetails.getContact());
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }

        out.write(myobj.toJSONString());
    }
}
