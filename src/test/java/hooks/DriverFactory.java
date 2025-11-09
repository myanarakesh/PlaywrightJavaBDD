package hooks;

import com.microsoft.playwright.*;
import Utilities.ConfigReader;

public class DriverFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void initBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (browser == null) {
            ConfigReader.loadProperties();

            boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
            String browserName = ConfigReader.getProperty("browser").toLowerCase();

            switch (browserName) {
                case "firefox":
                    browser = playwright.firefox().launch(
                            new BrowserType.LaunchOptions().setHeadless(headless));
                    break;

                case "webkit":
                    browser = playwright.webkit().launch(
                            new BrowserType.LaunchOptions().setHeadless(headless));
                    break;

                case "edge":
                    browser = playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setChannel("msedge")
                                    .setHeadless(headless));
                    break;

                case "chromium":
                    browser = playwright.chromium().launch(
                            new BrowserType.LaunchOptions().setHeadless(headless));
                    break;

                default:
                    throw new RuntimeException("Invalid browser in config.properties: " + browserName);
            }
        }

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
        );

        page = context.newPage();
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        context = null;
        browser = null;
        playwright = null;
    }
}
