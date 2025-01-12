package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.SearchPage;

import java.time.Duration;


public class SearchStep {

    public SearchPage searchPage;
    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        searchPage = new SearchPage();
    }

    @Given("booking search page is opened")
    public void bookingSearchPageIsOpened() {
        searchPage.open();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String hotel) throws InterruptedException {
        searchPage.search("Viking Express Hotel");
    }

    @Then("{string} hotel is shown")
    public void hotelIsShown(String expectedResult) throws InterruptedException {
        searchPage.isHotelShown("Viking Express Hotel");
    }

    @And("hotel has rating {string}")
    public void hotelHasRating(String actualResult) {
        actualResult = searchPage.getHotelRating("Viking Express Hotel");
        Assert.assertEquals(actualResult, "8,4");
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }


}
