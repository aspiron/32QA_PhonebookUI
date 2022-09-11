package com.telran.phonebook.tests;

import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test
    public void openHomepage() {
        System.out.println("Home Component: " + app.getHomepage().isHomeComponentPresent2());
        app.getHomepage().isHomeComponentPresent();
    }

}
