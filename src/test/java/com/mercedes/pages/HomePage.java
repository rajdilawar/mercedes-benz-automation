package com.mercedes.pages;

import org.openqa.selenium.*;


import static org.testng.Assert.assertEquals;

public class HomePage {
    private WebDriver driver;

    //Locators
    private static final By COOKIE_SHADOW_HOST = By.cssSelector("cmm-cookie-banner");
    private static final By ACCEPT_ALL_COOKIE_BUTTON = By
            .cssSelector("wb7-button.button.button--accept-all.wb-button.hydrated");
    private static final By OPEN_MENU_SHADOW_HOST = By.cssSelector("owc-header");
    private static final By OPEN_MENU_MENU_BUTTON = By.cssSelector("button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookiesConcept() {
        WebElement nestedText = driver.findElement ( (COOKIE_SHADOW_HOST)).getShadowRoot ()
                .findElement ( (ACCEPT_ALL_COOKIE_BUTTON));
        nestedText.click();
    }

    public void verifyHomePageTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Mercedes-Benz Passenger Cars";
        assertEquals(expectedTitle,actualTitle);
    }

    public void OpenMenuItems() {
        WebElement nestedElement = driver.findElement ((OPEN_MENU_SHADOW_HOST)).getShadowRoot ()
                .findElement ( (OPEN_MENU_MENU_BUTTON));
        nestedElement.click();
    }

}
