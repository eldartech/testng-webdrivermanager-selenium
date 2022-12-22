package utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ElementUtils {

    public static List<String> getAllText(List<WebElement> elements){
        List<String> result = new ArrayList<>();
        for (WebElement element:elements){
            result.add(element.getText());
        }
        return result;
    }
}
