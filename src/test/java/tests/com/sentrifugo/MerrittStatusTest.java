package tests.com.sentrifugo;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.com.sentrifugo.LogInPage;
import pages.com.sentrifugo.MaritalStatusPage;
import pages.com.sentrifugo.WelcomePage;

import java.util.Arrays;
import java.util.List;

public class MerrittStatusTest extends TestBase {
    LogInPage logInPage;
    WelcomePage welcomePage;
    MaritalStatusPage merrittStatusPage;
    SoftAssert softAssert;

    @BeforeClass
    void setPage() {
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        merrittStatusPage = new MaritalStatusPage(driver);
        softAssert = new SoftAssert();
    }

    @Parameters({"username", "password"})
    @Test
    void maritalStatusPageTest(String username, String password) {
        driver.get("http://demo.sentrifugo.com/index.php/");
        logInPage.logIn(username, password);
        welcomePage.clickShortcuts("gender");
        final List<String> expectedOptions = Arrays.asList("Ethnic Codes", "Gender", "Time Zones", "Marital Status"
                , "Prefixes", "Race Codes", "Nationality Context Codes", "Nationalities", "Account Class Types"
                , "License Types", "Identity Codes", "Email Contacts", "Number Formats");
        softAssert.assertEquals(merrittStatusPage.getAllGeneralOptions(),expectedOptions);
        merrittStatusPage.clickGeneralOption("marital status");
        merrittStatusPage.clickAddButton();
        softAssert.assertEquals(merrittStatusPage.getAddMaritalStatusLabels(),Arrays.asList("Marital Code","Marital Status","Description"));
        merrittStatusPage.clickSaveButton();
        softAssert.assertEquals(merrittStatusPage.getErrorMessagesText(),Arrays.asList("Please enter marital code.","Please enter marital status."));
        wait.until(ExpectedConditions.visibilityOfAllElements(merrittStatusPage.getErrorMessages()));
        final String RED = "rgb(255, 0, 0)";
//        softAssert.assertTrue(merrittStatusPage.getErrorMessages().get(0).getCssValue("color").equals(RED));
//        softAssert.assertTrue(merrittStatusPage.getErrorMessages().get(1).getCssValue("color").equals(RED));
        softAssert.assertTrue(merrittStatusPage.getErrorMessages().stream().allMatch(e->e.getCssValue("color").equals(RED)));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "maritalStatusPageTest", dataProvider = "maritalStatusData")
    void validateAddingMaritalStatus(String code, String status, String description){
        merrittStatusPage.setMaritalCode(code);
        merrittStatusPage.setMaritalStatusName(status);
        merrittStatusPage.setDescription(description);
        merrittStatusPage.clickSaveButton();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(merrittStatusPage.getSuccessMessage())).isDisplayed());
        Assert.assertEquals(merrittStatusPage.getSuccessMessage().getText(),"Marital status added successfully.");
        merrittStatusPage.clickAddButton();
    }

    @DataProvider(name = "maritalStatusData")
    public Object[][] getData(){
        return new Object[][]{
                {"SS","SSingle","SingleDescription"},
                {"DD","DDivorced","DivorcedDescription"},
                {"WW","WWidow","WidowDescription"}
        };
    }





}
