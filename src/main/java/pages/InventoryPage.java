package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;


public class InventoryPage {
    private Page page;
    private String title = ".title";
    private String menuItemEles = "a.bm-item.menu-item";

    public InventoryPage(Page page) {
        this.page = page;
    }

    public boolean isOnInventoryPage() {
        return page.locator(title).innerText().equals("Products");
    }

    public void validateMenuItemList() {
        Locator menuItems = page.locator(menuItemEles);
        List<String> actualItems = menuItems.allTextContents();
        List<String> expectedItems = Arrays.asList("All Items", "About", "Logout", "Reset App State");
        Assert.assertTrue(actualItems.equals(expectedItems),
                "Menu item is not as per expectation. /nActual list : " + actualItems + "/nExpected List : " + expectedItems);
    }
}
