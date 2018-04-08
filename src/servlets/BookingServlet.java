package servlets;

import handlers.BookingHandler;
import handlers.FlightHandler;
import handlers.SignupHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class BookingServlet extends HttpServlet{
    private static String USER_NAME = "msn.2494";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "password"; // GMail password
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("startBooking"))
            startBooking(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("getDepartingFlights"))
            getDepartingFlights(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("getReturningFlights"))
            getReturningFlights(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("bookFlight"))
            bookFlight(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("getPaymentDetails"))
            getPaymentDetails(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("makePayment"))
            makePayment(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("sendMail"))
            sendMail(request,response);
        else if(Jsoup.clean(request.getParameter("request"), Whitelist.basic()).equals("logout"))
            logout(request,response);
    }



    private void startBooking(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();
        if(session.getAttribute("name") == null) {

            myobj.put("success", false);
            myobj.put("request", Jsoup.clean(request.getParameter("request"), Whitelist.basic()));

            out.write(myobj.toJSONString());
        }
        else {
            String reservation_type = Jsoup.clean(request.getParameter("reservation_type"), Whitelist.basic());
            if (reservation_type.equals("1"))
                session.setAttribute("reservation_type", "return");
            else if (reservation_type.equals("2"))
                session.setAttribute("reservation_type", "one-way");

            session.setAttribute("flight-from", Jsoup.clean(request.getParameter("flight-from"), Whitelist.basic()));
            session.setAttribute("date-depart", Jsoup.clean(request.getParameter("date-depart"), Whitelist.basic()));
            session.setAttribute("flight-to", Jsoup.clean(request.getParameter("flight-to"), Whitelist.basic()));
            if (reservation_type.equals("1")) {
                session.setAttribute("date-return", Jsoup.clean(request.getParameter("date-return"), Whitelist.basic()));
            } else if (reservation_type.equals("2")) {
                session.setAttribute("date-return", "-");
            }
            //session.setAttribute("num_seats",Jsoup.clean(request.getParameter("num_seats"), Whitelist.basic()));

            myobj.put("success", true);
            myobj.put("request", Jsoup.clean(request.getParameter("request"), Whitelist.basic()));

            out.write(myobj.toJSONString());
        }

    }

    private void getDepartingFlights(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();
        HttpSession session = request.getSession();
        String flightFrom = session.getAttribute("flight-from").toString();
        String flightTo = session.getAttribute("flight-to").toString();
        String dateDepart = session.getAttribute("date-depart").toString();

        JSONArray departingFlights = new BookingHandler().getDepartingFlights(flightFrom,flightTo,dateDepart);

        myobj.put("request",Jsoup.clean(request.getParameter("request"), Whitelist.basic()));
        myobj.put("departingFlights",departingFlights);
        out.write(myobj.toJSONString());

    }

    private void getReturningFlights(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();
        HttpSession session = request.getSession();
        String flightTo = session.getAttribute("flight-from").toString();
        String flightFrom = session.getAttribute("flight-to").toString();
        String dateReturn = session.getAttribute("date-return").toString();

        JSONArray returningFlights = new BookingHandler().getReturningFlights(flightFrom,flightTo,dateReturn);

        myobj.put("request",Jsoup.clean(request.getParameter("request"), Whitelist.basic()));
        myobj.put("returningFlights",returningFlights);
        out.write(myobj.toJSONString());

    }

    private void bookFlight(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();
        session.setAttribute("dflightNumber",Jsoup.clean(request.getParameter("dflightNumber"), Whitelist.basic()));
        session.setAttribute("ddeparts",Jsoup.clean(request.getParameter("ddeparts"), Whitelist.basic()));
        session.setAttribute("ddepartureDate",Jsoup.clean(request.getParameter("ddepartureDate"), Whitelist.basic()));
        session.setAttribute("ddeparture",Jsoup.clean(request.getParameter("ddeparture"), Whitelist.basic()));
        session.setAttribute("darrives",Jsoup.clean(request.getParameter("darrives"), Whitelist.basic()));
        session.setAttribute("darrivalDate",Jsoup.clean(request.getParameter("darrivalDate"), Whitelist.basic()));
        session.setAttribute("darrival",Jsoup.clean(request.getParameter("darrival"), Whitelist.basic()));
        session.setAttribute("decoPrice",Jsoup.clean(request.getParameter("decoPrice"), Whitelist.basic()));
        session.setAttribute("dbussPrice",Jsoup.clean(request.getParameter("dbussPrice"), Whitelist.basic()));
        session.setAttribute("dfclassPrice",Jsoup.clean(request.getParameter("dfclassPrice"), Whitelist.basic()));
        session.setAttribute("dseatType",Jsoup.clean(request.getParameter("dseatType"), Whitelist.basic()));
        JSONArray dflightId = new FlightHandler().getFlightIdForBooking(Jsoup.clean(request.getParameter("dflightNumber"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("ddeparts"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("darrives"), Whitelist.basic()),
                Jsoup.clean(request.getParameter("ddepartureDate"), Whitelist.basic()));
        JSONObject dfid = (JSONObject) dflightId.get(0);
        int dFlightID = (int) dfid.get("flightId");
        session.setAttribute("dflightId",dFlightID);

        if(session.getAttribute("reservation_type").toString().equals("return")) {
            session.setAttribute("rflightNumber", Jsoup.clean(request.getParameter("rflightNumber"), Whitelist.basic()));
            session.setAttribute("rdeparts", Jsoup.clean(request.getParameter("rdeparts"), Whitelist.basic()));
            session.setAttribute("rdepartureDate", Jsoup.clean(request.getParameter("rdepartureDate"), Whitelist.basic()));
            session.setAttribute("rdeparture", Jsoup.clean(request.getParameter("rdeparture"), Whitelist.basic()));
            session.setAttribute("rarrives", Jsoup.clean(request.getParameter("rarrives"), Whitelist.basic()));
            session.setAttribute("rarrivalDate", Jsoup.clean(request.getParameter("rarrivalDate"), Whitelist.basic()));
            session.setAttribute("rarrival", Jsoup.clean(request.getParameter("rarrival"), Whitelist.basic()));
            session.setAttribute("recoPrice", Jsoup.clean(request.getParameter("recoPrice"), Whitelist.basic()));
            session.setAttribute("rbussPrice", Jsoup.clean(request.getParameter("rbussPrice"), Whitelist.basic()));
            session.setAttribute("rfclassPrice", Jsoup.clean(request.getParameter("rfclassPrice"), Whitelist.basic()));
            session.setAttribute("rseatType", request.getParameter("rseatType"));
            JSONArray rflightId = new FlightHandler().getFlightIdForBooking(Jsoup.clean(request.getParameter("rflightNumber"), Whitelist.basic()),
                    Jsoup.clean(request.getParameter("rdeparts"), Whitelist.basic()),
                    Jsoup.clean(request.getParameter("rarrives"), Whitelist.basic()),
                    Jsoup.clean(request.getParameter("rdepartureDate"), Whitelist.basic()));
            JSONObject rfid = (JSONObject) rflightId.get(0);
            int rFlightID = (int) rfid.get("flightId");
            session.setAttribute("rflightId",rFlightID);
        }
        else
        {
            session.removeAttribute("rflightNumber");
            session.removeAttribute("rdeparts");
            session.removeAttribute("rdepartureDate");
            session.removeAttribute("rdeparture");
            session.removeAttribute("rarrives");
            session.removeAttribute("rarrivalDate");
            session.removeAttribute("rarrival");
            session.removeAttribute("recoPrice");
            session.removeAttribute("rbussPrice");
            session.removeAttribute("rfclassPrice");
            session.removeAttribute("rseatType");
            session.removeAttribute("rflightId");
        }
        JSONArray departingArray = new JSONArray();
        JSONObject departingFlight= new JSONObject();

        departingFlight.put("Flight Number",session.getAttribute("dflightNumber").toString());
        departingFlight.put("Departure Date",session.getAttribute("ddepartureDate").toString());
        departingFlight.put("Departs",session.getAttribute("ddeparts").toString());
        departingFlight.put("Departure",session.getAttribute("ddeparture").toString());
        departingFlight.put("Arrival Date",session.getAttribute("darrivalDate").toString());
        departingFlight.put("Arrives",session.getAttribute("darrives").toString());
        departingFlight.put("Arrival",session.getAttribute("darrival").toString());
        String dseatType = session.getAttribute("dseatType").toString();
        departingFlight.put("Class Type",dseatType);
        if(dseatType.equals("Economy"))
            departingFlight.put("Price",session.getAttribute("decoPrice"));
        else if(dseatType.equals("Business"))
            departingFlight.put("Price",session.getAttribute("dbussPrice"));
        else if(dseatType.equals("First Class"))
            departingFlight.put("Price",session.getAttribute("dfclassPrice"));

        departingArray.add(departingFlight);
        myobj.put("departingFlight",departingArray);

        if(session.getAttribute("reservation_type").toString().equals("return")) {
            JSONArray returningArray = new JSONArray();
            JSONObject returningFlight = new JSONObject();
            returningFlight.put("Flight Number", session.getAttribute("rflightNumber").toString());
            returningFlight.put("Departure Date", session.getAttribute("rdepartureDate").toString());
            returningFlight.put("Departs", session.getAttribute("rdeparts").toString());
            returningFlight.put("Departure", session.getAttribute("rdeparture").toString());
            returningFlight.put("Arrival Date", session.getAttribute("rarrivalDate").toString());
            returningFlight.put("Arrives", session.getAttribute("rarrives").toString());
            returningFlight.put("Arrival", session.getAttribute("rarrival").toString());
            String rseatType = session.getAttribute("rseatType").toString();
            returningFlight.put("Class Type",rseatType);
            if(rseatType.equals("Economy"))
                returningFlight.put("Price",session.getAttribute("recoPrice"));
            else if(rseatType.equals("Business"))
                returningFlight.put("Price",session.getAttribute("rbussPrice"));
            else if(rseatType.equals("First Class"))
                returningFlight.put("Price",session.getAttribute("rfclassPrice"));
            returningArray.add(returningFlight);
            myobj.put("returningFlight",returningArray);
        }

        JSONArray customerArray = new JSONArray();
        JSONObject customerDetails = new JSONObject();
        customerDetails.put("Name",session.getAttribute("name"));
        customerDetails.put("Email",session.getAttribute("email"));
        customerDetails.put("Contact Number",session.getAttribute("number"));
        customerArray.add(customerDetails);
        myobj.put("customerDetails",customerArray);

        myobj.put("reservationType",session.getAttribute("reservation_type").toString());
        myobj.put("success",true);
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());
    }

    private void getPaymentDetails(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();
        JSONArray paymentDetails = new JSONArray();
        JSONObject payment = new JSONObject();
        int departFare = 0;
        int returnFare = 0;
        int fare = 0;
        double tax = 0;
        double total = 0;
        String dseatType = session.getAttribute("dseatType").toString();
        if(dseatType.equals("Economy"))
            departFare = Integer.valueOf(session.getAttribute("decoPrice").toString());
        else if(dseatType.equals("Business"))
            departFare = Integer.valueOf(session.getAttribute("dbussPrice").toString());
        else if(dseatType.equals("First Class"))
            departFare = Integer.valueOf(session.getAttribute("dfclassPrice").toString());

        if(session.getAttribute("reservation_type").toString().equals("return")) {
            String rseatType = session.getAttribute("rseatType").toString();
            if (rseatType.equals("Economy"))
                returnFare = Integer.valueOf(session.getAttribute("recoPrice").toString());
            else if (rseatType.equals("Business"))
                returnFare = Integer.valueOf(session.getAttribute("rbussPrice").toString());
            else if (rseatType.equals("First Class"))
                returnFare = Integer.valueOf(session.getAttribute("rfclassPrice").toString());
        }
        fare = departFare + returnFare;
        tax = (departFare* 0.20) + (returnFare*0.20);
        total = fare + tax;
        session.setAttribute("dfare",departFare);
        session.setAttribute("rfare",returnFare);
        payment.put("Fare",fare);
        payment.put("Taxes",tax);
        payment.put("Total",total);
        paymentDetails.add(payment);

        myobj.put("paymentDetails",paymentDetails);
        myobj.put("success",true);
        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());

    }

    private void makePayment(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();

        int cid = (int) session.getAttribute("id");
        String dfare = session.getAttribute("dfare").toString();
        String dclassType = session.getAttribute("dseatType").toString();
        int dflightId = (int)session.getAttribute("dflightId");
        boolean dbooked = new BookingHandler().BookFlight(cid,dflightId,dclassType,dfare);


        int rflightId;
        String rclassType;
        String rfare;
        boolean rbooked = true;
        if(session.getAttribute("reservation_type").toString().equals("return")) {
            rfare = session.getAttribute("rfare").toString();
            rclassType = session.getAttribute("rseatType").toString();
            rflightId = (int) session.getAttribute("rflightId");
            rbooked = new BookingHandler().BookFlight(cid,rflightId,rclassType,rfare);
        }

        if(dbooked && session.getAttribute("reservation_type").toString().equals("one-way"))
        {
            myobj.put("success",true);
        }
        else if(dbooked && session.getAttribute("reservation_type").toString().equals("return"))
        {
            if(rbooked)
                myobj.put("success",true);
            else
                myobj.put("success",false);
        }
        else
        {
            myobj.put("success",false);
        }

        myobj.put("request",request.getParameter("request"));

        out.write(myobj.toJSONString());
    }

    private void sendMail(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myobj = new JSONObject();

        HttpSession session = request.getSession();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { session.getAttribute("email").toString() }; // list of recipient email addresses
        String subject = "Flight Booking";
        String body = "Welcome aboard " + session.getAttribute("name").toString() + "!\n\n";
        body += "Departing Flight\n";
        body += "\nFight Id: " + session.getAttribute("dflightId").toString();
        body += "\nFight Number: " + session.getAttribute("dflightNumber").toString();
        body += "\nDeparts: " + session.getAttribute("ddeparts").toString();
        body += "\nDeparture time: " + session.getAttribute("ddepartureDate").toString();
        body += "\nDeparture: " + session.getAttribute("ddeparture").toString();
        body += "\nArrives: " + session.getAttribute("darrives").toString();
        body += "\nArrival date: " + session.getAttribute("darrivalDate").toString();
        body += "\nArrival: " + session.getAttribute("darrival").toString();
        body += "\nClass type: " + session.getAttribute("dseatType").toString();
        body += "\nFare: PKR." + session.getAttribute("dfare").toString();

        if(session.getAttribute("reservation_type").toString().equals("return")) {
            body += "\n\nReturning Flight\n";
            body += "\nFight Id: " + session.getAttribute("rflightId").toString();
            body += "\nFight Number: " + session.getAttribute("rflightNumber").toString();
            body += "\nDeparts: " + session.getAttribute("rdeparts").toString();
            body += "\nDeparture time: " + session.getAttribute("rdepartureDate").toString();
            body += "\nDeparture: " + session.getAttribute("rdeparture").toString();
            body += "\nArrives: " + session.getAttribute("rarrives").toString();
            body += "\nArrival date: " + session.getAttribute("rarrivalDate").toString();
            body += "\nArrival: " + session.getAttribute("rarrival").toString();
            body += "\nClass type: " + session.getAttribute("rseatType").toString();
            body += "\nFare: PKR." + session.getAttribute("rfare").toString();
        }

        body += "\n\nCustomer Id: " + session.getAttribute("id");
        body += "\nContact number: " + session.getAttribute("number");

        body += "\n\nWe hope you have a wonderful journey!\n\n\n\n\n\n\n\n";


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session ssession = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(ssession);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = ssession.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }


        myobj.put("success",true);
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
