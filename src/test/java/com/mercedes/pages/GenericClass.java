package com.mercedes.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class GenericClass {
    private WebDriver driver;

    public GenericClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getShadowDomWebElements(String MAIN_SHADOW_ELEMENT_SELECTOR, String SHADOW_ROOT_ELEMENT, String SHADOW_ROOT_NESTED) {
        return driver.findElement(By.cssSelector(MAIN_SHADOW_ELEMENT_SELECTOR)).getShadowRoot()
                .findElement(By.cssSelector(SHADOW_ROOT_ELEMENT)).getShadowRoot()
                .findElement(By.cssSelector(SHADOW_ROOT_NESTED));
    }


    public void scrollToTheElement(WebElement element) {
        // Scroll to the element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}