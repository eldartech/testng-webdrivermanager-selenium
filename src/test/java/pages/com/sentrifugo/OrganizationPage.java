package pages.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class OrganizationPage {
    public OrganizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy(xpath = "//div[contains(@class,'table-header')]"),
            @FindBy(xpath = "//div[contains(@class,'breadcrumbs')]/a")
    })
    private List<WebElement> announcements;


    public List<String> getAnnouncements(){
        return ElementUtils.getAllText(announcements);
    }
}
