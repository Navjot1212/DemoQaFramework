package com.demoqa.Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.demoqa.Base.TestBase;
import com.demoqa.Pages.AccountLoginPage;
import com.demoqa.Pages.LaptopsAndNotebooksPage;
import com.demoqa.Pages.MyAccountPage;
import com.demoqa.Pages.MyWishListPage;
import com.demoqa.Pages.YourStorePage;
import com.demoqa.Pages.MyWishListPage.MyWishList;

public class MyWishListTest extends TestBase {

	SoftAssert softAssert;
	YourStorePage yourStorePage;
	AccountLoginPage accountLoginPage;
	MyAccountPage myAccountPage;
	LaptopsAndNotebooksPage laptopsAndNotebooksPage;
	MyWishListPage myWishListPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		// Logging into NaveenAutomation website
		softAssert = new SoftAssert();
		yourStorePage = new YourStorePage();
		yourStorePage.clickMyAccount();
		AccountLoginPage accountLoginPage = yourStorePage.clickLoginBtn();
		myAccountPage = accountLoginPage.login("Nav12@icloud.com", "Navjot13");

	}

	@Test
	public void verifyUserIsAbleToModifyWishList() {
		// Navigating to laptops and notebooks menu page
		laptopsAndNotebooksPage = myAccountPage.clickAllLaptopsAndNoteBooksBtn();

		// validating laptops and notebooks page title and text
		softAssert.assertEquals(driver.getTitle(), "Laptops & Notebooks", "Page not loaded");
		softAssert.assertEquals(laptopsAndNotebooksPage.getPageHeadingText(), "Laptops & Notebooks",
				"Text did not matched");

		// Sorting products by highest to lowest rating
		laptopsAndNotebooksPage.sortProductsByHighestRating();

		// Adding first three products to cart
		laptopsAndNotebooksPage.addProductsToWishList();

		// validating products are added to cart
		softAssert.assertEquals(laptopsAndNotebooksPage.getWishlistAddedSuccessText(),
				"Success: You have added MacBook Air to your wish list!\n×", "Text did not matched");

		// Navigating to My Wish List page
		myWishListPage = laptopsAndNotebooksPage.clickWishList();

		// validating My Wish List page title
		softAssert.assertEquals(driver.getTitle(), "My Wish List", "Page not loaded");

		// Validating Product's names and prices in the wish list
		String firstProductName = myWishListPage.getElementFromTheTable("MacBook Air", MyWishList.PRODUCTNAME)
				.getText();
		String secondProductName = myWishListPage.getElementFromTheTable("MacBook Pro", MyWishList.PRODUCTNAME)
				.getText();
		String thirdProductName = myWishListPage.getElementFromTheTable("Sony VAIO", MyWishList.PRODUCTNAME).getText();
		String firstProductPrice = myWishListPage.getElementFromTheTable("MacBook Air", MyWishList.UNITPRICE).getText();
		String secondProductPrice = myWishListPage.getElementFromTheTable("MacBook Pro", MyWishList.UNITPRICE)
				.getText();
		String thirdProductPrice = myWishListPage.getElementFromTheTable("Sony VAIO", MyWishList.UNITPRICE).getText();
		softAssert.assertEquals(firstProductName, "MacBook Air", "Product name did not matched");
		softAssert.assertEquals(secondProductName, "MacBook Pro", "Product name did not matched");
		softAssert.assertEquals(thirdProductName, "Sony VAIO", "Product name did not matched");
		softAssert.assertEquals(firstProductPrice, "$1,202.00", "Product price did not matched");
		softAssert.assertEquals(secondProductPrice, "$2,000.00", "Product price did not matched");
		softAssert.assertEquals(thirdProductPrice, "$1,202.00", "Product price did not matched");

		// Removing Last product from the wish list
		myWishListPage.removeProduct("Sony VAIO",MyWishList.ACTION,By.cssSelector("a"));

		// Validating product successfully removed
		softAssert.assertEquals(myWishListPage.getSuccessAlertText(), "Success: You have modified your wish list!\n×",
				"Text did not matched");
		softAssert.assertAll();

	}
	
	@Test
	public void helloHelloMikeTesting() {
		Assert.assertTrue(false);
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
}
