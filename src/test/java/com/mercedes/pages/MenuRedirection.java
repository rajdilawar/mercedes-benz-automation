package com.mercedes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MenuRedirection {
    private WebDriver driver;
    public MenuRedirection(WebDriver driver) {this.driver = driver; }

    //Locators
    private static final By OUR_MODEL_HOST = By.cssSelector("owc-header[class='webcomponent aem-GridColumn aem-GridColumn--default--12']");
    private static final By OUR_MODEL_BUTTON = By.cssSelector("nav li button[class*='owc-header-navigation-topic__button']");


    public void clickOurModelButton() {
        WebElement nestedElement = driver.findElement ( (OUR_MODEL_HOST)).getShadowRoot ()
                .findElement ( (OUR_MODEL_BUTTON));
        nestedElement.click();
    }
    private boolean moveTo(final WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        return true;
    }
    public void clickElement(String selector1, String selector2, String label) {
        WebElement nestedElement = driver.findElement(By.cssSelector(selector1)).getShadowRoot()
                .findElement(By.cssSelector(selector2));
        if (label!=null) {
            List<WebElement> carElements = nestedElement.findElements(By.tagName("li"));
            for (WebElement carElement : carElements) {
                String carText = carElement.getText();
                if (carText.equals(label)) {
                    moveTo(carElement);
                    carElement.click();
                    break;
                }
            }
        }
    }
    public void clickElement(String label) {
        if (label.equals("Hatchbacks"))
            clickElement("vmos-flyout[class='webcomponent webcomponent-nested']","#app-vue > div > ul","Hatchbacks");
        else if (label.equals("A-Class Hatchback"))
            //clickElement("vmos-flyout[class='webcomponent webcomponent-nested']","#app-vue > div > ul","A-Class Hatchbacks");
            clickElement("body > div.root.responsivegrid.owc-content-container > div > owc-header > vmos-flyout", "#app-vue > div > owc-header-flyout > ul", "A-Class Hatchback");
    }
}
