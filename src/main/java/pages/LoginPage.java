package pages;

import Utilities.utils;
import com.microsoft.playwright.Page;

import java.awt.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPage {
    private Page page;

    // Locators
    private String usernameField = "#user-name";
    private String passwordField = "#password";
    private String loginButton = "#login-button";
    private String errorMessage = "[data-test='error']";
    private String errorMessageCloseIcon = "[data-test='error-button']";
    private String errorMessageDiv = "//*[@id='login_button_container']/div/form/div[3]";
    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Actions
    public void navigateToLoginPage() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        page.fill(usernameField, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordField, password);
    }

    public void validateErrorMessage(String message){
        assertThat(page.locator(errorMessage)).hasText(message);
        assertThat(page.locator(errorMessageDiv)).
                hasCSS("background-color", utils.hexToRgb("#e2231a"));
        page.locator(errorMessageCloseIcon).click();
        assertThat(page.locator(errorMessageCloseIcon)).not().isVisible();
    }

    public void clickLogin() {
        page.click(loginButton);
    }
}
