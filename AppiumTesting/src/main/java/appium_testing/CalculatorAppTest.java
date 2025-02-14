package appium_testing;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CalculatorAppTest {

    public static void main(String[] args) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 9 Pro XL API 35");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.example.calculator");
        options.setAppActivity(".MainActivity");

        AppiumDriver driver = null;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement firstNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/etFirstNumber")));
            WebElement secondNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/etSecondNumber")));
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.calculator:id/btnAdd")));
            WebElement resultView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/tvResult")));

            firstNumber.sendKeys("5");
            secondNumber.sendKeys("3");
            addButton.click();

            String resultText = wait.until(ExpectedConditions.visibilityOf(resultView)).getText();
            if (resultText.contains("8")) {
                System.out.println("Test Passed: Result is correct.");
            } else {
                System.out.println("Test Failed: Incorrect result.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
