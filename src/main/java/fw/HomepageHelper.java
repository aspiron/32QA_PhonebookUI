package fw;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomepageHelper extends HelperBase{
    public HomepageHelper(WebDriver dr) {
        super(dr);
    }

    public boolean isHomeComponentPresent() {
        return isElementPresent(By.cssSelector("div:nth-child(2) > div > div"));
    }

    public boolean isHomeComponentPresent2() {
        try {
            isElementPresent2(By.xpath("//div[@id='root']/div[2]/div/div"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
