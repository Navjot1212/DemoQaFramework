package com.demoqa.Pages;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.demoqa.Base.TestBase;

public class AddressBookPage extends TestBase {
	
	Scanner sc = new Scanner(System.in);

	public AddressBookPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ("div.alert"))
	WebElement sucessAlert;
	
	@FindBy(id = ("input-firstname"))
	WebElement firstName;
	
	@FindBy(id = ("input-lastname"))
	WebElement lastName;
	
	@FindBy(id = ("input-address-1"))
	WebElement address;
	
	@FindBy(id = ("input-city"))
	WebElement city;
	
	@FindBy(id = ("input-postcode"))
	WebElement postalCode;
	
	@FindBy(id = ("input-country"))
	WebElement country;
	
	@FindBy(id = ("input-zone"))
	WebElement province;
	
	@FindBy(css = ("input[value='Continue']"))
	WebElement continueBtn;
	
	public String getSucessAlert() {
		return sucessAlert.getText();
	}

	public String getAddress(String customerName, String postalCode) {
		return getElementFromTheTable(customerName, postalCode, AddressBookEntries.ADDRESS).getText();

	}

	public void editAddress(String customerName, String postalcode) {
		getElementFromTheTable(customerName, postalcode, AddressBookEntries.EDIT).click();
		System.out.println("Please enter first name");
		firstName.clear();
		firstName.sendKeys(sc.nextLine());
		System.out.println("Please enter last name");
		lastName.clear();
		lastName.sendKeys(sc.nextLine());
		System.out.println("Please enter Address");
		address.clear();
		address.sendKeys(sc.nextLine());
		System.out.println("Please enter City");
		city.clear();
		city.sendKeys(sc.nextLine());
		System.out.println("Please enter postal code");
		postalCode.clear();
		postalCode.sendKeys(sc.nextLine());
		System.out.println("Please enter Country");
		String country = sc.nextLine();
		selectElementByVisibleText(this.country, country);
		System.out.println("Please enter Region/State");
		String province = sc.nextLine();
		selectElementByVisibleText(this.province, province);
		continueBtn.click();

	}

	public void deleteAddress(String customerName, String postalCode) {
		getElementFromTheTable(customerName, postalCode, AddressBookEntries.DELETE).click();
		driver.switchTo().alert().accept();

	}

	public WebElement getElementFromTheTable(String customerName, String postalCode, AddressBookEntries column) {
		List<WebElement> rowsInTable = driver.findElements(By.cssSelector("table.table tbody tr"));
		for (WebElement row : rowsInTable) {
			List<WebElement> cellsInARow = row.findElements(By.cssSelector("td"));
			String addressText = cellsInARow.get(0).getText();
			if (addressText.contains(customerName) && addressText.contains(postalCode)) {
				switch (column) {
				case ADDRESS:
					return cellsInARow.get(0);
				case EDIT:
					return cellsInARow.get(1).findElement(By.cssSelector("a:first-of-type"));
				case DELETE:
					return cellsInARow.get(1).findElement(By.cssSelector("a:last-of-type"));
				default:
					System.out.println("Column not found!!!");
					break;
				}
			}
		}
		System.out.println("Address not found!!!");
		return null;
	}

	public enum AddressBookEntries {
		ADDRESS,
		EDIT,
		DELETE;
	}
	
	public void selectElementByVisibleText(WebElement element, String value) {
		Select sc = new Select(element);
		sc.selectByVisibleText(value);
	}
}

