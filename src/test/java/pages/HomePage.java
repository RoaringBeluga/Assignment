package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class HomePage extends WebPage {
    private final By dateField = By.id("date");
    private final By favoriteTeam = By.id("team");
    private final By playerName = By.id("name");
    private final By selectDateError = By.id("teamError");
    private final String dateErrorMessage = "Please select a date first";
    private final By submitButton = By.id("submitButton");

    private final By finalPage = By.id("thankYouContainer");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage fillDate(String aDate) {
        var date = find(dateField);
        date.clear();
        // date.sendKeys(aDate) doesn't work on this field
//        var executor = (JavascriptExecutor) driver;
//        executor.executeScript(
//                "arguments[0].value = arguments[1]; arguments[0].onchange();",
//                date, aDate
//        );
        new Actions(driver)
                .sendKeys(date, aDate)
                .sendKeys(Keys.ENTER)
                .perform();

        return this;
    }

    public HomePage fillTeamName(String myTeam) {
        var teamName = find(favoriteTeam);
        Select teamSelect = new Select(teamName);
        teamSelect.selectByVisibleText(myTeam);

        return this;
    }

    public HomePage fillPlayerName(String player) {
        var name = find(playerName);
        name.clear();
        name.sendKeys(player);

        return this;
    }

    public HomePage submitForm() {
        find(submitButton).click();
        return this;
    }

    public boolean alertVisible(String expected) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            return text.equalsIgnoreCase(expected);
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public HomePage dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (TimeoutException ignored) {}
        return this;
    }

    public boolean dateErrorVisible() {
        // Using exceptions this way is not a good idea.
        // In this case, we do it to capture test failure
        // as opposed to genuine exception.
        try {
            wait.until(ExpectedConditions.textToBe(selectDateError, dateErrorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean teamNameIsActive() {
        return find(favoriteTeam).isEnabled();
    }

    public boolean teamNameListNotEmpty() {
        var teamName = find(favoriteTeam);
        Select teamSelect = new Select(teamName);

        return !teamSelect.getOptions().isEmpty();
    }

    public HomePage waitForTeamNameActivation() {
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTeam));
        return this;
    }

    public boolean teamIsSelected(String myTeam) {
        var teamName = find(favoriteTeam);
        Select teamSelect = new Select(teamName);
        return teamSelect.getFirstSelectedOption().getText().equalsIgnoreCase(myTeam);
    }

    public boolean playerNameIsActive() {
        return find(playerName).isEnabled();
    }

    public HomePage waitForPlayerName() {
        wait.until(ExpectedConditions.visibilityOf(find(playerName)));
        return this;
    }

    public boolean formSubmissionIsSuccessful() {
        try {
            return find(finalPage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
