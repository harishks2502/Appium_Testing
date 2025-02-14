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

public class TodoAppTest {

    public static void main(String[] args) {
        AppiumDriver driver = null;

        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("Pixel 9 Pro XL API 35");
            options.setAppPackage("com.example.todoapplication");
            options.setAppActivity("com.example.todoapplication.ui.MainActivity");
            options.setAutomationName("UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement addTaskButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.todoapplication:id/fab_add")));
            addTaskButton.click();

            WebElement taskText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.todoapplication:id/taskText")));
            taskText.sendKeys("Running");

            WebElement taskDescriptionText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.todoapplication:id/taskDescriptionText")));
            taskDescriptionText.sendKeys("Run 5 km everyday");

            WebElement addButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.todoapplication:id/addButton")));
            addButton.click();

            WebElement titleTextView = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'Running')]")));
            WebElement descriptionTextView = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'Run 5 km everyday')]")));

            if (titleTextView.isDisplayed() && descriptionTextView.isDisplayed()) {
                System.out.println("Successfully navigated to AddTaskFragment!!!");
                System.out.println("Task & Description added Successfully!");
            } else {
                System.out.println("Task or Description was NOT added.");
            }

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
