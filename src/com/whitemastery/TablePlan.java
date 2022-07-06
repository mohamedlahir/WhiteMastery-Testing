package com.whitemastery;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
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

public class TablePlan {

	String url = "https://pos.dinamic.io/";
	String email = "ops@thepumphouse.in";
	String password = "TPH@dinamic02";

	public void creatingTableArea() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		driver.manage().deleteAllCookies();
		driver.get("https://pos.dinamic.io");

		// Action object

		driver.manage().window().maximize();
		// Fluent wait

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
		driver.findElement(By.xpath("//a[@href='/management/landd']")).click();
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/management/landd/plans']")));
		driver.findElement(By.xpath("//a[@href='/management/landd/plans']")).click();

//	WebElement add = driver.findElement(By.xpath("//a[normalize-space()='ADD FLOOR']"));

		String value = "FLoor";
		int numberOfFloor = 6;
		for (int k = 1; k < numberOfFloor; k++) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//a[normalize-space()='ADD FLOOR']")));
			WebElement add = driver.findElement(By.xpath("//a[normalize-space()='ADD FLOOR']"));
			wait.until(ExpectedConditions.elementToBeClickable(add));
			add.click();
			WebElement ele = driver.findElement(By.id("table-name"));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			action.click(ele).build().perform();
			
			action.sendKeys(ele, value + " " + k).build().perform();
			WebElement numOfTables = driver.findElement(By.xpath("//input[@id='table_count']"));
			action.click(numOfTables).build().perform();
			numOfTables.clear();
			action.sendKeys(numOfTables, "5");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("table_prefix")));
			WebElement tableNamePrefix = driver.findElement(By.id("table_prefix"));
			action.click(tableNamePrefix).build().perform();
			tableNamePrefix.sendKeys("F" + k + "T");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='ADD']")));
			driver.findElement(By.xpath("//button[normalize-space()='ADD']")).click();

		}

		List<WebElement> total = driver
				.findElements(By.xpath("//div[@id='div__itemcard'] //div[@class='header'] //h2"));
		int tot = 0;
		System.out.println("Table Names on My Account Module : ");
		List<String> floor = new ArrayList<String>();
		for (int a = 0; a < total.size(); a++) {

			System.out.println("Name of the Floors : " + total.get(a).getText());
			floor.add(total.get(a).getText());
			tot++;

		}

		System.out.println("Total Number of Floors added: " + tot);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[normalize-space()='arrow_back']")));
		WebElement back = driver.findElement(By.xpath("//i[normalize-space()='arrow_back']"));
		back.click();
		List<WebElement> total1 = driver
				.findElements(By.xpath("//div[@id='div__itemcard'] //div[@class='header'] //h2"));
		driver.findElement(By.xpath("//a[@id='navbarDropdownMenuLink_1'] //i[@class='material-icons']")).click();
		driver.findElement(By.xpath("//a[@data-target='#admin_pin'] [1]")).click();

		List<WebElement> pin = driver
				.findElements(By.xpath("//div[@class='pin-container__body'] //button[@class='pin-button ripple']"));

		String[] p = { "7", "6", "0", "0" };

		List al = Arrays.asList(p);

		for (int l = 0; l < al.size(); l++) {
			for (int inner = 0; inner < pin.size(); inner++) {

				if (al.get(l).toString().contains(pin.get(inner).getText())) {
					pin.get(inner).click();
				}
			}
		}

		Thread.sleep(5000);
		driver.quit();
		
	}
	}


