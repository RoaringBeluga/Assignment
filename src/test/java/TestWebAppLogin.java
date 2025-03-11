import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class TestWebAppLogin {
    // Test data - in actual framework should NOT be provided as class properties.
    final String wrongUsername = "wrong_user";
    final String wrongPassword = "wrong_password";
    final String correctUsername = "admin";
    final String correctPassword = "p@$$w0rd";

    /**
     * Login Page test case 1.
     * Empty credentials should cause error message.
     */
    @Test(
            description = "No credentials provided",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testNoCredentials(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                        .submitForm()
                        .loginErrorVisible()
        )
                .withFailMessage("login error should be displayed")
                .isTrue();
    }

    /**
     * Login Page test case 2.
     * Wrong username should cause error message.
     */
    @Test(
            description = "Wrong username, no password",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testWrongUsernameNoPassword(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                .fillUsername(wrongUsername)
                .submitForm()
                .usernameErrorVisible()
        ).isTrue();
    }
    /**
     * Login Page test case 3.
     * Wrong password with correct username should cause error message.
     */
    @Test(
            description = "Correct username, wrong password",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testCorrectUsernameWrongPassword(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                .fillUsername(correctUsername)
                .fillPassword(wrongPassword)
                .submitForm()
                .passwordErrorVisible()
        ).isTrue();
    }

    /**
     * Login Page test case 4.
     * Clear button clears the form.
     */
    @Test(
            description = "Correct username, wrong password",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testFormIsCleared(WebDriver driver) {
        var page = new LoginPage(driver);
        page
                .fillUsername(correctUsername)
                .fillPassword(correctPassword)
                .clearForm();
        assertThat(page
                .loginIsEmpty()
        ).isTrue();
        assertThat(page
                .passwordIsEmpty()
        ).isTrue();
    }
    /**
     * Login Page test case 5.
     * Blank password not allowed.
     */
    @Test(
            description = "Blank password not allowed",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testNoBlankPassword(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                .fillUsername(correctUsername)
                .submitForm()
                .passwordErrorVisible()
        ).isTrue();
    }

    /**
     * Login Page test case 6.
     * Blank username is not allowed.
     */
    @Test(
            description = "Blank username not allowed",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testNoBlankUsername(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                .fillPassword(correctPassword)
                .submitForm()
                .usernameErrorVisible()
        ).isTrue();
    }
    /**
     * Login Page test case 7.
     * Valid credentials should allow logging in.
     */
    @Test(
            description = "Valid credentials should allow logging in",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Login", "Full"},
            enabled = true
    )
    public void testCanLogIn(WebDriver driver) {
        var page = new LoginPage(driver);
        assertThat(page
                .fillUsername(correctUsername)
                .fillPassword(correctPassword)
                .submitForm()
                .loginIsSuccessfull()
        ).isTrue();
    }

}
