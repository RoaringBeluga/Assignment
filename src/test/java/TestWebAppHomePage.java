import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class TestWebAppHomePage {
    // Test data - in actual framework should NOT be provided as class properties.
    final String correctUsername = "admin";
    final String correctPassword = "p@$$w0rd";
    final String alertText = "Please fill out all fields";
    final String wrongDate = "02311970";
    final String correctDate = "02011970";
    final String favoriteTeam = "San Francisco Giants";
    final String playerName = "George Herman \"Babe\" Ruth";

    private boolean login(WebDriver driver) {
        return new LoginPage(driver)
                .login(correctUsername, correctPassword)
                .loginIsSuccessfull();
    }

    /**
     * Home Page test case 1.
     * Verify empty date causes an error message.
     */
    @Test(
            description = "Error on empty date",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testEmptyDate(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .submitForm()
                .alertVisible(alertText)
        )
                .withFailMessage("'Please fill out all fields' alert is not displayed")
                .isTrue();
        page.dismissAlert();
    }

    /**
     * Home Page test case 3.
     * Verify manual date input is validated.
     */
    @Test(
            description = "Invalid date should be rejected",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testInvalidDateRejection(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .fillDate(wrongDate)
                .dateErrorVisible()
        ).isTrue();
    }

    /**
     * Home Page test case 4.
     * Verify date can be entered manually.
     */
    @Test(
            description = "Verify date can be entered manually",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testCorrectDateManualInput(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .teamNameIsActive()
        ).isTrue();
    }

    /**
     * Home Page test case 5.
     * Verify empty team name is not allowed.
     */
    @Test(
            description = "Empty team name is not allowed",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testEmptyTeamNameError(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .submitForm()
                .alertVisible(alertText)
        ).withFailMessage("No alert displayed").isTrue();
    }

    /**
     * Home Page test case 6.
     * Verify favorite team can be selected when valid date is provided.
     */
    @Test(
            description = "Verify favorite team field activation",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testTeamNameActivation(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .teamNameListNotEmpty()
        ).isTrue();
        assertThat(page
                .fillTeamName(favoriteTeam)
                .teamIsSelected(favoriteTeam)
        ).isTrue();
    }

    /**
     * Home Page test case 7.
     * Verify player name field activates on team selection.
     */
    @Test(
            description = "Player name activates on team selection",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testPlayerNameActiveOnTeamSelection(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .fillTeamName(favoriteTeam)
                .playerNameIsActive()
        ).isTrue();
    }

    /**
     * Home Page test case 8.
     * Verify player name can not be empty.
     */
    @Test(
            description = "Verify player name can not be empty",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testNoEmptyPlayerName(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .fillTeamName(favoriteTeam)
                .waitForPlayerName()
                .submitForm()
                .alertVisible(alertText)
        ).isTrue();
    }

    /**
     * Home Page test case 9.
     * Verify the form can be submitted.
     */
    @Test(
            description = "Verify the form can be submitted",
            dataProvider = "driversInArray", dataProviderClass = DriverProvider.class,
            groups = {"Homepage", "Full"},
            enabled = true
    )
    public void testFormSubmission(WebDriver driver) {
        assertThat(login(driver)).withFailMessage("Login failed").isTrue();
        var page = new HomePage(driver);
        assertThat(page
                .fillDate(correctDate)
                .waitForTeamNameActivation()
                .fillTeamName(favoriteTeam)
                .waitForPlayerName()
                .fillPlayerName(playerName)
                .submitForm()
                .formSubmissionIsSuccessful()
        ).isTrue();
    }


}
