package com.mercedes.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    private WebDriver driver;
    private String screenshotFolder;

    public ScreenshotUtils(WebDriver driver, String screenshotFolder) {
        this.driver = driver;
        this.screenshotFolder = screenshotFolder;

        // Create the screenshot folder if it doesn't exist
        File folder = new File(screenshotFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void takeScreenshotAtMiddleOfPage(String fileName) {
        // Scroll to the middle of the page using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");

        // Take the screenshot as a File
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Set the file path for the screenshot
        String screenshotFilePath = screenshotFolder + File.separator + fileName;

        // Delete the old screenshot file if it exists
        File oldScreenshot = new File(screenshotFilePath);
        if (oldScreenshot.exists()) {
            oldScreenshot.delete();
        }

        // Move the new screenshot file to the specified location
        try {
            FileUtils.moveFile(screenshotFile, new File(screenshotFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
