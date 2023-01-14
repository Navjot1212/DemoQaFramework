package com.demoqa.Tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.demoqa.Base.TestBase;
import com.demoqa.Pages.AccountLoginPage;
import com.demoqa.Pages.AddressBookPage;
import com.demoqa.Pages.MyAccountPage;
import com.demoqa.Pages.YourStorePage;
import com.demoqa.Utils.Utils;

public class AddressBookTest extends TestBase {

	SoftAssert softAssert;
	YourStorePage yourStorePage;
	AccountLoginPage accountLoginPage;
	MyAccountPage myAccountPage;
	AddressBookPage addressBookPage;

//	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		yourStorePage = new YourStorePage();
		yourStorePage.clickMyAccount();
		AccountLoginPage accountLoginPage = yourStorePage.clickLoginBtn();
		myAccountPage = accountLoginPage.login("Navjot.rehal12@gmail.com", "Navjot13");
		addressBookPage = myAccountPage.clickAddressBookBtn();

	}

	@Test
	public void verifyUserIsAbleToGetAddress() {
		String firstAddress = addressBookPage.getAddress("Navjot Singh", "L9E 1L3");
		softAssert.assertEquals(firstAddress, "Navjot Singh\n621 Beam Court\nMilton L9E 1L3\nOntario\nCanada",
				"Wrong Address found");
		String secondAddress = addressBookPage.getAddress("Harjinder Kaur", "L9E 1L3");
		softAssert.assertEquals(secondAddress, "Harjinder Kaur\n621 Beam court\nMilton L9E 1L3\nOntario\nCanada",
				"Wrong Address found");
		String thirdAddress = addressBookPage.getAddress("Kuldeep Singh", "L6S 2L1");
		softAssert.assertEquals(thirdAddress, "Kuldeep Singh\n19 Grand Valley\nBrampton L6S 2L1\nOntario\nCanada",
				"Wrong Address found");
		String fourthAddress = addressBookPage.getAddress("Damanpreet Singh", "M9V 1P5");
		softAssert.assertEquals(fourthAddress, "Damanpreet Singh\n66 Humber College Blvd\nEtobicoke M9V 1P5\nOntario\nCanada",
				"Wrong Address found");
		String fifthAddress = addressBookPage.getAddress("Navjot Singh", "L5M 1Y4");
		softAssert.assertEquals(fifthAddress, "Navjot Singh\n13 Thomas St\nMississauga L5M 1Y4\nOntario\nCanada",
				"Wrong Address found");
		softAssert.assertAll();

	}

	@Test
	public void verifyUserIsAbleToDeleteAddress() {
		addressBookPage.deleteAddress("Navjot Singh", "L5M 1Y4");
		softAssert.assertEquals(addressBookPage.getSucessAlert(), "Your address has been successfully deleted");
		addressBookPage.deleteAddress("Damanpreet Singh", "M9V 1P5");
		softAssert.assertEquals(addressBookPage.getSucessAlert(), "Your address has been successfully deleted");
		softAssert.assertAll();

	}

	@Test
	public void verifyUserIsAbleToEditAddress() {
		addressBookPage.editAddress("Damanpreet Singh", "M9V 1P5");
		softAssert.assertEquals(addressBookPage.getSucessAlert(), "Your address has been successfully updated","Address did not changed");
		addressBookPage.editAddress("Navjot Singh", "L5M 1Y4");
		softAssert.assertEquals(addressBookPage.getSucessAlert(), "Your address has been successfully updated","Address did not changed");
		softAssert.assertAll();

	}
	
	
	@Test
	public void test() {
		System.out.println(Utils.getRandomPassword());
	}

//	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
}
