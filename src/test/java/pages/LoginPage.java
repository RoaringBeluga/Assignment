package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WebPage {
    private final By loginField = By.id("username");
    private final By passwordField = By.id("password");
    private final By usernameError = By.id("usernameError");
    private final By passwordError = By.id("passwordError");
    private final By loginError = By.id("loginError");
    private final By clearButton = By.xpath("//button[text()='Clear'");
    private final By loginButton = By.xpath("//button[text()='Login']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillUsername(String username) {
        var login = find(loginField);
        login.clear();
        login.sendKeys(username);

        return this;
    }

    public boolean usernameErrorVisible() {
        return find(loginField).isDisplayed();
    }

    public LoginPage fillPassword(String password) {
        var passInput = find(passwordField);
        passInput.clear();
        passInput.sendKeys(password);

        return this;
    }

    public boolean passwordErrorVisible() {
        return find(passwordError).isDisplayed();
    }

    public boolean loginErrorVisible() {
        return find(loginError).isDisplayed();
    }

    public LoginPage clearForm() {
        find(clearButton).click();
        return this;
    }

    public LoginPage submitForm() {
        find(loginButton).click();
        return this;
    }

}
