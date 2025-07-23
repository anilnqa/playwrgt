package tests.apitests;

import api.ApiReqHandler;
import api.services.EmployeeApi;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.Test;

public class EmployeeServiceTest extends BaseApiTest {
    EmployeeApi employeeApi;
    @Test
    public void getAllEmployees(){
        employeeApi = new EmployeeApi();
        ApiReqHandler reqHandler = employeeApi.serviceContext();
        APIResponse res = employeeApi.getAllEmployees(reqHandler,"/api/v1/employees");
        employeeApi.validateResponseStatusCode(res,200);
        employeeApi.validateGetAllEmployeeResponse(res);
    }

    @Test
    public void getEmployee(){
        employeeApi = new EmployeeApi();
        ApiReqHandler reqHandler = employeeApi.serviceContext();
        APIResponse res = employeeApi.getAllEmployees(reqHandler,"/api/v1/employee/11");
        employeeApi.validateResponseStatusCode(res,200);
    }

    @Test
    public void createEmployee(){
        employeeApi = new EmployeeApi();
        ApiReqHandler reqHandler = employeeApi.serviceContext();
        String reqBody = employeeApi.newEmpObjJson(33,"sqq",33,33333,"").toString();
        APIResponse res = employeeApi.createNewEmployee(reqHandler,"/api/v1/create",reqBody);
        employeeApi.validateResponseStatusCode(res,200);
    }


}
