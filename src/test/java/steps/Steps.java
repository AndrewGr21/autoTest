package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.Browser;


public class Steps extends Browser {

    @Given("user opens {string} browser")
    public void user_opens_browser(String driver) {
        Browser.driver = initDriver(driver);
    }

    @And("user navigate to {string} url")
    public void user_navigate_to_url(String url) {
        Browser.driver.get(url);
    }

    @When("user opens transports")
    public void userOpensTransports() {
        driver.findElement(By.xpath("//li[@class='   ']/a")).click();
    }

    @And("user opens category transports")
    public void userOpensCategoryTransports() {
        driver.findElement(By.xpath("//li[@class='category__subCategories-collection ']/a")).click();
    }

    @And("user scroll to checkbox Nissan")
    public void userScrollToCheckboxNissan() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 500)", "");
    }

    @And("user click checkbox")
    public void uderClickCeckbox() {
        verificationCheck("option-28");
        selectCheck("option-28");
        verificationCheck("option-28");
    }

    @Then("user save table in Excel document")
    public void userSaveTableInExcellDocument() {
        createExcel();
    }

    @After
    public void afterScenario() throws InterruptedException {
        Thread.sleep(1000);
        closeBrowser();
    }

}
