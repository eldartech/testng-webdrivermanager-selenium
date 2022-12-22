package pages.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.JSUtils.jsScroll;

public class HR_Page {
    WebDriver driver;
    public HR_Page(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="viewmorediv")
    private WebElement viewMoreRemaining;

    @FindBy(className = "list-item")
    private List<WebElement> employeeList;

    @FindBy(xpath = "//div[@class='slimScrollDiv']//tbody/tr")
    private List<WebElement> employeeSummaryRows;

    public void clickViewMoreRemaining(){
        jsScroll(driver, viewMoreRemaining);
        viewMoreRemaining.click();
    }

    public WebElement getViewMoreRemaining(){
        return viewMoreRemaining;
    }

    public  List<WebElement> getEmployeeList(){
        return employeeList;
    }

    public List<WebElement> getEmployeeSummaryRows(){
        return employeeSummaryRows;
    }
}
