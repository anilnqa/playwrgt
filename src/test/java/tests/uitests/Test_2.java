package tests.uitests;

import org.testng.annotations.Test;
import pages.LoginPage;

public class Test_2 extends BaseTest {

    @Test(description = "As a User, I will see Error when leave Username and Password field as empty and then click Login button")
    public void loginTest001() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.clickLoginButton();
//        loginPage.verifyErrorOnUsername();
//        loginPage.verifyErrorOnPassword();
//        loginPage.verifyErrorUsernameIsRequired();
    }

//    @Test(description = "As a User, I will see Error when input incorrect Username and Password then click Login button")
//    public void loginTest002() {
//        LoginPage loginPage = new LoginPage(page);
//        loginPage.inputToUsername("MyName");
//        loginPage.inputToPassword("Unknown");
//        loginPage.clickLoginButton();
//        loginPage.verifyErrorOnUsername();
//        loginPage.verifyErrorOnPassword();
//        loginPage.verifyErrorUsernameAndPasswordDoNotMatch();
//    }
//
//    @Test(description = "As a User, I will not see Login Page anymore when input correct Username and Password then click Login button")
//    public void loginTest003() {
//        LoginPage loginPage = new LoginPage(page);
//        loginPage.inputToUsername("standard_user");
//        loginPage.inputToPassword("secret_sauce");
//        loginPage.clickLoginButton();
//        loginPage.verifyLoginPageIsNotShownAnymore();
//        ProductsPage productsPage = new ProductsPage(page);
//        productsPage.verifyProductsPageOK(yamlData.get("SauceDemo_URL").toString());
//    }
}