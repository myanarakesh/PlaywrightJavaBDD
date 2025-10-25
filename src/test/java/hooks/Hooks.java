package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import Utilities.ConfigReader;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;
    private static int stepCounter = 1;

    @Before
    public void setUp() {
        ConfigReader.loadProperties();
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        playwright = Playwright.create();
        switch (ConfigReader.getProperty("browser").toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setHeadless(headless));
                break;
            case "edge":
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setChannel("msedge")
                                .setHeadless(headless));
                break;
            default:
                browser = playwright.chromium().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setHeadless(headless));
        }
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario) {
        if (ConfigReader.getProperty("screenshotMode").equals("each_step")) {
            try {
                if (page != null) {
                    // Create screenshot folder if not exist
                    Path screenshotDir = Paths.get("target/ExtentReports/screenshots");
                    if (!Files.exists(screenshotDir)) {
                        Files.createDirectories(screenshotDir);
                    }
                    // Generate unique screenshot name
                    String stepName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                    String fileName = "Step_" + stepCounter + "_" + stepName + ".png";
                    Path filePath = screenshotDir.resolve(fileName);

                    // Take screenshot
                    page.screenshot(new Page.ScreenshotOptions().setPath(filePath));

                    // Attach to Extent / Allure report
                    byte[] fileContent = Files.readAllBytes(filePath);
                    String encoded = Base64.getEncoder().encodeToString(fileContent);

                    scenario.log("<b>Step Screenshot:</b>");
                    scenario.log("<img src=\"data:image/png;base64," + encoded + "\" width=\"600\" />");

                    stepCounter++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (ConfigReader.getProperty("screenshotMode").equals("end_only")) {
            if (scenario.isFailed()) {
                byte[] screenshot = page.screenshot();
                scenario.attach(screenshot, "image/png", "FailedStepScreenshot");
            } else {
                // Optional: Capture screenshots for every step (not just failed)
                byte[] screenshot = page.screenshot();
                scenario.attach(screenshot, "image/png", "StepScreenshot");
            }
        }
        context.close();
        browser.close();
        playwright.close();
    }
}