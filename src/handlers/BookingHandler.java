package handlers;

import dao.FlightDAO;
import model.Flight;
import org.json.simple.JSONArray;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class BookingHandler {


    public JSONArray getDepartingFlights(String flightFrom, String flightTo, String dateDepart)
    {
        JSONArray departingFlights = new FlightDAO().getDepartingFlights(flightFrom,flightTo,dateDepart);

        return departingFlights;
    }

    public JSONArray getReturningFlights(String flightFrom, String flightTo, String dateReturn)
    {
        JSONArray returningFlights = new FlightDAO().getReturningFlights(flightFrom,flightTo,dateReturn);

        return returningFlights;
    }

    public boolean BookFlight(int cid, int dflightId, String dclassType,String fare)
    {
        boolean booked = new FlightDAO().BookFlight(cid,dflightId,dclassType,fare);

        return booked;
    }

}

