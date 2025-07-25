package api.services;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.util.Map;

import api.ApiReqHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ApiConfigReader;

public class OrderApi {

    private Logger logger = LogManager.getLogger(OrderApi.class);

    private Playwright playwright;
    private APIRequestContext requestContext;
    private ApiReqHandler apiClient;
    private APIResponse response;
    private String orderId;

    public void the_store_API_is_available() {
        String baseUrl = ApiConfigReader.getApiBaseUrl();
        System.out.println(baseUrl);
        playwright = Playwright.create();
        requestContext = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL(baseUrl));

        // Initialize API Client with request context
        apiClient = new ApiReqHandler(requestContext);

        logger.info("✅ API Client initialized with Base URL: {}", baseUrl);
    }

    public void an_existing_order_with_ID(int orderId) {
        this.orderId = String.valueOf(orderId);

        // ✅ Prepare request body
        String requestBody = String.format("""
        {
            "id": %d,
            "petId": 1,
            "quantity": 1,
            "shipDate": "2025-02-25T08:04:31.972Z",
            "status": "placed",
            "complete": true
        }
        """, orderId);

        logger.info("🔹 Sending request to create order with ID: {}", orderId);

        // ✅ Send POST request using APIClient
        response = apiClient.sendRequest("POST", "/store/order",
                Map.of("accept", "application/json", "Content-Type", "application/json"),
                null, null, requestBody);

        logger.info("📨 Response Status: {}", response.status());
        logger.debug("📩 Response Body: {}", response.text());

        // ✅ Validate Response
        Assert.assertEquals(response.status(), 200, "❌ Order creation failed!");
        logger.info("✅ Order successfully created with ID: {}", orderId);
    }

    public void a_GET_request_is_sent_to_retrieve_the_order() {
        logger.info("🔹 Sending GET request to retrieve order with ID: {}", orderId);

        // ✅ Send GET request using APIClient
        response = apiClient.sendRequest("GET", "/store/order/{orderId}",
                Map.of("accept", "application/json"), null,
                Map.of("orderId", orderId), null);

        logger.info("📨 Response Status: {}", response.status());
        logger.debug("📩 Response Body: {}", response.text());
    }

    public void the_response_status_code_should_be(int expectedStatusCode) {
        logger.info("🔹 Validating response status code...");
        apiClient.validateResponse(response, expectedStatusCode);
        logger.info("✅ Response status code matches expected: {}", expectedStatusCode);
    }

    public void the_order_details_should_be_displayed() {
        try {
            logger.info("🔹 Validating order details...");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseBody = objectMapper.readValue(response.text(), Map.class);
            Assert.assertEquals(responseBody.get("id"), Integer.parseInt(orderId), "❌ Order details mismatch!");

            logger.info("✅ Order details match for ID: {}", orderId);
        } catch (Exception e) {
            logger.error("❌ Failed to parse or validate JSON response!", e);
            throw new RuntimeException(e);
        }
    }

    public void an_order_ID_that_does_not_exist(String invalidOrderId) {
        this.orderId = invalidOrderId;
        logger.info("🔹 Testing with invalid order ID: {}", invalidOrderId);
    }

    public void an_error_message_should_be_returned(String expectedErrorMessage) {
        try {
            String responseBody = response.text();
            logger.info("🔹 Validating error message...");

            // ✅ Parse JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseJson = objectMapper.readValue(responseBody, Map.class);

            // ✅ Expected Response Structure
            int expectedCode = 1;
            String expectedType = "error";

            // ✅ Assert All Fields
            Assert.assertEquals(responseJson.get("code"), expectedCode, "❌ Mismatched 'code' field!");
            Assert.assertEquals(responseJson.get("type"), expectedType, "❌ Mismatched 'type' field!");
            Assert.assertEquals(responseJson.get("message"), expectedErrorMessage, "❌ Mismatched 'message' field!");

            logger.info("✅ Error response validation passed!");

        } catch (Exception e) {
            logger.error("❌ Failed to parse or validate JSON response!", e);
            throw new RuntimeException(e);
        }
    }


}
