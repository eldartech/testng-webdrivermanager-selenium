package tests.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.sentrifugo.HR_Page;
import pages.com.sentrifugo.LogInPage;
import pages.com.sentrifugo.OrganizationPage;
import pages.com.sentrifugo.WelcomePage;
import tests.com.sentrifugo.testData.UsersData;
import utils.Driver;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.JSUtils.jsScroll;

public class WelcomeTest {
    WebDriver driver;
    LogInPage logInPage;
    WelcomePage welcomePage;
    OrganizationPage organization;
    HR_Page hr_page;
    WebDriverWait webDriverWait;

    @BeforeMethod
    void setUp() {
        driver = Driver.getDriver("firefox");
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        organization = new OrganizationPage(driver);
        hr_page = new HR_Page(driver);
        webDriverWait = new WebDriverWait(driver,3);
        driver.navigate().to("http://demo.sentrifugo.com/index.php/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(description = "Welcome page - role based tabs test",dataProvider = "roleData",dataProviderClass = UsersData.class)
    void roleBasedWelcomePageTest(String username, String password,List expectedMenuOptions) {
        logInPage.logIn(username, password);
        Assert.assertEquals(welcomePage.getMenuOptions(), expectedMenuOptions);
    }

    @Test(description = "Welcome page - schedule date, account holder name test")
    void welcomePageTest() {
        logInPage.logIn("EM01", "sentrifugo");
        final List<String> expectedMenuOptions = Arrays.asList("Dashboard"
                , "Self Service"
                , "Service Request"
                , "HR"
                , "Appraisals"
                , "Recruitments"
                , "Background Check"
                , "Organization"
                , "Analytics"
                , "Site Config"
                , "Modules"
                , "Expenses"
                , "Assets"
                , "Disciplinary"
                , "Time");
        Assert.assertEquals(welcomePage.getMenuOptions(), expectedMenuOptions);
        Assert.assertTrue(welcomePage.getMemberName().isDisplayed());
        Assert.assertEquals(welcomePage.getMemberName().getText(), "Super Admin");
        Assert.assertEquals(welcomePage.getScheduleDate().getText().toUpperCase(), getExpectedDate());

    }

    String getExpectedDate() {
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();
        //Wed 14, Dec 2022
        return String.format("%s %d, %s %d", currentDate.getDayOfWeek().toString().substring(0, 3), currentDate.getDayOfMonth(), month.toString().substring(0, 3), year);
    }

    @Test(description = "Welcome page - account options test")
    void accountOptionsTest() {
        logInPage.logIn("EM01", "sentrifugo");
        welcomePage.clickProfName();
        final List<String> expectedAccountOptions = Arrays.asList("View Profile", "Settings", "Change Password", "Mail Settings", "Take Tour", "Upgrade Application", "Logout");
        Assert.assertEquals(welcomePage.getAccountOptions(), expectedAccountOptions);

    }

    @Test(description = "Welcome page - Box links test")
    void viewLinksTest(){
        logInPage.logIn("EM01", "sentrifugo");
        List<String> expectedUrls =Arrays.asList("employee","empleavesummary","requisition","ethniccode","approvedrequisitions","roles","timezone","employee","feedforwardquestions");
       List<WebElement> links = welcomePage.getViewLinks();
        for (int i=0; i<links.size();i++){
            jsScroll(driver,links.get(i));
            links.get(i).click();
            Assert.assertTrue(driver.getCurrentUrl().endsWith(expectedUrls.get(i)));
            driver.navigate().back();
        }
    }

    @Test(description = "Welcome page - Shortcut links test")
    void shortcutsTest(){
        logInPage.logIn("EM01", "sentrifugo");
        List<String> expectedUrls =Arrays.asList("departments","timezone","gender","countries","states","cities","remunerationbasis","language","competencylevel","numberformats","managemenus","appraisalinit","employee","appraisalself","appraisalmanager","myteamappraisal");
        List<WebElement> shortcuts = welcomePage.getShortcuts();
        for (int i=0; i<shortcuts.size();i++){
            shortcuts.get(i).click();
            Assert.assertTrue(driver.getCurrentUrl().endsWith(expectedUrls.get(i)));
            driver.navigate().back();
        }
    }

    @Test(description = "Welcome page - View Announcements test")
    void viewAnnouncementsTest(){
        logInPage.logIn("EM01", "sentrifugo");
        welcomePage.clickViewAnnouncements();

        Assert.assertEquals(organization.getAnnouncements(),Arrays.asList("Announcements","Announcements"));
    }

    @Test(description = "Welcome page - Total Employee count test")
    void employeeCountTest(){
        logInPage.logIn("EM01", "sentrifugo");
        int employeeCount = welcomePage.getTotalEmpCount();
        Assert.assertEquals(employeeCount,welcomePage.getActEmpCount()+ welcomePage.getInactEmpCount());
        welcomePage.getViewLinks().get(0).click();
        hr_page.clickViewMoreRemaining();
        webDriverWait.until(ExpectedConditions.invisibilityOf(hr_page.getViewMoreRemaining()));
        Assert.assertEquals(hr_page.getEmployeeList().size(),employeeCount);
    }

    @Test(description = "Welcome page - Employee Leaves Summary Test")
    void employeeLeavesSummaryCountTest(){
        logInPage.logIn("EM01", "sentrifugo");
        int employeeLeavesSummary = welcomePage.getEmployeeLeavesCount();
        welcomePage.getViewLinks().get(1).click();
        Assert.assertEquals(hr_page.getEmployeeSummaryRows().size(),employeeLeavesSummary);

    }

}
