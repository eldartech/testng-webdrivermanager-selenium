package tests.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.com.sentrifugo.LogInPage;
import utils.Driver;

public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    @Parameters("browser")
    @BeforeTest
    void setUp(String browserName){
        driver = Driver.getDriver(browserName);
        wait = new WebDriverWait(driver,3);
    }
}
