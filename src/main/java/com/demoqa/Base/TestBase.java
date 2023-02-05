package com.demoqa.Base;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.demoqa.Browsers.Browsers;
import com.demoqa.Listeners.WebdriverEvents;
import com.microsoft.edge.seleniumtools.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	private Browsers browser;
	public static WebdriverEvents events;
	private EventFiringWebDriver eventFiringWebDriver;
	@SuppressWarnings("deprecation")
	
	public void launchBrowser() {
		HashMap<String, Object> preferences = new HashMap<>();
		preferences.put("download.default_directory", System.getProperty("user.dir"));

		String browserName = System.getProperty("browser");
		if (browserName != null) {
			browser = Browsers.valueOf(browserName);
		} else {
			browser = Browsers.FIREFOX;
		}

		switch (browser) {
		case GOOGLE_CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", preferences);
			driver = new ChromeDriver(chromeOptions);
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setExperimentalOption("prefs", preferences);
			driver = new EdgeDriver(edgeOptions);
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("browser.download.folderList", 2);
			firefoxOptions.addPreference("browser.download.dir",
					"C:\\Users\\navjo\\eclipse-workspace\\DemoQAFramework");
			driver = new FirefoxDriver(firefoxOptions);
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
		driver.get("https://demoqa.com/");
	}

	public void quitBrowser() {
		driver.close();
	}
}
