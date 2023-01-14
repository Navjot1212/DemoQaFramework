package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class MyAccountPage extends TestBase {
	Actions action = new Actions(driver);
	public MyAccountPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Laptops & Notebooks']")
	WebElement laptopsMenu;

	@FindBy(xpath = "//a[text()='Show All Laptops & Notebooks']")
	WebElement allLaptopsOptionBtn;
	
	@FindBy(css = "div>a[href$='address']")
	WebElement addressBook;
	
	@FindBy(css = "li>a[href$='edit']")
	WebElement editInformation;

	private void hoverlaptopsMenu() {
		action.moveToElement(laptopsMenu).perform();
	}

	public MyAccountInformationPage clickEditInfo() {
		editInformation.click();
		return new MyAccountInformationPage();
	}
	
	public LaptopsAndNotebooksPage clickAllLaptopsAndNoteBooksBtn() {
		hoverlaptopsMenu();
		allLaptopsOptionBtn.click();
		return new LaptopsAndNotebooksPage();
	}
	
	public AddressBookPage clickAddressBookBtn() {
		addressBook.click();
		return new AddressBookPage();
	}

}
