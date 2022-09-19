package fw;

import com.google.common.io.Files;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Recorder;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

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

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshot.getAbsolutePath();
    }

    private ScreenRecorder screenRecorder;

    public void startRecording() throws IOException, AWTException {

        File file = new File("records");

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screensize.getWidth();
        double height = screensize.getHeight();
        Rectangle captureSize = new Rectangle(0, 0, (int) width, (int) height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        screenRecorder = new Recorder(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null,
                file, "myVideo");

        screenRecorder.start();
    }

    public void stopRecording() throws IOException {

        screenRecorder.stop();

    }

    public void deleteRecords() {
        File directory = new File("records");
        File[] files = directory.listFiles();
        for (File f: files) {
            f.delete()
        }
    }

}
