package com.whitemastery;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class CategoryEditCheck {
	

	String url = "https://pos.dinamic.io/";
	String email = "ops@thepumphouse.in";
	String password = "TPH@dinamic02";

	List<String> categoryNames = new ArrayList<>();
	List<Integer> categoryRanks = new ArrayList<>();

	public void edit() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(co);
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

//		Thread.sleep(5000);

		//Clicking Staff menu under Management
		driver.findElement(By.xpath("//a[normalize-space()='Staff']")).click();
//		Thread.sleep(2000);
		//Clicking Categories option on side menu
		driver.findElement(By.xpath("//a[normalize-space()='Categories']")).click();
//		Thread.sleep(2000);
		//Clicking View option on categories
		driver.findElement(By.xpath("//a[@href='/management/menu/categories']")).click();
	//	Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[@href='/management/menu/categories']")).click();
		List<WebElement> editss = driver.findElements(By.xpath("//div[@class='row pd-16-t'] //button[@class='btn btn-purple-text width-auto p-0']"));
		for(int i=0;i<editss.size();i++) {
		editss.get(i).click();
		WebElement catName = driver.findElement(By.id("category_name"));
		Thread.sleep(1000);
		action.click(catName).click().build().perform();
		catName.clear();
		catName.sendKeys("Edit ");
		Thread.sleep(1000);
		WebElement catRank = driver.findElement(By.id("category_rank"));
		Thread.sleep(1000);
		action.click(catRank).click().build().perform();
		catRank.clear();
		catRank.sendKeys("1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='NEXT']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='DONE']")).click();
		}
	driver.close();
	}

}
