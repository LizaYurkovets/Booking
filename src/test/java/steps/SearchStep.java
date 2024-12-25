package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class SearchStep extends BaseTest {

    WebDriver driver;

    @Given("booking search page is opened")
    public void bookingSearchPageIsOpened() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @When("user searches for {string}")
    public void userSearchesFor(String hotel) throws InterruptedException {
        sleep(2000);
        driver.findElement(By.xpath("//input[@name ='ss']")).sendKeys(hotel);
        sleep(2000);
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    @Then("{string} hotel is shown")
    public void hotelIsShown(String expectedResult) {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@data-testid='title']"));
        boolean isHotelFound = false;
        for(WebElement title :titles) {
            if (title.getText().equals(expectedResult)) {
                isHotelFound = true;
                break;
            }
        }
        Assert.assertTrue(isHotelFound);
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
