package tests.com.sentrifugo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.com.sentrifugo.LogInPage;
import tests.com.sentrifugo.testData.UsersData;


public class LogInTest extends TestBase{
    public LogInPage logInPage;

    @BeforeMethod
    void setPages(){
        logInPage = new LogInPage(driver);
    }

    @Parameters({"username","password"})
    @Test()
    void happyPathLogInTest(String username,String password){
        driver.get("http://demo.sentrifugo.com/index.php/");
        logInPage.logIn(username,password);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("welcome"),"Log in functionality test failed");
        Reporter.log("Navigated to sentrifugo");
    }


    @Test(dataProvider = "activeUsers",dataProviderClass = UsersData.class)
    void activeUsersLogInTest(String username,String password){
        driver.get("http://demo.sentrifugo.com/index.php/");
        logInPage.logIn(username,password);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("welcome"),"Log in functionality test failed");
    }
}
