package com.telran.phonebook.tests;

import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviders;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // ensure sign out button is not displayed
        if (!app.getHeader().isSignOutButtonPresent()) {
            //click on login link
            app.getHeader().clickOnLoginLink();
            //login
            app.getUser().login();
            //click on the link add
            app.getHeader().clickOnAddLink();
        }
    }

    @Test
    public void addContactPositiveTest() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        //fill contact form
        app.getContact().addRandomContact(i);
        // assert contact is created
        Assert.assertTrue(app.getContact().isContactCreated("Ivan" + i));
    }

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String surname, String phone, String
            email, String address, String descript) {
        app.getContact().addContact(new Contact()
                .setName(name)
                .setSurname(surname)
                .setTelephone(phone)
                .setEmail(email)
                .setAddress(address).setDescription(descript));
        app.getContact().removeContact();
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithCsvFile(Contact contact) {
        app.getContact().addContact(contact);
        app.getContact().removeContact();
        app.getUser().pause(2000);

    }


}

