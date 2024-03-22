package io.github.rsinha.api.restfulbooker;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.github.rsinha.api.manager.BaseTest;
import io.github.rsinha.api.reqres.BookingData;
import io.github.rsinha.api.reqres.CreateToken;
import io.github.rsinha.api.reqres.PartialBookingData;
import io.github.rsinha.api.reqres.TokenBuilder;
import io.qameta.allure.*;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.github.rsinha.api.reqres.BookingDataBuilder.getBookingData;
import static io.github.rsinha.api.reqres.BookingDataBuilder.getPartialBookingData;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.util.ResultsUtils.OWNER_LABEL_NAME;
import static io.qameta.allure.util.ResultsUtils.SEVERITY_LABEL_NAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RestfulBookingEnd2EndTest extends BaseTest {

    @Step ("Create a booking")
    @Test
    public void createBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        Allure.description("This test attempts to create a booking");
        Allure.label(SEVERITY_LABEL_NAME, CRITICAL.value());
        Allure.label(OWNER_LABEL_NAME, "Rohit Sinha");
        Allure.link("Website", "https://restful-booker.herokuapp.com");
        Allure.issue("AUTH-123", "https://example.com/issues/AUTH-123");
        Allure.tms("TMS-456", "https://example.com/tms/TMS-456");
        Allure.addAttachment ("Endpoint : ","/booking");
        APIResponse response = manager.postRequest("/booking", RequestOptions.create().setData(createBookingData));
        Allure.addAttachment("Response from api request : ", response.text ());

        assertEquals(response.status(), 200);
        if(response.status ()==200){
            System.out.println(response.text ());
        }

        JSONObject responseObject = new JSONObject(response.text());
        JSONObject bookingObject = responseObject.getJSONObject("booking");
        JSONObject bookingDatesObject = bookingObject.getJSONObject("bookingdates");

        assertNotNull(responseObject.get("bookingid"));

        assertEquals(createBookingData.getFirstname(), bookingObject.get("firstname"));
        assertEquals(createBookingData.getBookingdates().getCheckin(), bookingDatesObject.get("checkin"));
        bookingId = responseObject.getInt("bookingid");

        System.out.println(bookingId);
    }

    @Description ("This test attempts to get the details of newly created booking")
    @Owner ("Rohit Sinha")
    @Severity (CRITICAL)
    @Test(dependsOnMethods = {"createBookingTest"})
    public void getBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        APIResponse response = manager.getRequest ("/booking/" + bookingId);
        Allure.addAttachment("Response from api request : ", response.text ());

        assertEquals(response.status(), 200);

        JSONObject responseObject = new JSONObject(response.text());
        JSONObject bookingDatesObject = responseObject.getJSONObject("bookingdates");

        assertEquals(createBookingData.getFirstname(), responseObject.get("firstname"));
        assertEquals(createBookingData.getBookingdates()
                .getCheckin(), bookingDatesObject.get("checkin"));
    }

    @Test(dependsOnMethods = {"getBookingTest"})
    public void generateTokenTest(){
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        CreateToken createToken = TokenBuilder.getToken ();
        //Store value of token in the object of CreateToken class
        APIResponse response = manager.postRequest ("/auth",RequestOptions.create ().setData (createToken));
        Allure.addAttachment("Response from api request : ", response.text ());
        assertEquals(response.status(), 200);

        JSONObject responseObject = new JSONObject(response.text());
        String tokenValue = responseObject.getString("token");
        assertNotNull(tokenValue);
        token = tokenValue;
    }

    @Test(dependsOnMethods = {"generateTokenTest"})
    public void updateBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        //import static method getBookingData() from BookingDataBuilder class
        BookingData updateBookingData = getBookingData();
        APIResponse response = manager.putRequest("/booking/" + bookingId, RequestOptions.create().setData(updateBookingData)
                .setHeader("Cookie", "token=" + token));
        Allure.addAttachment("Response from api request : ", response.text ());
        assertEquals(response.status(), 200);

        if(response.status ()==200){
            System.out.println(response.text ());
        }

        JSONObject responseObject = new JSONObject(response.text());
        JSONObject bookingDatesObject = responseObject.getJSONObject("bookingdates");

        assertEquals(updateBookingData.getFirstname(), responseObject.get("firstname"));
        assertEquals(updateBookingData.getBookingdates()
                .getCheckout(), bookingDatesObject.get("checkout"));
    }

    @Test(dependsOnMethods = {"updateBookingTest"})
    public void patchBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        //import static method getPartialBookingData() from BookingDataBuilder class
        PartialBookingData partialBookingData = getPartialBookingData ();
        APIResponse response = manager.patchRequest ("/booking/" + bookingId, RequestOptions.create()
                .setData(partialBookingData)
                .setHeader("Cookie", "token=" + token));
        Allure.addAttachment("Response from api request : ", response.text ());

        assertEquals(response.status(), 200);

        if(response.status ()==200){
            System.out.println(response.text ());
        }

        JSONObject responseObject = new JSONObject(response.text());

        assertEquals(partialBookingData.getFirstname(), responseObject.get("firstname"));
        assertEquals(partialBookingData.getTotalprice (), responseObject.get("totalprice"));
    }

    @Test(dependsOnMethods = {"patchBookingTest"})
    public void deleteBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        APIResponse response = manager.deleteRequest("/booking/" + bookingId, RequestOptions.create()
                .setHeader("Cookie", "token=" + token));
        Allure.addAttachment("Response from api request : ", response.text ());

        assertEquals(response.status(), 201);
        if(response.status ()==201){
            System.out.println(response.text ());
        }
    }

    @Test(dependsOnMethods = {"deleteBookingTest"})
    public void testBookingDeleted() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        APIResponse response = manager.getRequest("/booking/" + bookingId);
        Allure.addAttachment("Response from api request : ", response.text ());
        if(response.status ()!=201){
            System.out.println ("Booking is already deleted");
        }
    }
}
