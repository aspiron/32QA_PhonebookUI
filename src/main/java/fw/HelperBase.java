package fw;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    WebDriver dr;

    public HelperBase(WebDriver dr) {
        this.dr = dr;
    }

    public boolean isElementPresent(By locator) {
        return dr.findElements(locator).size() > 0;
    }

    public boolean isElementPresent2(By locatorXPath) {
        try {
            dr.findElement(locatorXPath);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            dr.findElement(locator).clear();
            dr.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator) {
        dr.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWithAction(By locator) {
        Actions actions = new Actions(dr);
        WebElement element = dr.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(dr, 20).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            dr.switchTo().alert();
            alert.accept();
            return true;
        }
    }
}
