package com.telran.phonebook.tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getHeader().isLoginLinkPresent()) {
            app.getHeader().clickOnSignOutButton();
        } else {
            app.getHeader().clickOnLoginLink();
        }
    }


    @Test
    public void loginPositiveTest() {
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+999@gmail.co").setPassword("Aa12345~"));
        app.getUser().clickOnLoginButton();
        app.getUser().pause(2000);
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());
    }


}
