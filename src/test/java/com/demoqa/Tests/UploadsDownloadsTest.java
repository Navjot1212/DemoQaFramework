package com.demoqa.Tests;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.demoqa.Base.TestBase;
import com.demoqa.Pages.ElementsPage;
import com.demoqa.Pages.HomePage;
import com.demoqa.Pages.UploadDownloadPage;

public class UploadsDownloadsTest extends TestBase {

	SoftAssert softAssert;
	HomePage homePage;
	ElementsPage elementsPage;
	UploadDownloadPage uploadDownloadPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		homePage = new HomePage();
		elementsPage = homePage.clickElementBtn();
		uploadDownloadPage = elementsPage.clickUploadAndDownloadBtn();
	}

	@Test
	public void verifyuploadFunctionality() {
		uploadFile("C:\\Users\\navjo\\Downloads\\sampleFile.jpeg", uploadDownloadPage.UploadBtn());
		softAssert.assertEquals(uploadDownloadPage.getUploadedFileName(), "sampleFile.jpeg",
				"File name did not matched");
		softAssert.assertAll();
	}

	
	//jenkins 2
	@Test
	public void verifyUserIsAbleTODownloadFile() {
		uploadDownloadPage.clickDownloadBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		File downloadedFile = null;
		boolean isFilePresent = false;
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().equals("sampleFile.jpeg")) {
				isFilePresent = true;
				downloadedFile = new File(file.getName());

			}
		}
		softAssert.assertTrue(isFilePresent, "File Not Found");
		downloadedFile.deleteOnExit();
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

	private void uploadFile(String filePath, WebElement uploadButton) {
		uploadButton.sendKeys(filePath);
	}
}
