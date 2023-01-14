package com.demoqa.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class MyWishListPage extends TestBase {

	public MyWishListPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tbody>tr:last-of-type>td:last-of-type a")
	WebElement lastProductRemoveBtn;

	@FindBy(css = "div.alert")
	WebElement successAlert;

	public void removeProduct(String productName,MyWishList column,By locator) {
		getElementFromTheTable(productName, column).findElement(locator).click();
	}

	public String getSuccessAlertText() {
		
		return successAlert.getText();
	}

	public WebElement getElementFromTheTable(String ProductName, MyWishList column) {
		int columnIndex = getIndexForColumn(column);
		List<WebElement> rowsInTable = driver.findElements(By.cssSelector("table.table>tbody>tr"));
		for (WebElement row : rowsInTable) {
			List<WebElement> cellsInARow = row.findElements(By.cssSelector("td"));
			String ProductNameText = cellsInARow.get(1).getText();
			if (ProductNameText.equals(ProductName)) {
				return cellsInARow.get(columnIndex);
			}
		}
		System.out.println("Column name was not found!!!");
		return null;
	}

	private int getIndexForColumn(MyWishList column) {
		List<WebElement> tableHeaders = driver.findElements(By.cssSelector("table.table>thead>tr>td"));
		for (WebElement header : tableHeaders) {
			if (header.getText().equals(column.getName())) {
				return tableHeaders.indexOf(header);
			}
		}
		System.out.println("No such column exist");
		return -1;
	}

	public enum MyWishList {
		IMAGE("Image"),
		PRODUCTNAME("Product Name"),
		MODEL("Model"),
		STOCK("Stock"),
		UNITPRICE("Unit Price"),
		ACTION("Action");

		String name;

		private MyWishList(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
}
