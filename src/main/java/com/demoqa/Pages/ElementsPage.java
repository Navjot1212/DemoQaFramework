package com.demoqa.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class ElementsPage extends TestBase {

	public ElementsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.accordion>div:first-of-type ul #item-7")
	WebElement uploadAndDownloadBtn;

	@FindBy(css = "label.cbb>svg>path:last-of-type")
	WebElement advertisement;

	public UploadDownloadPage clickUploadAndDownloadBtn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", uploadAndDownloadBtn);
		return new UploadDownloadPage();
	}

}
