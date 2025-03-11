import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.DataProvider;

public class DriverProvider {
    @DataProvider(name = "driverProviderDocker", parallel = true)
    public WebDriver[] driversInDocker() {
        return new WebDriver[] {
                WebDriverManager.firefoxdriver().browserInDocker().create(),
                WebDriverManager.chromedriver().browserInDocker().create(),
                WebDriverManager.edgedriver().browserInDocker().create()
        };
    }

    @DataProvider(name = "driverProvider", parallel = true)
    public WebDriver[] driversInArray() {
        var s = "";
        return new WebDriver[] {
                WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create(),
                //WebDriverManager.chromedriver().capabilities(new ChromeOptions().addArguments("--headless")).create(),
                //WebDriverManager.edgedriver().capabilities(new EdgeOptions().addArguments("--headless")).create()
        };
    }

}
