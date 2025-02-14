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

public class WeatherAppTesting {

    public static void main(String[] args) {
        AppiumDriver driver = null;

        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("Pixel 9 Pro XL API 35");
            options.setAppPackage("com.example.weatherapp");
            options.setAppActivity("com.example.weatherapp.ui.MainActivity");
            options.setAutomationName("UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement cityNameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/cityNameField")));
            cityNameField.sendKeys("Mumbai");

            WebElement fetchButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.weatherapp:id/fetchButton")));
            fetchButton.click();

            WebElement locationField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/locationField")));
            System.out.println("City: " + locationField.getText());

            WebElement temperatureField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/temperatureField")));
            System.out.println("Temperature: " + temperatureField.getText());

            WebElement detailsViewButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.example.weatherapp:id/detailsViewButton")));
            detailsViewButton.click();

            WebElement pressureField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/pressureField")));
            System.out.println("Pressure: " + pressureField.getText());

            WebElement humidityField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/humidityField")));
            System.out.println("Humidity: " + humidityField.getText());

            WebElement windSpeedField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherapp:id/windSpeedField")));
            System.out.println("Wind Speed: " + windSpeedField.getText());

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
