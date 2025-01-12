package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchPage {

    private final By SEARCH_INPUT = By.xpath("//input[@name ='ss']");
    private final By SUBMIT_BUTTON = By.cssSelector("button[type=submit]");
    private final By TITLE = By.xpath("//*[@data-testid='title']");
    private final By HOTEL_RATING = By.xpath("//*[@data-testid='review-score']/div/div");
    private final By HOTEL_CARD = By.xpath("ancestor::div[@data-testid='property-card']");

    WebDriver driver;

    public void open() {
        driver = new ChromeDriver();
        driver.get("https://www.booking.com/searchresults.en-gb.html");
    }

    public void search(String hotel) throws InterruptedException {
        sleep(2000);
        driver.findElement(SEARCH_INPUT).sendKeys(hotel);
        sleep(2000);
        driver.findElement(SUBMIT_BUTTON).click();
    }

    public void isHotelShown(String expectedResult) throws InterruptedException {
        sleep(2000);
        List<WebElement> titles = driver.findElements(TITLE);
        sleep(2000);
        boolean isHotelFound = false;
        for (WebElement title : titles) {
            if (title.getText().equals(expectedResult)) {
                isHotelFound = true;
                break;
            }
        }
        Assert.assertTrue(isHotelFound);
    }

    public String getHotelRating(String hotelName) {
        var hotelCard = getHotelCard(hotelName);
        var hotelRating = hotelCard.findElement(HOTEL_RATING);
        var hotelRatingText = hotelRating.getText();
        String [] textParts = hotelRatingText.split(" ");
        return textParts[textParts.length - 1];
    }

    private WebElement getHotelCard(String hotelName) {
        var title = driver.findElement(By.xpath(String.format("//*[@data-testid='title'][text()='%s']", hotelName)));
        return title.findElement(HOTEL_CARD);
    }

}
