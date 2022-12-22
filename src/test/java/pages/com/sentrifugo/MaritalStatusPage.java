package pages.com.sentrifugo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class MaritalStatusPage {
    public MaritalStatusPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//b[text()='General']/parent::span/following-sibling::ul/li/a")
    private List<WebElement> generalOptions;

    @FindBy(css = "input[class='sprite addrecord']")
    private WebElement addButton;

    @FindBy(xpath = "//div[@class='total-form-controller']//label")
    private List<WebElement> addMaritalStatusLabels;

    @FindBy(id = "submitbutton")
    private WebElement saveButton;

    @FindBy(xpath = "//span[contains(@id,'errors-marital')]")
    private List<WebElement> errorMessages;

    @FindBy(id = "maritalcode")
    private WebElement maritalCodeInput;

    @FindBy(id = "maritalstatusname")
    private WebElement maritalStatusNameInput;

    @FindBy(id = "description")
    private WebElement descriptionTextArea;

    @FindBy(xpath = "//div[@id='messageData' and contains(@class,'success')]/div")
    private WebElement successMessage;

    public List<String> getAllGeneralOptions(){
        return ElementUtils.getAllText(generalOptions);
    }



    public void clickGeneralOption(String option){
        for (WebElement generalOption:generalOptions){
            if (generalOption.getText().equalsIgnoreCase(option)){
                generalOption.click();
                break;
            }
        }
    }

    public void clickAddButton(){
        addButton.click();
    }

    public List<String> getAddMaritalStatusLabels(){
        return ElementUtils.getAllText(addMaritalStatusLabels);
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public List<String> getErrorMessagesText(){
        return ElementUtils.getAllText(errorMessages);
    }

    public List<WebElement> getErrorMessages(){
        return errorMessages;
    }

    public void setMaritalCode(String maritalCode){
        maritalCodeInput.sendKeys(maritalCode);
    }

    public void setMaritalStatusName(String maritalStatusName){
        maritalStatusNameInput.sendKeys(maritalStatusName);
    }

    public void setDescription(String description){
        descriptionTextArea.sendKeys(description);
    }

    public WebElement getSuccessMessage(){
        return successMessage;
    }
}
