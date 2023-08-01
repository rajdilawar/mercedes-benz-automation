package com.mercedes.pages;
import com.mercedes.util.ScreenshotUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class CarConfigurationPage extends GenericClass {
    private WebDriver driver;
    private Properties configuration;

    public int prizeRangeMinValue() {
        String minValue = configuration.getProperty("prizeRange_Min");
        return Integer.parseInt(minValue);
    }

    public int prizeRangeMaxValue() {
        String maxValue = configuration.getProperty("prizeRange_Max");
        return Integer.parseInt(maxValue);
    }

    public CarConfigurationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        configuration = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            configuration.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // LOCATORS
    private static final String SHADOW_HOST_SELECTOR = "body > div.root.responsivegrid.owc-content-container > div > div > div > owcc-car-configurator";
    private static final String DROPDOWN_SHADOW_SELECTOR = "#cc-app-container-main > div.cc-app-container__main-frame > div > div > div:nth-child(3) > cc-motorization > cc-motorization-filters > cc-motorization-filters-form > form > div > div.cc-motorization-filters-form__primary > div > cc-motorization-filters-primary-filters > div > fieldset > div > ccwb-multi-select";
    private static final String DROPDOWN_NESTED_SHADOW_SELECTOR = "button";

    private static final String FUEL_CHECKBOX_SHADOW_SELECTOR = "#cc-app-container-main > div.cc-app-container__main-frame > div > div > div:nth-child(3) > cc-motorization > cc-motorization-filters > cc-motorization-filters-form > form > div > div.cc-motorization-filters-form__primary > div > cc-motorization-filters-primary-filters > div > fieldset > div > ccwb-multi-select > ccwb-checkbox:nth-child(1)";
    private static final String FUEL_CHECKBOX_NESTED_SHADOW_SELECTOR = "label";

    private static final String CAR_SELECTOR = "#cc-app-container-main > div.cc-app-container__main-frame > div > div > div:nth-child(3) > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(%s) > ccwb-card > div.cc-motorization-comparison-header-wrapper > cc-motorization-header > div > div > ccwb-heading";
    private static final String CAR_PRICE_SELECTOR = "#cc-app-container-main > div.cc-app-container__main-frame > div > div > div:nth-child(3) > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(%s) > ccwb-card > div.cc-motorization-comparison-header-wrapper > cc-motorization-header > div > div > ccwb-text";
    private static final String SHADOW_ROOT_PRIZE_HEADING = "[id^='ccwb-heading']";
    private static final String SHADOW_ROOT_PRIZE_TEXT = "[id^='ccwb-text']";


    public void clickFuelType() {
            GenericClass genericClass = new CarConfigurationPage(driver);
        try
        {
            WebElement nestedFilterElement = genericClass.getShadowDomWebElements(SHADOW_HOST_SELECTOR, DROPDOWN_SHADOW_SELECTOR, DROPDOWN_NESTED_SHADOW_SELECTOR);
            nestedFilterElement.click();
            WebElement nestedDropDownElement = genericClass.getShadowDomWebElements(SHADOW_HOST_SELECTOR, FUEL_CHECKBOX_SHADOW_SELECTOR, FUEL_CHECKBOX_NESTED_SHADOW_SELECTOR);

            if(!nestedDropDownElement.isSelected())
            {
                Actions actions = new Actions(driver);
                actions.moveToElement(nestedDropDownElement).perform();
                // Perform the click at the current mouse position
                actions.click().perform();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void getprices() {
        List<Integer> carPricesList = new ArrayList<>();
        try
        {
            WebElement car = null;
            WebElement carPrice = null;
            for(int i=1;i<=4;i++)
            {
                car = driver.findElement(By.cssSelector(String.format(SHADOW_HOST_SELECTOR))).getShadowRoot().
                        findElement(By.cssSelector(String.format(CAR_SELECTOR, i)));

                carPrice = driver.findElement(By.cssSelector(String.format(SHADOW_HOST_SELECTOR))).getShadowRoot().
                        findElement(By.cssSelector(String.format(CAR_PRICE_SELECTOR, i)));

                System.out.print(car.getText());
                System.out.print("\n");
                System.out.print("My car prize " + carPrice.getText());
                System.out.print("\n");
                String carPriceValueString = carPrice.getText().replace("£", "").replace(",", "");
                carPricesList.add(Integer.parseInt(carPriceValueString));         }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Car prize max value is " + Collections.max(carPricesList));
        System.out.println("car prize max value is " + Collections.min(carPricesList));
        com.mercedes.util.FileUtils.writeToFile("output.txt", "Car prize max value is  " + Collections.max(carPricesList) + " And car prize min value is  " + Collections.min(carPricesList));
    }

    public boolean validateCarPrices() {
        boolean allPricesWithinRange = true;

        try {
            List<WebElement> carElements = driver.findElements(By.cssSelector(String.format(SHADOW_HOST_SELECTOR)))
                    .get(0).getShadowRoot().findElements(By.cssSelector(String.format(SHADOW_ROOT_PRIZE_HEADING)));

            List<WebElement> carPriceElements = driver.findElements(By.cssSelector(String.format(SHADOW_HOST_SELECTOR)))
                    .get(0).getShadowRoot().findElements(By.cssSelector(String.format(SHADOW_ROOT_PRIZE_TEXT)));

            for (int i = 0; i < carElements.size(); i++) {
                String carPriceText = carPriceElements.get(i).getText();
                String carPriceValueString = carPriceText.replace("£", "").replace(",", "");
                int carPriceValue = Integer.parseInt(carPriceValueString);
                if (carPriceValue < prizeRangeMinValue() || carPriceValue > prizeRangeMaxValue()) {
                    allPricesWithinRange = false;
                    System.out.println("Car: " + carElements.get(i).getText() + " - Price: " + carPriceText + " is not within the valid range (£15,000 - £60,000).");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            allPricesWithinRange = false;
        }

        return allPricesWithinRange;
    }

    public void checkCarPricesInRange() {
        // Call the method to get the prices
        getprices();
        // Create an instance of the ScreenshotUtils class
        ScreenshotUtils screenshotUtils = new ScreenshotUtils(driver, "/Users/rajdilawar/IdeaProjects/Mercedes-Benz/screenshot");

        // Call the method to take a screenshot at the middle of the page and specify the file name
        screenshotUtils.takeScreenshotAtMiddleOfPage("screenshot.jpg");

        // Call the method to validate the prices
        boolean allPricesWithinRange = validateCarPrices();
        if (allPricesWithinRange) {
            System.out.println("All car prices are within the valid range (£15,000 - £60,000).");

        } else {
            System.out.println("Some car prices are not within the valid range (£15,000 - £60,000).");
        }
    }
}
