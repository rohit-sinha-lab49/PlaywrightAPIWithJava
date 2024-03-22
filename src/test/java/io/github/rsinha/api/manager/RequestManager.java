package io.github.rsinha.api.manager;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Map;
import java.util.logging.Logger;

public class RequestManager {

    private Playwright playwright;
    //APIRequestContext is an interface in Playwright test framework which is used to trigger API endpoints,
    //prepare environment or the service for your e2e test
    private APIRequestContext apiRequestContext;

    public void createPlaywright(){
        //Initialize Playwright
        playwright = Playwright.create();
    }

    public void setApiRequestContext(String baseUrl, Map<String, String> headers){
        Logger.getGlobal ().info ("Base url : "+baseUrl+" & Headers : "+headers);
        APIRequest apiRequest = playwright.request ();
        APIRequest.NewContextOptions newContextOptions = new APIRequest.NewContextOptions ().setBaseURL (baseUrl).setExtraHTTPHeaders (headers);
        apiRequestContext = apiRequest.newContext (newContextOptions);
    }

    public APIResponse getRequest(String endpoint){
        Logger.getGlobal ().info ("Endpoint for making get request is : "+endpoint);
        return apiRequestContext.get (endpoint);
    }

    public APIResponse getRequest(String endpoint, RequestOptions options) {
        Logger.getGlobal ().info ("Endpoint for making get request is : "+endpoint);
        return apiRequestContext.get(endpoint, options);
    }

    public APIResponse postRequest(String endpoint) {
        return apiRequestContext.post(endpoint);
    }

    public APIResponse postRequest(String endpoint, RequestOptions options) {
        Logger.getGlobal ().info ("Endpoint for making post request is : "+endpoint);
        return apiRequestContext.post(endpoint, options);
    }

    public APIResponse putRequest(String endpoint) {
        return apiRequestContext.put(endpoint);
    }

    public APIResponse putRequest(String endpoint, RequestOptions options) {
        Logger.getGlobal ().info ("Endpoint for making put request is : "+endpoint);
        return apiRequestContext.put(endpoint, options);
    }

    public APIResponse patchRequest(String endpoint) {
        return apiRequestContext.patch(endpoint);
    }

    public APIResponse patchRequest(String endpoint, RequestOptions options) {
        Logger.getGlobal ().info ("Endpoint for making patch request is : "+endpoint);
        return apiRequestContext.patch(endpoint, options);

    }

    public APIResponse deleteRequest(String endpoint) {
        return apiRequestContext.delete(endpoint);
    }

    public APIResponse deleteRequest(String endpoint, RequestOptions options) {
        Logger.getGlobal ().info ("Endpoint for making delete request is : "+endpoint);
        return apiRequestContext.delete(endpoint, options);
    }

    public void disposeAPIRequestContext() {
        apiRequestContext.dispose();
    }

    public void closePlaywright() {
        playwright.close();
    }

}
