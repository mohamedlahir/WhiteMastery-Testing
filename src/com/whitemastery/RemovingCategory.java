package com.whitemastery;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RemovingCategory {

	String url = "https://pos.dinamic.io/";
	String email = "ops@thepumphouse.in";
	String password = "TPH@dinamic02";

	List<String> categoryNames = new ArrayList<>();
	List<Integer> categoryRanks = new ArrayList<>();

	public void remove() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.get("https://pos.dinamic.io");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Action object

		driver.manage().window().maximize();
		driver.get(url);

		// Fluent wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
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

		// Clicking Staff menu under Management
		driver.findElement(By.xpath("//a[normalize-space()='Staff']")).click();

		// Clicking Categories option on side menu
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Categories']")));
		driver.findElement(By.xpath("//a[normalize-space()='Categories']")).click();

		// Clicking View option on categories
		driver.findElement(By.xpath("//a[@href='/management/menu/categories']")).click();
		Thread.sleep(1000);
		List<WebElement> removeCards = new ArrayList<>();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row pd-16-t'] //div[@id='div__itemcard'] //button[@class='btn btn-red-text width-auto p-0 ml-3']")));
		removeCards = driver.findElements(By.xpath(
				"//div[@class='row pd-16-t'] //div[@id='div__itemcard'] //button[@class='btn btn-red-text width-auto p-0 ml-3']"));
		System.out.println(removeCards.size());
		for(int i = 0; i < removeCards.size(); i++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='div__itemcard_0']//button[@class='btn btn-red-text width-auto p-0 ml-3'][normalize-space()='REMOVE']")));
			driver.findElement(By.xpath(
					"//div[@class='row pd-16-t'] //div[@id='div__itemcard'] //button[@class='btn btn-red-text width-auto p-0 ml-3']"))
					.click();

			Thread.sleep(1000);

			WebElement remove2 = driver.findElement(By.xpath("//span[normalize-space()='REMOVE']"));

			action.click(remove2).build().perform();
		}
		driver.quit();

	}
}
