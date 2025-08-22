package org.autoenterprise.plugin.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.autoenterprise.core.BasePage;
import org.autoenterprise.core.driver.DriverFactory;
import org.autoenterprise.core.manager.PageManager;
import org.autoenterprise.core.utils.ConfigReader;
import org.autoenterprise.core.utils.LoggerUtils;
import org.autoenterprise.plugin.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

import java.util.Set;

public class StepDefinitions {

    WebDriver driver;
    PageManager pageManager;
    BasePage currentPage;
    private static final Logger log = LoggerUtils.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        this.driver = DriverFactory.getDriver();
        this.pageManager = new PageManager(driver);

        // Scan for all classes extending BasePage in your Pages package
        Reflections reflections = new Reflections("org.autoenterprise.plugin.Pages");
        Set<Class<? extends BasePage>> pageClasses = reflections.getSubTypesOf(BasePage.class);

        for (Class<? extends BasePage> pageClass : pageClasses) {
            String pageName = pageClass.getSimpleName().toLowerCase(); // e.g., "loginpage"
            pageManager.registerPage(pageName, drv -> {
                try {
                    return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(drv);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate " + pageClass, e);
                }
            });
        }

    }

    @Given("I am on the {string}")
    public void i_am_on_the_page(String page) {
        currentPage = pageManager.getPage(page);
        driver.get(ConfigReader.getProperty("url"));
        log.info("User is on :"+page);

    }

    @When("I enter username {string} and password {string} on {string} page")
    public void i_enter_username_and_password_on_page(String username, String password, String page) {
        if (currentPage instanceof LoginPage) {
            LoginPage loginPage = (LoginPage) currentPage;
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
        } else {
            throw new RuntimeException("This step is not implemented for page: " + page);
        }
    }

    @When("I click {string} on {string} page")
    public void i_click_on_page(String elementName, String page) {
        if (currentPage instanceof LoginPage) {
            LoginPage loginPage = (LoginPage) currentPage;
            switch (elementName.toLowerCase()) {
                case "loginbutton":
                    loginPage.clickLogin();
                    break;
                default:
                    throw new RuntimeException("Element not found: " + elementName);
            }
        } else {
            throw new RuntimeException("This step is not implemented for page: " + page);
        }
    }

    @And("I debug")
    public void iDebug() throws InterruptedException {
        Thread.sleep(0);
    }
}
