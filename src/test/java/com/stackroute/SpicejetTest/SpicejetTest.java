package com.stackroute.SpicejetTest;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpicejetTest {
	static WebDriver driver;
	private static final String CHROMEPATH = System.getProperty("user.dir") + "/lib/chromedriver.exe";
	private static final String APPURL = "https://www.spicejet.com/";
	private static final String SEARCHURL = "https://www.spicejet.com/search?from=BLR&to=AMD&tripType=1&departure=2021-12-05&adult=1&child=0&infant=0&currency=INR&redirectTo=/";
	private static final String BOOKINGURL = "https://www.spicejet.com/booking";
	private static final String ADDONSURL = "https://www.spicejet.com/booking/addons";
	private static final String PREPAYMENTURL = "https://www.spicejet.com/booking/prepayment";

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", CHROMEPATH);
		driver = new ChromeDriver();

		driver.navigate().to(APPURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void homepageTest() throws InterruptedException {

		driver.findElement(By.xpath("//div[1]/div[3]/div[2]/div[3]/div/div[1]/div[1]/div[2]/input")).click();

		driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[10]")).click();

		driver.findElement(By.xpath("//div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[2]/div/div[3]/div[1]/div[7]")).click();

		driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();
		Thread.sleep(3000);
		assertEquals(driver.getCurrentUrl(), SEARCHURL);
	}

	@Test(priority = 2)
	public void PricechooseTest() throws InterruptedException {

		driver.findElement(By.xpath("//div[5]/div[2]/div/div[2]/div[1]/div/div/div/div[1]/div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[1]/div[5]/div/div/div[2]/div[2]")).click();
		Thread.sleep(5000);

		assertEquals(driver.getCurrentUrl(), BOOKINGURL);

	}

	@Test(priority = 3)
	public void DataInsertionTest() throws InterruptedException {

		driver.findElement(By.xpath("//input[@data-testid='first-inputbox-contact-details']")).sendKeys("Chris");

		driver.findElement(By.xpath("//input[@data-testid='last-inputbox-contact-details']")).sendKeys("Evans");

		driver.findElement(By.xpath("//input[@data-testid='contact-number-input-box']")).sendKeys("7736159975");

		driver.findElement(By.xpath("//input[@data-testid='emailAddress-inputbox-contact-details']"))
				.sendKeys("chris.evans@gmail.com");

		driver.findElement(By.xpath("//input[@data-testid='traveller-0-first-traveller-info-input-box']"))
				.sendKeys("Chris");

		driver.findElement(By.xpath("//input[@data-testid='traveller-0-last-traveller-info-input-box']"))
				.sendKeys("Evans");

		driver.findElement(By.xpath("//input[@data-testid='sc-member-mobile-number-input-box']"))
				.sendKeys("7736159975");

		driver.findElement(By.xpath("//div[@data-testid='traveller-info-continue-cta']")).click();
		Thread.sleep(5000);

		assertEquals(driver.getCurrentUrl(), ADDONSURL);

	}

	@Test(priority = 4)
	public void AddonsTest() throws InterruptedException {

		driver.findElement(By.xpath("//div[@data-testid='add-ons-continue-footer-button']")).click();
		Thread.sleep(5000);
		assertEquals(driver.getCurrentUrl(), PREPAYMENTURL);
	}

	@Test(priority = 5)
	public void FinalpageTest() throws InterruptedException {

		String buttoncolor = driver.findElement(By.xpath("//div[@data-testid='common-proceed-to-pay']"))
				.getCssValue("background-color");

		assertEquals(buttoncolor, "rgba(56, 193, 114, 1)");

		String totalpay = driver.findElement(By.xpath("//div[3]/div[6]/div/div[2]/div[2]")).getText();

		assertEquals(totalpay, "17,324");

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
