package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.demoqa.Base.TestBase;
import com.demoqa.Utils.Utils;

public class LaptopsAndNotebooksPage extends TestBase {

	public LaptopsAndNotebooksPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#content h2")
	WebElement pageHeadingText;

	@FindBy(id = "input-sort")
	WebElement sortingList;

	@FindBy(css = "#content>div:nth-of-type(4)>div:first-of-type button:nth-of-type(2)")
	WebElement firstProductWishListBtn;

	@FindBy(css = "#content>div:nth-of-type(4)>div:nth-of-type(2) button:nth-of-type(2)")
	WebElement secondProductWishListBtn;

	@FindBy(css = "#content>div:nth-of-type(4)>div:nth-of-type(3) button:nth-of-type(2)")
	WebElement thirdProductWishListBtn;

	@FindBy(css = "div.alert")
	WebElement wishlistAddedSuccessText;

	@FindBy(css = "div.alert a:last-of-type")
	WebElement wishlistBtn;

	public String getPageHeadingText() {
		return pageHeadingText.getText();
	}

	public void sortProductsByHighestRating() {
		selectElementByVisibleText(sortingList, "Rating (Highest)");
	}

	private void addFirstProductToWishList() {
		firstProductWishListBtn.click();
	}

	private void addSecondProductToWishList() {
		secondProductWishListBtn.click();
	}

	private void addThirdProductToWishList() {
		thirdProductWishListBtn.click();
	}

	public void addProductsToWishList() {
		addFirstProductToWishList();
		addSecondProductToWishList();
		addThirdProductToWishList();
		Utils.sleep(1500);
	}

	public String getWishlistAddedSuccessText() {
		return wishlistAddedSuccessText.getText();
	}

	private void selectElementByVisibleText(WebElement element, String text) {
		Select sc = new Select(element);
		sc.selectByVisibleText(text);
	}

	public MyWishListPage clickWishList() {
		wishlistBtn.click();
		return new MyWishListPage();
	}

}
