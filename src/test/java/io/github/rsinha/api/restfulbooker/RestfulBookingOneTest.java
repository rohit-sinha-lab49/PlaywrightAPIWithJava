package io.github.rsinha.api.restfulbooker;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.github.rsinha.api.manager.BaseTest;
import io.github.rsinha.api.reqres.BookingData;
import io.qameta.allure.*;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.github.rsinha.api.reqres.BookingDataBuilder.getBookingData;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.util.ResultsUtils.OWNER_LABEL_NAME;
import static io.qameta.allure.util.ResultsUtils.SEVERITY_LABEL_NAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RestfulBookingOneTest extends BaseTest {


    @Test(enabled = false)
    public void createBookingTest() {
        System.out.println ("Current thread id is : "+Thread.currentThread ().getId ());
        Allure.description("This test attempts to create a booking");
        Allure.label(SEVERITY_LABEL_NAME, CRITICAL.value());
        Allure.label(OWNER_LABEL_NAME, "Rohit Sinha");
        Allure.link("Website", "https://restful-booker.herokuapp.com");
        Allure.issue("AUTH-123", "https://example.com/issues/AUTH-123");
        Allure.tms("TMS-456", "https://example.com/tms/TMS-456");
        APIResponse response = manager.postRequest("/booking", RequestOptions.create().setData(createBookingData));

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

    @Test(description = "To get the details of user with id 3", priority = 0)
    @Story ("GET Request with Valid User")
    @Severity (SeverityLevel.NORMAL)
    @Description ("Test Description : Verify the details of user of id-3")
    public void verifyUser() {
        APIResponse response = manager.getRequest ("/booking/" + Integer.parseInt ("1"));
        assertEquals(response.status(), 200);
        System.out.println("Response : "+response.text ());
    }

}
