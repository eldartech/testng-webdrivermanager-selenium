package pages.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class WelcomePage {
    public WelcomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@id='main_ul']/li/a")
    private List<WebElement> menuOptions;

    @FindBy(css = "div[class='interview_shed_box'] span")
    private WebElement scheduleDate;

    @FindBy(className = "member_name")
    private WebElement memberName;

    @FindBy(id = "prof_name")
    private WebElement prof_name;

    @FindBy(xpath = "//div[@class='logoutdiv']/*[not(@class='arrow-div')]")
    private List<WebElement> accountOptions;

    @FindBy(xpath = "//div[@class='left_dashboard']//h4[not(@span)]")
    private List<WebElement> dashboardNames;

    @FindBy(xpath = "//div[@class='left_dashboard']//a[contains(text(), 'View')]")
    private List<WebElement> viewLinks;

    @FindBy(css = "div[class='short-text-ctrl'] a")
    private List<WebElement> shortcuts;

    @FindBy(className = "view_more_annou")
    private WebElement viewAnnouncements;

    @FindBy(css = "div[class*='colour_1'] div[class='box_count_tol emp_total']")
    private WebElement totalEmpCount;

    @FindBy(xpath = "//div[text()='Active']/span[@class='box_count']")
    private WebElement activeEmpCount;

    @FindBy(xpath = "//div[text()='Inactive']/span[@class='box_count']")
    private WebElement inactiveEmpCount;

    @FindBy(css = "div[class*='colour_2'] span")
    private WebElement employeeLeavesCount;


    public List<String> getMenuOptions(){
        return ElementUtils.getAllText(menuOptions);
    }

    public WebElement getMemberName(){
        return memberName;
    }

    public WebElement getScheduleDate(){
        return scheduleDate;
    }

    public void clickProfName(){
        prof_name.click();
    }

    public List<String> getAccountOptions(){
        return ElementUtils.getAllText(accountOptions);
    }

    public void selectAccountOption(String option){
//        for (WebElement element:accountOptions){
//            if (element.getText().equalsIgnoreCase(option)){
//                element.click();
//            }
//        }
        accountOptions.stream().filter(e->e.getText().equalsIgnoreCase(option)).forEach(WebElement::click);
    }

    public List<WebElement> getViewLinks(){
        return viewLinks;
    }

    public List<WebElement> getShortcuts(){
        return shortcuts;
    }

    public void clickShortcuts(String title){
        for (WebElement shortcut: shortcuts){
            if (shortcut.getAttribute("title").equalsIgnoreCase(title)){
                shortcut.click();
                break;
            }
        }
    }

    public void clickViewAnnouncements(){
        viewAnnouncements.click();
    }

    public int getTotalEmpCount(){
        return Integer.parseInt(totalEmpCount.getText());
    }

    public int getActEmpCount(){
        return Integer.parseInt(activeEmpCount.getText());
    }

    public int getInactEmpCount(){
        return Integer.parseInt(inactiveEmpCount.getText());
    }

    public int getEmployeeLeavesCount(){
        return Integer.parseInt(employeeLeavesCount.getText().trim());
    }
}
