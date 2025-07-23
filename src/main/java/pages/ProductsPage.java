package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductsPage {
    Page page;
    Locator textProductsTitle;

    public ProductsPage(Page page){
        this.page= page;
        this.textProductsTitle= page.locator("xpath=//span[@data-test=\"title\"]");
    }

    @Step("Verify Current Page opened is Product Page")
    public void verifyProductsPageOK(String baseUrl){
        assertThat(textProductsTitle).isVisible();
        assertThat(textProductsTitle).hasText("Products");
        assertThat(page).hasURL(baseUrl + "inventory.html");
    }
}