package servlets;

import handlers.FlightHandler;
import model.Flight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("request").equals("addFlight"))
            addFlight(request,response);
        else if(request.getParameter("request").equals("getAllFlights") || request.getParameter("request").equals("getTentativeFlights"))
            getAllFlights(request,response);
        else if(request.getParameter("request").equals("updateFlight"))
            updateFlight(request,response);
        else if(request.getParameter("request").equals("deleteFlight"))
            deleteFlight(request,response);
        else if(request.getParameter("request").equals("approveFlight") || request.getParameter("request").equals("disapproveFlight"))
            manageFlight(request,response);
        else if(request.getParameter("request").equals("logout"))
            logout(request,response);
    }

    private void updateFlight(HttpServletRequest request, HttpServletResponse response)
    {
        Flight updateFlight = new Flight(request.getParameter("uFlightNumber"),request.getParameter("uDeparts"),request.getParameter("uDepartureDate"),
                request.getParameter("uDeparture"),request.getParameter("uArrives"),request.getParameter("uArrivalDate"),request.getParameter("uArrival"),
                request.getParameter("u-eco-price"),request.getParameter("u-eco-seats"),request.getParameter("u-buss-price"),request.getParameter("u-buss-seats"),
                request.getParameter("u-fclass-price"),request.getParameter("u-fclass-seats"));

        Flight oldFlight = new Flight(request.getParameter("flightNumber"),request.getParameter("departs"),request.getParameter("departureDate"),
                request.getParameter("departure"),request.getParameter("arrives"),request.getParameter("arrivalDate"),request.getParameter("arrival"),
                request.getParameter("ecoPrice"),request.getParameter("ecoSeats"),request.getParameter("bussPrice"),request.getParameter("bussSeats"),
                request.getParameter("fclassPrice"),request.getParameter("fclassSeats"));

        String flightType = request.getParameter("flightType");

        boolean rowUpdated = new FlightHandler().updateFlight(updateFlight,oldFlight,flightType);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        if(rowUpdated)
        {
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());

    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response)
    {
        Flight flight = new Flight(request.getParameter("flightNumber"),request.getParameter("departs"),request.getParameter("departureDate"),
                request.getParameter("departure"),request.getParameter("arrives"),request.getParameter("arrivalDate"),request.getParameter("arrival"),
                request.getParameter("eco-price"),request.getParameter("eco-seats"),request.getParameter("buss-price"),request.getParameter("buss-seats"),
                request.getParameter("fclass-price"),request.getParameter("fclass-seats"));

        String flightType = request.getParameter("flightType");

        boolean rowInserted = new FlightHandler().insertFlight(flight,flightType);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        if(rowInserted)
        {
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());
    }

    private void getAllFlights(HttpServletRequest request, HttpServletResponse response)
    {
        JSONObject myobj = new JSONObject();
        String flightType = request.getParameter("flightType");
        JSONArray allFlights = new FlightHandler().getAllFlights(flightType);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }


        myobj.put("request",request.getParameter("request"));
        myobj.put("allFlights",allFlights);
        out.write(myobj.toJSONString());
    }

    private void deleteFlight(HttpServletRequest request, HttpServletResponse response)
    {
        Flight flight = new Flight(request.getParameter("flightNumber"),request.getParameter("departs"),request.getParameter("departureDate"),
                request.getParameter("departure"),request.getParameter("arrives"),request.getParameter("arrivalDate"),request.getParameter("arrival"),
                request.getParameter("ecoPrice"),request.getParameter("ecoSeats"),request.getParameter("bussPrice"),request.getParameter("bussSeats"),
                request.getParameter("fclassPrice"),request.getParameter("fclassSeats"));

        JSONObject myobj = new JSONObject();
        String flightType = request.getParameter("flightType");

        boolean deleted = new FlightHandler().deleteFlight(flight,flightType);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        myobj.put("request",request.getParameter("request"));
        if(deleted)
        {
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }
        out.write(myobj.toJSONString());
    }


    private void manageFlight(HttpServletRequest request, HttpServletResponse response)
    {
        Flight flight = new Flight(request.getParameter("flightNumber"),request.getParameter("departs"),request.getParameter("departureDate"),
                request.getParameter("departure"),request.getParameter("arrives"),request.getParameter("arrivalDate"),request.getParameter("arrival"),
                request.getParameter("ecoPrice"),request.getParameter("ecoSeats"),request.getParameter("bussPrice"),request.getParameter("bussSeats"),
                request.getParameter("fclassPrice"),request.getParameter("fclassSeats"));
        String flightType = null;

        boolean managed = false;
        if(request.getParameter("request").equals("approveFlight"))
        {
            flightType = "TentativeFlights";
            managed =  new FlightHandler().deleteFlight(flight,flightType);
            if(managed) {
                flightType = "Schedule";
                managed = new FlightHandler().insertFlight(flight, flightType);
            }
        }
        else if(request.getParameter("request").equals("disapproveFlight"))
        {
            flightType = "TentativeFlights";
            managed = new FlightHandler().deleteFlight(flight,flightType);
        }

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        if(managed)
        {
            myobj.put("success",true);
        }
        else {

            myobj.put("success",false);
        }
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();
        session.invalidate();

        myobj.put("success",true);
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());
    }
}
