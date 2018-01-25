package dao;

import model.Flight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DBUtil;

import java.sql.*;

public class FlightDAO {

    public boolean insertFlight(Flight flight, String flightType) {
        boolean rowInserted = false;
        String query = "INSERT INTO " + flightType +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getDeparts());
            ps.setString(3, flight.getDepartureDate());
            ps.setString(4, flight.getDeparture());
            ps.setString(5, flight.getArrives());
            ps.setString(6, flight.getArrivalDate());
            ps.setString(7, flight.getArrival());
            ps.setString(8, flight.getEcoPrice());
            ps.setString(9, flight.getEcoSeats());
            ps.setString(10, flight.getBussPrice());
            ps.setString(11, flight.getBussSeats());
            ps.setString(12, flight.getFclassPrice());
            ps.setString(13, flight.getFclassSeats());

            rowInserted = ps.executeUpdate() > 0;

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public boolean updateFlight(Flight updateFlight, Flight oldFlight, String flightType) {
        boolean rowUpdated = false;

        if(deleteFlight(oldFlight,flightType))
            if(insertFlight(updateFlight,flightType))
                rowUpdated = true;

        return rowUpdated;
    }

    public JSONArray getAllFlights(String flightType) {
        JSONObject flight = new JSONObject();
        JSONArray allFlights = new JSONArray();

        String query = "SELECT * FROM " + flightType;

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                flight = new JSONObject();

                String flightNumber  = rs.getString("flightNumber");
                String departs = rs.getString("departs");
                String departureDate = rs.getString("departureDate");
                String departure = rs.getString("departure");
                String arrives = rs.getString("arrives");
                String arrivalDate = rs.getString("arrivalDate");
                String arrival = rs.getString("arrival");
                String ecoPrice = rs.getString("ecoPrice");
                String ecoSeats = rs.getString("ecoSeats");
                String bussPrice = rs.getString("bussPrice");
                String bussSeats = rs.getString("bussSeats");
                String fclassPrice = rs.getString("fclassPrice");
                String fclassSeats = rs.getString("fclassSeats");

                flight.put("Flight Number",flightNumber);
                flight.put("Departs",departs);
                flight.put("Departure Date",departureDate);
                flight.put("Departure",departure);
                flight.put("Arrives",arrives);
                flight.put("Arrival Date",arrivalDate);
                flight.put("Arrival",arrival);
                flight.put("Economy Price",ecoPrice);
                flight.put("Economy Seats",ecoSeats);
                flight.put("Business Price",bussPrice);
                flight.put("Business Seats",bussSeats);
                flight.put("First Class Price",fclassPrice);
                flight.put("First Class Seats",fclassSeats);

                allFlights.add(flight);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allFlights;
    }

    public int getFlightId(Flight flight) {
        int id = -1;

        String query = "SELECT f.id FROM TentativeFlights f WHERE f.flightNumber=? AND f.departs=? AND f.departureDate=?" +
                    " AND f.departure=? AND f.arrives=? AND f.arrivalDate=? AND f.arrival=? AND f.ecoPrice=? AND f.ecoSeats=?" +
                    " AND f.bussPrice=? AND f.bussSeats=? AND f.fclassPrice=? AND f.fclassSeats=?";

       try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

           ps.setString(1, flight.getFlightNumber());
           ps.setString(2, flight.getDeparts());
           ps.setString(3, flight.getDepartureDate());
           ps.setString(4, flight.getDeparture());
           ps.setString(5, flight.getArrives());
           ps.setString(6, flight.getArrivalDate());
           ps.setString(7, flight.getArrival());
           ps.setString(8, flight.getEcoPrice());
           ps.setString(9, flight.getEcoSeats());
           ps.setString(10, flight.getBussPrice());
           ps.setString(11, flight.getBussSeats());
           ps.setString(12, flight.getFclassPrice());
           ps.setString(13, flight.getFclassSeats());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                id = rs.getInt("id");
            }

           connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean deleteFlight(Flight flight, String flightType) {
        boolean deleted = false;

        int id = getFlightId(flight);
        if(id == -1)
        {
            deleted = false;
        }
        else
        {
              String query = "DELETE FROM " + flightType + " WHERE id=?";

            try {
                Connection connection = DBUtil.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setInt(1,id);

                deleted = ps.executeUpdate() > 0;

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleted;
    }

    public JSONArray getDepartingFlights(String flightFrom, String flightTo, String dateDepart) {
        JSONObject flight = new JSONObject();
        JSONArray departingFlights = new JSONArray();

        String query = "SELECT * FROM Schedule s WHERE s.departs=? AND s.arrives=? AND s.departureDate=?";

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,flightFrom);
            ps.setString(2,flightTo);
            ps.setString(3,dateDepart);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flight = new JSONObject();

                String flightNumber  = rs.getString("flightNumber");
                String departs = rs.getString("departs");
                String departureDate = rs.getString("departureDate");
                String departure = rs.getString("departure");
                String arrives = rs.getString("arrives");
                String arrivalDate = rs.getString("arrivalDate");
                String arrival = rs.getString("arrival");
                String ecoPrice = rs.getString("ecoPrice");
                String bussPrice = rs.getString("bussPrice");
                String fclassPrice = rs.getString("fclassPrice");

                flight.put("Flight Number",flightNumber);
                flight.put("Departs",departs);
                flight.put("Departure Date",departureDate);
                flight.put("Departure",departure);
                flight.put("Arrives",arrives);
                flight.put("Arrival Date",arrivalDate);
                flight.put("Arrival",arrival);
                flight.put("Economy Price",ecoPrice);
                flight.put("Business Price",bussPrice);
                flight.put("First Class Price",fclassPrice);

                departingFlights.add(flight);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return departingFlights;
    }

    public JSONArray getReturningFlights(String flightFrom, String flightTo, String dateReturn) {
        JSONObject flight = new JSONObject();
        JSONArray returningFlights = new JSONArray();

        String query = "SELECT * FROM Schedule s WHERE s.departs=? AND s.arrives=? AND s.departureDate=?";

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,flightFrom);
            ps.setString(2,flightTo);
            ps.setString(3,dateReturn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flight = new JSONObject();

                String flightNumber  = rs.getString("flightNumber");
                String departs = rs.getString("departs");
                String departureDate = rs.getString("departureDate");
                String departure = rs.getString("departure");
                String arrives = rs.getString("arrives");
                String arrivalDate = rs.getString("arrivalDate");
                String arrival = rs.getString("arrival");
                String ecoPrice = rs.getString("ecoPrice");
                String bussPrice = rs.getString("bussPrice");
                String fclassPrice = rs.getString("fclassPrice");

                flight.put("Flight Number",flightNumber);
                flight.put("Departs",departs);
                flight.put("Departure Date",departureDate);
                flight.put("Departure",departure);
                flight.put("Arrives",arrives);
                flight.put("Arrival Date",arrivalDate);
                flight.put("Arrival",arrival);
                flight.put("Economy Price",ecoPrice);
                flight.put("Business Price",bussPrice);
                flight.put("First Class Price",fclassPrice);

                returningFlights.add(flight);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returningFlights;
    }

    public JSONArray getFlightIdForBooking(String flightNumber, String departs, String arrives, String departureDate) {
        JSONObject flightId = new JSONObject();
        JSONArray id = new JSONArray();

        int fid = -1;

        String query = "SELECT f.id FROM Schedule f WHERE f.flightNumber=? AND f.departs=? AND f.departureDate=? AND f.arrives=?";

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, flightNumber);
            ps.setString(2, departs);
            ps.setString(3, departureDate);
            ps.setString(4, arrives);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                fid = rs.getInt("id");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        flightId.put("flightId",fid);
        id.add(flightId);

        return id;
    }

    public boolean BookFlight(int cid, int dflightId, String dclassType,String fare)
    {
        boolean rowInserted = false;
        String query = "INSERT INTO Bookings VALUES(?,?,?,?)";

        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, cid);
            ps.setInt(2, dflightId);
            ps.setString(3, dclassType);
            ps.setString(4, fare);

            rowInserted = ps.executeUpdate() > 0;

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}
