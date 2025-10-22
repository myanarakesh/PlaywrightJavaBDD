package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    // Locators
    private String usernameField = "#user-name";
    private String passwordField = "#password";
    private String loginButton = "#login-button";

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

    public void clickLogin() {
        page.click(loginButton);
    }
}
