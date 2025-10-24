package stepDefinitions;

import pages.InventoryPage;
import hooks.Hooks;
import pages.LoginPage;
import pages.InventoryPage;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class InventorySteps {
    InventoryPage inventoryPage;

    @And("Validate menu items")
    public void validateMenuItems() {
        inventoryPage = new InventoryPage(Hooks.page);
        inventoryPage.validateMenuItemList();
    }
}
