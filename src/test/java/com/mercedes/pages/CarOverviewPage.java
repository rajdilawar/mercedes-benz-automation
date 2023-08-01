package com.mercedes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static org.testng.Assert.assertEquals;

public class CarOverviewPage {
    private WebDriver driver;
    public CarOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private static final By STAGE_SHADOW_HOST = By.cssSelector("owc-stage[class*='owc-image-stage-host']");
    private static final By BUILD_CAR_LINK =  By.linkText("Build your car");

    public void moveToAndClick(final WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void navigateToBuiltYourCar() {
        WebElement nestedElement = driver.findElement ( (STAGE_SHADOW_HOST)).getShadowRoot ()
                .findElement ( (BUILD_CAR_LINK));
        moveToAndClick(nestedElement);
    }

}
