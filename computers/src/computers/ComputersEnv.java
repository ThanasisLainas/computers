package computers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class ComputersEnv {
	private WebDriver driver;

	@BeforeSuite
	public void initDriver() throws Exception {
		System.out.println("You are testing in firefox");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void Computer() throws InterruptedException {
		// Add new Computer and check the message
		driver.get("http://computer-database.herokuapp.com/computers");
		if(driver.findElements(By.id("main")).size() != 0){
			System.out.println("Element is Present");
			}else{
			System.out.println("Element is Absent");
			}
		driver.findElement(By.id("add")).click();;
		assert driver.findElement(By.xpath(".//*[@id='main']/h1")).isDisplayed();
		driver.findElement(By.id("name")).sendKeys("AAtlas");
		driver.findElement(By.id("introduced")).sendKeys("2016-12-12");
		driver.findElement(By.id("discontinued")).sendKeys("2018-12-12");
		Select company = new Select(driver.findElement(By.id("company")));
		company.selectByValue("3");		
		driver.findElement(By.xpath(".//*[@id='main']/form/div/input")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
			System.out.println("Computer has been created");
		}
		else{
			System.out.println("Computer has not been created");
		}
	}
	
	@Test
	public void Computer2() throws InterruptedException {
		// Search for the computer, update that computer and check the message
		driver.get("http://computer-database.herokuapp.com/computers");
		if(driver.findElements(By.id("main")).size() != 0){
			System.out.println("Element is Present");
			}else{
			System.out.println("Element is Absent");
			}
		driver.findElement(By.id("searchbox")).sendKeys("AAtlas");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr/td[1]/a")).click();
		assert driver.findElement(By.xpath(".//*[@id='main']/h1")).isDisplayed();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("AAtlas1");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2001-01-01");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2021-01-01");
		Select company = new Select(driver.findElement(By.id("company")));
		company.selectByValue("4");
		driver.findElement(By.xpath(".//*[@id='main']/form[1]/div/input")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
			System.out.println("Computer has been updated");
		}
		else{
			System.out.println("Computer has not been updated");
		}
	}
	
	@Test
	public void Computer3() throws InterruptedException {
		// Search for the computer, delete that computer and
		// search for that computer again
		driver.get("http://computer-database.herokuapp.com/computers");
		if(driver.findElements(By.id("main")).size() != 0){
			System.out.println("Element is Present");
			}else{
			System.out.println("Element is Absent");
			}
		driver.findElement(By.id("searchbox")).sendKeys("AAtlas1");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr/td[1]/a")).click();
		assert driver.findElement(By.xpath(".//*[@id='main']/h1")).isDisplayed();
		driver.findElement(By.xpath(".//*[@id='main']/form[2]/input")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
			System.out.println("Computer has been deleted");
		}
		else{
			System.out.println("Computer has not been deleted");
		}
		driver.findElement(By.id("searchbox")).sendKeys("AAtlas1");
		if (driver.findElement(By.xpath(".//*[@id='main']/div[2]")).isDisplayed()){
			System.out.println("Nothing to display");
		}
		else{
			System.out.println("Error: No computers should be displayed");
		}
		Thread.sleep(5000);
	}
	
	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
}