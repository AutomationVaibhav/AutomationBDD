package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.Base.BasePage;
import org.example.Manager.DriverFactory;
import org.example.Manager.PageManager;
import org.example.Pages.LoginPage;
import org.example.Utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {

    WebDriver driver;
    PageManager pageManager;
    BasePage currentPage;

    public StepDefinitions() {
        this.driver = DriverFactory.getDriver();
        this.pageManager = new PageManager(driver);
    }

    @Given("I am on the {string}")
    public void i_am_on_the_page(String page) {
        currentPage = pageManager.getPage(page);
        driver.get(ConfigReader.getProperty("url"));
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
