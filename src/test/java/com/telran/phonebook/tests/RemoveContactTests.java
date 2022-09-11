package com.telran.phonebook.tests;

import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getHeader().isSignOutButtonPresent()) {
            app.getHeader().clickOnLoginLink();
            app.getUser().login();
            app.getHeader().clickOnAddLink();
            app.getContact().addContact(new Contact()
                            .setName("Ivan")
                            .setSurname("Ivanov")
                            .setTelephone("123456789")
                            .setEmail("ivan@mail.com")
                            .setAddress("adress str.1, adressCity")
                            .setDescription("some description about this contact"));
        }
    }

    @Test
    public void removeContactPositiveTest() {
        //check size of contact list
        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        app.getContact().pause(2000);
        //check size of contact list
        int sizeAfter = app.getContact().sizeOfContacts();
        //compare list before - list after

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
