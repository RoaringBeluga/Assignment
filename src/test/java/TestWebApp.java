import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class TestWebApp {
    private WebDriver driver;
    // Test data - in actual framework should NOT be provided as class properties.
    final String wrongUsername = "wrong_user";
    final String wrongPassword = "wrong_password";
    final String correctUsername = "admin";
    final String correctPassword = "p@$$w0rd";
    final String wrongDate = "23-23-2323";
    final String correctedDate = "";

    @BeforeSuite(alwaysRun = true)
    public void setupTests() {
        this.driver = WebDriverManager.firefoxdriver().create();
        this.driver.get("./assignment.html");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        this.driver.close();
    }

    /**
     * Login Page test case 1.
     * Empty credentials should cause error message.
     */
    @Test(groups = {"Login", "Full"}, testName = "No credentials provided")
    public void testNoCredentials() {
        var page = new LoginPage(this.driver);
        assertThat(
                page
                        .submitForm()
                        .loginErrorVisible()
        );
    }

    /**
     * Login Page test case 2.
     * Wrong username should cause error message.
     */
    @Test(groups = {"Login", "Full"}, testName = "Wrong username provided")
    public void testWrongUsername() {

    }

}
