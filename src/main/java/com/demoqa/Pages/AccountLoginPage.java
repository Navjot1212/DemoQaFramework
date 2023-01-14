package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class AccountLoginPage extends TestBase {

	public AccountLoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "form div[class='form-group']:first-of-type input")
	WebElement email;

	@FindBy(css = "form div[class='form-group']:last-of-type input")
	WebElement password;

	@FindBy(css = "input[type='submit']")
	WebElement loginBtn;

	private void enterEmail(String email) {
		this.email.sendKeys(email);
	}

	private void enterPassword(String password) {
		this.password.sendKeys(password);
	}

	public MyAccountPage login(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		loginBtn.submit();
		return new MyAccountPage();
	}

}
