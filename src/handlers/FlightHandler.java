package handlers;

import dao.FlightDAO;
import model.Flight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FlightHandler {

    public boolean insertFlight(Flight flight, String flightType)
    {
        boolean rowInserted = new FlightDAO().insertFlight(flight,flightType);

        return rowInserted;
    }

    public boolean updateFlight(Flight updateFlight, Flight oldFlight, String flightType)
    {
        boolean rowUpdated = new FlightDAO().updateFlight(updateFlight,oldFlight,flightType);

        return rowUpdated;
    }

    public JSONArray getAllFlights(String flightType)
    {
        JSONArray allFlights = new FlightDAO().getAllFlights(flightType);

        return allFlights;
    }

    public boolean deleteFlight(Flight flight, String flightType)
    {
        boolean deleted = new FlightDAO().deleteFlight(flight,flightType);

        return deleted;
    }

    public JSONArray getFlightIdForBooking(String flightNumber, String departs, String arrives, String departureDate)
    {
        JSONArray id = new FlightDAO().getFlightIdForBooking(flightNumber,departs,arrives,departureDate);

        return id;
    }
}

