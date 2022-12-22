package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

public class Driver {
    //     1. private static  instance variable of WebDriver
    private static WebDriver driver;

    //     2. you need to create private  constructor
    private Driver() {
    }

    //    3. public method to access the instance variable.
//    we need to put one condition that will check our driver
//    is instantiated or not. if it is already instantiated we
//    will not instantiate one more time.
    public static WebDriver getDriver(String driverName) {
        if (driver == null) {
            switch (driverName.toLowerCase()) {
                case "chrome":
                   WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions  = new ChromeOptions();
                    chromeOptions.setHeadless(true);
                   // chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chrome-incognito":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeIncognito  = new ChromeOptions();
                    chromeIncognito.addArguments("--incognito");
                    driver = new ChromeDriver(chromeIncognito);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
//                    firefoxOptions.setHeadless(true);
                    firefoxOptions.addArguments("-headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "firefox-private":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxPrivate = new FirefoxOptions();
                    firefoxPrivate.addArguments("-private");
                    driver = new FirefoxDriver(firefoxPrivate);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;

                default: throw new IllegalArgumentException(driverName);
            }
        }
        Reporter.log(driverName.toUpperCase()+" launched.");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }
}
