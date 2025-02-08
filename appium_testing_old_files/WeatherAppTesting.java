package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WeatherAppTesting {

	public static void main(String[] args) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35"); 
		
		caps.setCapability("appPackage", "com.example.weatherapp");
		caps.setCapability("appActivity", "com.example.weatherapp.ui.MainActivity");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		
		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

			Thread.sleep(3000);
			
			WebElement cityNameField = driver.findElement(By.id("com.example.weatherapp:id/cityNameField"));
			cityNameField.sendKeys("Mumbai");
			
			WebElement fetchButton = driver.findElement(By.id("com.example.weatherapp:id/fetchButton"));
			fetchButton.click();
			
			Thread.sleep(3000);
			
			WebElement locationField = driver.findElement(By.id("com.example.weatherapp:id/locationField"));
			System.out.println("City: " + locationField.getText());
			
			WebElement temperatureField = driver.findElement(By.id("com.example.weatherapp:id/temperatureField"));
			System.out.println(temperatureField.getText());
			
			WebElement detailsViewButton = driver.findElement(By.id("com.example.weatherapp:id/detailsViewButton"));
			detailsViewButton.click();
			
			Thread.sleep(3000);
			
			System.out.println("Successfully Navigated to Weather Detail Fragment");
			
			WebElement pressureField = driver.findElement(By.id("com.example.weatherapp:id/pressureField"));
			System.out.println(pressureField.getText());
			
			WebElement humidityField = driver.findElement(By.id("com.example.weatherapp:id/humidityField"));
			System.out.println(humidityField.getText());
			
			WebElement windSpeedField = driver.findElement(By.id("com.example.weatherapp:id/windSpeedField"));
			System.out.println(windSpeedField.getText());
			
			driver.quit();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		
	}
	
}
