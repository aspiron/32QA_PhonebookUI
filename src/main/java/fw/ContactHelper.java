package fw;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver dr) {
        super(dr);
    }

    public void addContact(Contact contact) {
        type(By.cssSelector("input:nth-child(1)"), contact.name());
        type(By.cssSelector("input:nth-child(2)"), contact.surname());
        type(By.cssSelector("input:nth-child(3)"), contact.telephone());
        type(By.cssSelector("input:nth-child(4)"), contact.email());
        type(By.cssSelector("input:nth-child(5)"), contact.address());
        type(By.cssSelector("input:nth-child(6)"), contact.description());
        //click on button save
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void removeContact() {
        if (!isCOntactListEmpty()) {
            //click on the contact card
            click(By.cssSelector(".contact-item_card__2SOIM"));
            //click on the remove button
            click(By.xpath("//button[text()='Remove']"));
        }
    }

    public int sizeOfContacts() {
        if (dr.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            return dr.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } else {
            return 0;
        }
    }

    public boolean isCOntactListEmpty() {
        return dr.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public void addRandomContact(int i) {
        addContact(new Contact()
                .setName("Ivan" + i)
                .setSurname("Ivanov")
                .setTelephone("123456789" + i)
                .setEmail("ivan" + i + "@mail.com")
                .setAddress("adress str.1, adressCity")
                .setDescription("some description about this contact"));
    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = dr.findElements(By.xpath("//h2"));
        for (WebElement el : contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }
}
