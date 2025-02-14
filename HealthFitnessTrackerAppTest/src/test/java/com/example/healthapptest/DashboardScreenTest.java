package com.example.healthapptest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class DashboardScreenTest {
    
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 9 Pro XL API 35");
        options.setAppPackage("com.example.healthfitnesstracker");
        options.setAppActivity("com.example.healthfitnesstracker.SplashActivity");
        options.setAutomationName("UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testDashboardScreen() {
        System.out.println("------ Checking Dashboard Screen ------");

        WebElement skipButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.healthfitnesstracker:id/btnSkip")));
		assertNotNull(skipButton, "Skip button should be displayed");
		skipButton.click();
        
        WebElement syncButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.example.healthfitnesstracker:id/btnSync")));
        
        assertNotNull(syncButton, "Sync button should be displayed on Dashboard");
        System.out.println("Sync button is displayed!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
