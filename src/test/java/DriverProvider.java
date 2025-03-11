import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.DataProvider;

public class DriverProvider {
    @DataProvider(name = "driversInDocker", parallel = true)
    public WebDriver[] driversInDocker() {
        return new WebDriver[] {
                WebDriverManager.firefoxdriver().browserInDocker().create(),
                WebDriverManager.chromedriver().browserInDocker().create(),
                WebDriverManager.edgedriver().browserInDocker().create()
        };
    }

    @DataProvider(name = "driversInArray", parallel = true)
    public WebDriver[] driversInArray() {
        return new WebDriver[] {
                //WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create(),
                //WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create(),
                WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create()
        };
    }

}
