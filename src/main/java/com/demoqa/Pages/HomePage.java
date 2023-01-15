package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class HomePage extends TestBase {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.category-cards>div:first-of-type")
	WebElement elementBtn;

	public ElementsPage clickElementBtn() {
		elementBtn.click();
		return new ElementsPage();
	}

}
