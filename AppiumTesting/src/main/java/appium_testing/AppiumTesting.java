package appium_testing;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumTesting {

    public static void main(String[] args) {
        AppiumDriver driver = null;

        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("Pixel 9 Pro XL API 35");
            options.setAutomationName("UiAutomator2");
            options.withBrowserName("Chrome");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.get("https://www.apple.com/in");

            WebElement learnMoreButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Learn more')]")));
            learnMoreButton.click();

            wait.until(ExpectedConditions.urlContains("iphone-16-pro"));
            String currentUrl = driver.getCurrentUrl();

            System.out.println("Current URL: " + currentUrl);

            if (currentUrl.equalsIgnoreCase("https://www.apple.com/in/iphone-16-pro/")) {
                System.out.println("Successfully navigated to: " + currentUrl);
            } else {
                System.out.println("URL did not change correctly.");
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
