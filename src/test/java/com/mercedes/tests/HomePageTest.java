package com.mercedes.tests;

import com.mercedes.base.InitClass;
import com.mercedes.pages.CarConfigurationPage;
import com.mercedes.pages.CarOverviewPage;
import com.mercedes.pages.HomePage;
import com.mercedes.pages.MenuRedirection;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import io.qameta.allure.*;

public class HomePageTest {
    InitClass initclass = null;
    WebDriver driver = null;

    @BeforeTest
    public void setUpMethod() throws IOException {
        initclass = new InitClass();
        driver = initclass.getWebdriver();
        driver.get(initclass.getConfiguration().baseMercedesUrl());
    }


    @Test
    @Description("Test to verify car prices are within the valid range")
    @Severity(SeverityLevel.NORMAL)
    public void carPrizeTest()  {

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesConcept();
        homePage.verifyHomePageTitle();
        homePage.OpenMenuItems();
        MenuRedirection menuRedirection = new MenuRedirection(driver);
        menuRedirection.clickOurModelButton();
        menuRedirection.clickElement("Hatchbacks");
        menuRedirection.clickElement("A-Class Hatchback");
        CarOverviewPage carOverviewPage = new CarOverviewPage(driver);
        carOverviewPage.navigateToBuiltYourCar();
        CarConfigurationPage carConfigurationPage = new CarConfigurationPage(driver);
        carConfigurationPage.clickFuelType();
        carConfigurationPage.getprices();
        carConfigurationPage.checkCarPricesInRange();
        driver.quit();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
