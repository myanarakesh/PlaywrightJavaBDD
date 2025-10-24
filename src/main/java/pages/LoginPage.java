package pages;

import Utilities.utils;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPage {
    private Page page;

    // Locators
    private String logoEle = ".login_logo";
    private String usernameFieldEle = "#user-name";
    private String passwordFieldEle = "#password";
    private String loginButtonEle = "#login-button";
    private String errorMessageEle = "[data-test='error']";
    private String errorMessageCloseIconEle = "[data-test='error-button']";
    private String errorMessageDivEle = "//*[@id='login_button_container']/div/form/div[3]";
    private String hambergerMenuEle = "#react-burger-menu-btn";
    private String logoutLinkEle = "#logout_sidebar_link";
    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Actions
    public void navigateToLoginPage() {
        page.navigate("https://www.saucedemo.com/");
        assertThat(page.locator(logoEle)).isVisible();
        assertThat(page).hasTitle("Swag Labs");
    }

    public void enterUsername(String username) {
        page.fill(usernameFieldEle, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordFieldEle, password);
    }

    public void validateErrorMessage(String message){
        assertThat(page.locator(errorMessageEle)).hasText(message);
        assertThat(page.locator(errorMessageDivEle)).
                hasCSS("background-color", utils.hexToRgb("#e2231a"));
        page.locator(errorMessageCloseIconEle).click();
        assertThat(page.locator(errorMessageCloseIconEle)).not().isVisible();
    }

    public void clickLogin() {
        page.click(loginButtonEle);
    }

    public void logoutFromSauceDemo(){
        page.click(hambergerMenuEle);
        page.click(logoutLinkEle);
        assertThat(page).hasURL("https://www.saucedemo.com/");
        assertThat(page.locator(loginButtonEle)).isVisible();
    }

}
