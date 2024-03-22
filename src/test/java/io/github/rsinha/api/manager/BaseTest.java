package io.github.rsinha.api.manager;

import io.github.rsinha.api.reqres.BookingData;
import io.qameta.allure.Allure;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.github.rsinha.api.reqres.BookingDataBuilder.getBookingData;

public class BaseTest {

    public RequestManager manager;
    final String baseUrl = "https://restful-booker.herokuapp.com";
    public int bookingId;
    public String token;

    public BookingData createBookingData;

    @BeforeClass
    public void setUpBase(){
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        manager = new RequestManager ();
        manager.createPlaywright ();
        System.out.println (manager.toString ());
        Map<String, String> headers = new HashMap<> ();
        headers.put ("content-type", "application/json");
        headers.put ("Accept", "application/json");
        manager.setApiRequestContext (baseUrl, headers);
        Logger.getGlobal ().info ("Setup the request where baseURL : "+baseUrl);
        Logger.getGlobal ().info ("Headers : "+headers);

        Logger.getGlobal ().info ("Set Booking data ....");
        Allure.addAttachment("Base URL: ", baseUrl);
        Allure.addAttachment("headers: ", headers.toString ());
        createBookingData = getBookingData();

    }

    @AfterClass
    public void tearDown () {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        Logger.getGlobal ().info ("Dispose playwright request context");
        manager.disposeAPIRequestContext ();
        manager.closePlaywright ();
    }
}
