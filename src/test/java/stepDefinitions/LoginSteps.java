package stepDefinitions;

import hooks.DriverFactory;
import pages.LoginPage;
import pages.InventoryPage;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("user navigates to SauceDemo login page")
    public void user_navigates_to_sauce_demo_login_page() {
        loginPage = new LoginPage(DriverFactory.getPage());
        loginPage.navigateToLoginPage();
    }

    @Then("user should see the product inventory page")
    public void user_should_see_the_product_inventory_page() {
        inventoryPage = new InventoryPage(DriverFactory.getPage());
        assertTrue(inventoryPage.isOnInventoryPage());
    }

    @When("user enters {string} and {string}")
    public void userEntersAnd(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("user should see {string} error on login screen")
    public void userShouldSeeErrorOnLoginScreen(String message) {
        loginPage.validateErrorMessage(message);
    }

    @Then("user logout")
    public void user_logout() {
        loginPage.logoutFromSauceDemo();
    }

}