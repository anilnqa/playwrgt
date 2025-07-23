package api.services;

import api.ApiReqHandler;
import com.google.gson.*;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import java.util.Map;

public class EmployeeApi {

    private Logger logger = LogManager.getLogger(OrderApi.class);
    String serviceBaseUrl = "https://dummy.restapiexample.com/";

    public EmployeeObj newEmpObj(int id,String eName,int age, int sal,String profileImg){
        EmployeeObj e = new EmployeeObj();
        e.id=id;
        e.employee_name= eName;
        e.employee_age= age;
        e.employee_salary= sal;
        e.profile_image = profileImg;
        return e;
    }

    public JsonObject newEmpObjJson(int id,String eName,int age, int sal,String profileImg){
        JsonObject jObj = new JsonObject();
        //jObj.addProperty("id",id);
        jObj.addProperty("name",eName);
        jObj.addProperty("salary",sal);
        jObj.addProperty("age",age);
        //jObj.addProperty("profile_image",profileImg);
        return jObj;
    }

    public ApiReqHandler serviceContext() {
        System.out.println(serviceBaseUrl);
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL(serviceBaseUrl));
        // Initialize API Client with request context
        ApiReqHandler apiReqHandler =  new ApiReqHandler(apiRequestContext);
        apiReqHandler.setBaseUrl(serviceBaseUrl);
        logger.info("✅ API Client initialized with Base URL: {}", serviceBaseUrl);
        return apiReqHandler;
    }

    public void createEmployee (Map<String,String> payload, String endPoint){

    }

    public void updateEmployee(Map<String,String> payload, String endPoint){

    }
    public void getEmployee(String endPoint){

    }

    @Step("Employee Get All all employees")
    public APIResponse getAllEmployees(ApiReqHandler apiReqHandler,String endPoint){
        APIResponse response = apiReqHandler.sendRequest("GET",endPoint,null,null,null,null);
        Allure.attachment("Response : " , response.text());
        Allure.attachment("Response Status : " , response.statusText());
        logger.info("🔹 Validating response status code...");
        apiReqHandler.validateResponse(response, 200);
        logger.info("✅ Response status code matches expected: {}", "200");
        return response;
    }

    @Step("Employee Create New employees")
    public APIResponse createNewEmployee(ApiReqHandler apiReqHandler,String endPoint,String employeeDetails){
        APIResponse response = apiReqHandler.sendRequest("POST",endPoint,null,null,null,employeeDetails);
        Allure.attachment("Response : " , response.text());
        Allure.attachment("Response Status : " , response.statusText());
        logger.info("🔹 Validating response status code...");
        apiReqHandler.validateResponse(response, 200);
        logger.info("✅ Response status code matches expected: {}", "200");
        return response;
    }

    public void validateResponseStatusCode(APIResponse res, int statusCode){
        new ApiReqHandler().validateResponse(res,statusCode);
    }

    public void deleteEmployee(String endPoint){

    }
    public void validateCreateResponse(String expResponse){

    }
    public void validateGetEmployeeResponse(String expResponseCode){
 }

    public void validateGetAllEmployeeResponse(APIResponse res) {
        boolean flg = false;
        logger.info("Validating in response JSON Object");
        JsonObject jobj = new Gson().fromJson(res.text(), JsonObject.class);
        JsonArray jsonArray = jobj.getAsJsonArray("data").getAsJsonArray();
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("id").getAsInt() == 21) {
                flg = obj.get("employee_age").getAsInt() == 30;
                break;
            }
        }
        if (flg) {
            logger.info("Passed- Validation");
            Allure.attachment("elament Validation in Response object", "Employee 11");
            Assert.assertEquals("11", "11");
        } else {
            logger.info("Failed- Validation");
            Allure.attachment("element Validation in Response object", "Employee 11");
            Assert.assertEquals("Failed at : ", "11");
        }
    }

    //jobj.getAsJsonArray("data").elements.stream().filter(k->k.getAsJsonObject().get("id").getAsInt()==21).filter(m->m.getAsJsonObject().get("employee_age").getAsInt()==30).count();



    class  EmployeeObj {
        private int id;
        private String employee_name;
        private int employee_salary;
        private int employee_age;
        private String profile_image;
    }

}




