package com.demoqa.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.demoqa.Base.TestBase;

public class Utils extends TestBase {

	public static void sample() {

	}

	public static int getRandomInteger(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

	public static String getRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static String getRandomEmail() {
		String email = "";
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		String[] domain = { "gmail.com", "yahoo.com", "icloud.com", "outlook.com" };
		for (int i = 0; i < 6; i++) {
			email += alphabets.charAt(getRandomInteger(alphabets.length()));
		}
		email += getRandomInteger(1000);
		email += "@" + domain[getRandomInteger(domain.length)];
		return email;
	}

	public static String getRandomPassword() {
		return "Password" + new Random().nextInt(1000);
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	public static void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void takeScreenShot(String testName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile,
					new File("./FailedTestScreenShots\\" + testName + "_" + timeStamp + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
