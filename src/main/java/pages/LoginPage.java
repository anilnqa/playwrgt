package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    Locator buttonSignIn;

    Locator headerSignIn;
    Locator logo;
    Locator search;

    Page page;

    public LoginPage(Page page){

        this.logo = page.locator("xpath=//div[@id='nav-logo']");
        this.headerSignIn= page.locator("xpath=//div[@id='nav-link-accountList']/a");
        this.search=page.locator("xpath=//input[@id='twotabsearchtextbox']");
        this.buttonSignIn= page.locator("xpath=//a[@class='nav-action-signin-button']");
        this.page = page;
   }

    @Step("Verify Login Page opened OK")
    public void verifyLoginPageOK(){
        assertThat(logo).isVisible();
//        assertThat(headerSignIn).isVisible();
//        assertThat(search).hasAttribute("placeholder", "Search Amazon.in");

    }

    @Step("Verify Login Page opened OK but the Test Case is failed")
    public void verifyLoginPageFailed(){
        assertThat(headerSignIn).isVisible();
//        assertThat(inputUsername).isVisible();
//        assertThat(inputPassword).isVisible();
//        assertThat(buttonLogin).isVisible();
//        assertThat(textHeaderLogin).hasText("Swag Labs");
//        assertThat(inputUsername).hasAttribute("placeholder", "Username");
//        assertThat(inputPassword).hasAttribute("placeholder", "Password");
//        assertThat(buttonLogin).hasAttribute("value", "Logins");
    }

    @Step("Click Login Button")
    public void clickLoginButton(){
        assertThat(logo).isVisible();
        assertThat(headerSignIn).isVisible();
        assertThat(search).hasAttribute("placeholder", "Search Amazon.in");
        // setting "force" value as "true"
        Locator.HoverOptions hoverOptions = new Locator.HoverOptions();
        hoverOptions.setTimeout(5000);
        headerSignIn.hover(hoverOptions);
        buttonSignIn.click();
    }

    @Step("Verify error on field Username")
    public void verifyErrorOnUsername(){
        //assertThat(iconErrorUsername).isVisible();
    }

    @Step("Verify error on field Password")
    public void verifyErrorOnPassword(){
        //assertThat(iconErrorPassword).isVisible();
    }

    @Step("Verify error \"Username is required\"")
    public void verifyErrorUsernameIsRequired(){
//        assertThat(textHeaderError).isVisible();
//        assertThat(buttonError).isVisible();
//        assertThat(iconButtonError).isVisible();
//        assertThat(textHeaderError).hasText("Epic sadface: Username is required");
    }

    @Step("Input into Username field")
    public void inputToUsername(String text){
//        inputUsername.clear();
//        inputUsername.fill(text);
    }

    @Step("Input into Password field")
    public void inputToPassword(String text){
//        inputPassword.clear();
//        inputPassword.fill(text);
    }

    @Step("Verify error \"Username and Password do not match\"")
    public void verifyErrorUsernameAndPasswordDoNotMatch(){
//        assertThat(textHeaderError).isVisible();
//        assertThat(buttonError).isVisible();
//        assertThat(iconButtonError).isVisible();
//        assertThat(textHeaderError).hasText("Epic sadface: Username and password do not match any user in this service");
    }

    @Step("Verify Login Page is not shown anymore")
    public void verifyLoginPageIsNotShownAnymore(){
//        assertThat(textHeaderLogin).hasCount(0);
//        assertThat(inputUsername).hasCount(0);
//        assertThat(inputPassword).hasCount(0);
//        assertThat(buttonLogin).hasCount(0);
    }
}
