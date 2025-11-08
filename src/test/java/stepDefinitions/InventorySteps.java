package stepDefinitions;

import hooks.DriverFactory;
import pages.InventoryPage;
import io.cucumber.java.en.*;

public class InventorySteps {
    InventoryPage inventoryPage;

    @And("Validate menu items")
    public void validateMenuItems() {
        inventoryPage = new InventoryPage(DriverFactory.getPage());
        inventoryPage.validateMenuItemList();
    }
}
