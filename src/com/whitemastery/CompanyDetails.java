package com.whitemastery;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class CompanyDetails {

	public void companyDetails() throws InterruptedException {

//		Test Input :

//		url = "https://pos.dinamic.io/";
//		email = "ops@thepumphouse.in";
//		password = "TPH@dinamic02";
//		PIN : 7600
//		Number : 9444409275
//		TIN : A173CBSHBC9878D

// NOTE : Dont have to check the Banner deature      		

		String url = "https://pos.dinamic.io/";
		String email = "ops@thepumphouse.in";
		String password = "TPH@dinamic02";

		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("diable-notifications");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		driver.get("https://pos.dinamic.io");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Action object

		driver.manage().window().maximize();
		driver.get(url);

		// Fluent wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		WebElement user = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		action.moveToElement(user).click().build().perform();
		user.sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='pos-rel']")).click();
		driver.findElement(By.xpath("//div[@class='d-flex align-items-center push-48-t push-48-b msvg']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='7']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='6']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='0']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='0']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Company']")).click();
		driver.findElement(By.xpath(
				"//body[1]/app-root[1]/app-sidebar-layout[1]/main[1]/app-company[1]/main[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Edit']")));
		driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
		driver.findElement(By.xpath("//input[@id='cTIN']")).sendKeys("A173CBSHBC9878D");
		driver.findElement(By.xpath("//span[normalize-space()='DONE']")).click();
		driver.findElement(By.xpath("//i[normalize-space()='arrow_back']")).click();
		driver.findElement(By.xpath(
				"//body[1]/app-root[1]/app-sidebar-layout[1]/main[1]/app-company[1]/main[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[2]"))
				.click();
		driver.findElement(By.xpath("(//button[normalize-space()='Edit'])[1]")).click();
		WebElement ok = driver.findElement(By.xpath("//input[@id='cphone']"));
		ok.click();
		ok.clear();
		ok.sendKeys("9444409275");
		driver.findElement(By.cssSelector("button[type='button'] span")).click();
		driver.findElement(By.xpath("//i[normalize-space()='arrow_back']")).click();
		driver.findElement(By.xpath("//div[normalize-space()='Currency']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
		Select s = new Select(driver.findElement(By.xpath("//select[@name='currenyType']")));
		Thread.sleep(2000);
		s.selectByValue("0: INDIAN RUPEES (INR)");
		Thread.sleep(2000);
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"editcurrency\\\"]/div/div/div[3]/button[2]/span")));
		driver.findElement(By.xpath("//*[@id=\"editcurrency\"]/div/div/div[3]/button[2]/span")).click();
		driver.findElement(By.xpath("//i[normalize-space()='arrow_back']")).click();
		driver.findElement(By.xpath("//div[normalize-space()='Service charge %']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cservice")));
		WebElement text = driver.findElement(By.id("cservice"));
		text.clear();
		text.click();
		text.sendKeys("10");
		driver.findElement(By.xpath("//*[@id=\"editservice\"]/div/div/div[3]/button[2]/span")).click();
		driver.quit();
	}
}
