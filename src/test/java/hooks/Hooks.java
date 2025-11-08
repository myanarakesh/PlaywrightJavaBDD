package hooks;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import hooks.DriverFactory;
import Utilities.ConfigReader;

import java.nio.file.*;
import java.util.Base64;

public class Hooks {

    private static int stepCounter = 1;
    private Page page;

    @Before
    public void setUp() {
        DriverFactory.initBrowser();
        page = DriverFactory.getPage();
    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario) {

        if (!ConfigReader.getProperty("screenshotMode").equals("each_step")) {
            return;
        }

        try {
            Path screenshotDir = Paths.get("target/ExtentReports/screenshots");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            String stepName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String fileName = "Step_" + stepCounter + "_" + stepName + ".png";
            Path filePath = screenshotDir.resolve(fileName);

            page.screenshot(new Page.ScreenshotOptions().setPath(filePath));

            byte[] fileContent = Files.readAllBytes(filePath);
            String encoded = Base64.getEncoder().encodeToString(fileContent);

            scenario.log("<b>Screenshot:</b>");
            scenario.log("<img src=\"data:image/png;base64," + encoded + "\" width=\"600\" />");

            stepCounter++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        if (ConfigReader.getProperty("screenshotMode").equals("end_only")) {
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "EndOfScenario");
        }

        DriverFactory.closeBrowser();
    }
}