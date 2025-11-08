package hooks;

import com.microsoft.playwright.*;
import Utilities.ConfigReader;

public class DriverFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void initBrowser() {
        ConfigReader.loadProperties();

        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        String browserName = ConfigReader.getProperty("browser").toLowerCase();

        playwright = Playwright.create();

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

        context = browser.newContext();
        page = context.newPage();
    }

    public static Page getPage() {
        return page;
    }

//    public static BrowserContext getContext() {
//        return context;
//    }

    public static void closeBrowser() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
