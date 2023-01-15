package com.demoqa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.Base.TestBase;

public class UploadDownloadPage extends TestBase {

	public UploadDownloadPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "uploadFile")
	WebElement uploadBtn;

	@FindBy(id = "downloadButton")
	WebElement downloadBtn;

	@FindBy(id = "uploadedFilePath")
	WebElement filePath;

	public WebElement UploadBtn() {
		return uploadBtn;
	}

	public void clickDownloadBtn() {
		downloadBtn.click();
	}

	public String getUploadedFileName() {
		String filepath = filePath.getText();
		String[] fileName = filepath.split("\\\\");
		return fileName[2];
	}
}
