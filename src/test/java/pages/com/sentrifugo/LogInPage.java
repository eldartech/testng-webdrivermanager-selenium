package pages.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    public LogInPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID,using = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "input#loginsubmit")
    private WebElement loginSubmit;

    public void logIn(String userName, String password){
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        this.loginSubmit.click();
    }


}
