package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Constants;

public class WebPage {
    WebDriver driver;
    WebDriverWait wait;

    WebPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Constants.waitTimeout);
    }

    public WebElement find(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

}
