package com.whitemastery;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class DeletingCategory {
	String url = "https://pos.dinamic.io/";
	String email = "ops@thepumphouse.in";
	String password = "TPH@dinamic02";

	List<String> categoryNames = new ArrayList<>();
	List<Integer> categoryRanks = new ArrayList<>();

	public void deleteCategory() throws InterruptedException {
		for (int n = 0; n <= 20; n++) {
			categoryRanks.add(n);
			categoryNames.add(categoryRanks.get(n) + " Category");
		}
		System.out.println("Number of Ranks :" + categoryRanks.size());

		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
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
		driver.findElement(By.xpath("//a[normalize-space()='Staff']")).click();
		
		driver.findElement(By.xpath("//a[normalize-space()='Staff']")).click();
//		Thread.sleep(2000);
		//Clicking Categories option on side menu
		driver.findElement(By.xpath("//a[normalize-space()='Categories']")).click();
//		Thread.sleep(2000);
//		Clicking View option on categories
		driver.findElement(By.xpath("//a[@href='/management/menu/categories']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class='btn btn-red-text width-auto p-0 ml-3']")));
		 List<WebElement> remove =driver.findElements(By.xpath("//button[@class='btn btn-red-text width-auto p-0 ml-3']"));
		for (int i = 0; i < remove.size(); i++) {
			Thread.sleep(2000);
			WebElement remove1 = remove.get(i);
			action.click(remove1).build().perform();
			WebElement confirm = driver.findElement(By.xpath("//button[@class='col-md-3 btn btn-red d-flex align-items-center justify-content-center']"));
			wait.until(ExpectedConditions.elementToBeClickable(confirm));
			action.click(confirm).build().perform();
			
		}
		driver.quit();

	}
}
