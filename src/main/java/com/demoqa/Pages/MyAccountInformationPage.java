package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class MyAccountInformationPage extends TestBase {
	public MyAccountInformationPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	WebElement firstName;

	@FindBy(id = "input-lastname")
	WebElement lastName;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	public String getFirstName() {
		return firstName.getAttribute("value");
	}

	public String getLastName() {
		return lastName.getAttribute("value");
	}

	public String getEmail() {
		return email.getAttribute("value");
	}

	public String getTelephone() {
		return telephone.getAttribute("value");
	}

}
