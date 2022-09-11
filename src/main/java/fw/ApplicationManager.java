package fw;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver dr;
    UserHelper user;
    ContactHelper contact;
    HeaderHelper header;
    HomepageHelper homepage;

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public HomepageHelper getHomepage() {
        return homepage;
    }

    public void init() {
        dr = new ChromeDriver();
        dr.get("https://contacts-app.tobbymarshall815.vercel.app/");
        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        user = new UserHelper(dr);
        contact = new ContactHelper(dr);
        header = new HeaderHelper(dr);
        homepage = new HomepageHelper(dr);
    }

    public void stop() {
        dr.close();
    }

}
