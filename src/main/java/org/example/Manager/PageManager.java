package org.example.Manager;

import org.example.Base.BasePage;
import org.example.Pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageManager {
    WebDriver driver;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage getPage(String pageName) {
        switch (pageName.toLowerCase()) {
            case "login":
                return new LoginPage(driver);
            // add more cases for other pages later
            default:
                throw new RuntimeException("Page not found: " + pageName);
        }
    }
}
