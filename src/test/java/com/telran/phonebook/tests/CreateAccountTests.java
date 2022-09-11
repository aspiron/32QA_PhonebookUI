package com.telran.phonebook.tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        // precondition: user has to be logged in
        if (!app.getHeader().isLoginLinkPresent()) {
            app.getHeader().clickOnSignOutButton();
        } else {
            // click on LOGIN link
            app.getHeader().clickOnLoginLink();
        }
    }


    // test
    @Test
    public void createAccountPositiveTest() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        // assert that reg form is displayedx`
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        // fill reg form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("email" + i + "@gmail.com").setPassword("Aa12345~"));
        // click on REGISTRATION button
        app.getUser().clickOnRegistrationButton();
        // verify that Sign Out button is displayed
        app.getUser().pause(1000);
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());

    }

    @Test
    public void createAccountNegativeTestWithInvalidPassword() {
        // assert that reg form is displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        // fill reg form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+999@gmail.co").setPassword("Aa12345"));
        // click on REGISTRATION button
        app.getUser().clickOnRegistrationButton();
        // verify that Sign Out button is displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().isErrorMessagePreesent());
    }

    @Test
    public void createAccountNegativeTestWithoutPassword() {
        // assert that reg form is displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        // fill reg form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+999@gmail.co"));
        // click on REGISTRATION button
        app.getUser().clickOnRegistrationButton();
        // verify that Sign Out button is displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().isErrorMessagePreesent());
    }


}
