package pages;

import com.microsoft.playwright.Page;

public class InventoryPage {
    private Page page;
    private String title = ".title";

    public InventoryPage(Page page) {
        this.page = page;
    }

    public boolean isOnInventoryPage() {
        return page.locator(title).innerText().equals("Products");
    }
}
