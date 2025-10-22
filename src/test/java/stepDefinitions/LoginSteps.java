package stepDefinitions;

import hooks.Hooks;
import pages.LoginPage;
import pages.InventoryPage;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("user navigates to SauceDemo login page")
    public void user_navigates_to_sauce_demo_login_page() {
        loginPage = new LoginPage(Hooks.page);
        loginPage.navigateToLoginPage();
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Then("user should see the product inventory page")
    public void user_should_see_the_product_inventory_page() {
        inventoryPage = new InventoryPage(Hooks.page);
        assertTrue(inventoryPage.isOnInventoryPage());
    }
}
