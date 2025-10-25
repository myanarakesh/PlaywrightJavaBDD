package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;

    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "FailedStepScreenshot");
        } else {
            // Optional: Capture screenshots for every step (not just failed)
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "StepScreenshot");
        }
        context.close();
        browser.close();
        playwright.close();
    }
}