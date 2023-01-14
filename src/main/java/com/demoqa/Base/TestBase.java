package com.demoqa.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.demoqa.Browsers.Browsers;
import com.demoqa.Listeners.WebdriverEvents;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	Browsers DEFAULT_BROWSER = Browsers.EDGE;
	public static WebdriverEvents events;
	public EventFiringWebDriver eventFiringWebDriver;

	public void launchBrowser() {
		switch (DEFAULT_BROWSER) {
		case GOOGLE_CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Not a valid browser");
			break;
		}
		
		eventFiringWebDriver = new EventFiringWebDriver(driver);
		events = new WebdriverEvents();
		eventFiringWebDriver.register(events);
		driver = eventFiringWebDriver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
	}

	public void quitBrowser() {
		driver.close();
	}
}
