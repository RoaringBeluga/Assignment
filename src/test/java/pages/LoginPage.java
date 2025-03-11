package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WebPage {
    private final By loginField = By.id("username");
    private final By passwordField = By.id("password");
    private final By usernameError = By.id("usernameError");
    private final By passwordError = By.id("passwordError");
    private final By loginError = By.id("loginError");
    private final By clearButton = By.xpath("//button[text()='Clear']");
    private final By loginButton = By.xpath("//button[text()='Login']");
    private final By loginForm = By.id("loginContainer");
    private final By homePage = By.id("homeContainer");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(Constants.pageUrl);
    }

    public LoginPage fillUsername(String username) {
        var login = find(loginField);
        login.clear();
        login.sendKeys(username);

        return this;
    }

    public LoginPage fillPassword(String password) {
        var passInput = find(passwordField);
        passInput.clear();
        passInput.sendKeys(password);

        return this;
    }

    public LoginPage clearLogin() {
        find(loginField).clear();
        return this;
    }

    public LoginPage clearPassword() {
        find(passwordField).clear();
        return this;
    }

    public boolean passwordErrorVisible() {
        return driver.findElement(passwordError).isDisplayed();
    }

    public boolean usernameErrorVisible() {
        return driver.findElement(usernameError).isDisplayed();
    }

    public boolean loginErrorVisible() {
        return driver.findElement(loginError).isDisplayed();
    }

    public boolean loginIsEmpty() {
        return find(loginField).getText().isBlank();
    }

    public boolean passwordIsEmpty() {
        return find(passwordField).getText().isBlank();
    }

    public LoginPage clearForm() {
        find(clearButton).click();
        return this;
    }

    public LoginPage submitForm() {
        find(loginButton).click();
        return this;
    }

    public LoginPage login(String username, String password) {
        this
                .fillPassword(password)
                .fillUsername(username)
                .submitForm();
        return this;
    }

    public boolean loginIsSuccessfull() {
        return !driver.findElement(loginForm).isDisplayed() && driver.findElement(homePage).isDisplayed();
    }
}
