package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CalculatorAppWithNavigationTest {

    public static void main(String[] args) {
        AppiumDriver driver = null;

        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("Pixel 9 Pro XL API 35");
            options.setAppPackage("com.example.calculator");
            options.setAppActivity("com.example.calculator.MainActivity");
            options.setAutomationName("UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement num1Field = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/etFirstNumber")));
            num1Field.sendKeys("5");

            WebElement num2Field = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/etSecondNumber")));
            num2Field.sendKeys("3");

            WebElement addButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.calculator:id/btnAdd")));
            addButton.click();

            WebElement navigateButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.calculator:id/btnNavigate")));
            navigateButton.click();

            WebElement resultView = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/textViewMessage")));

            String resultText = resultView.getText();
            System.out.println("Result Text: " + resultText);

            if ("Welcome to the Second Screen!".equalsIgnoreCase(resultText)) {
                System.out.println("Successfully navigated to SecondActivity!!!");
            } else {
                System.out.println("Not able to navigate!!!");
            }

            System.out.println("Navigation to the second screen was successful!");
        
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
