import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import org.openqa.selenium.TakesScreenshot;  
import java.util.Date;  
import org.openqa.selenium.JavascriptExecutor;

public class ClassScreen {

	public static  void main(String[] args) throws IOException {
	
		WebDriver driver;
		for (int w = 0; w < 5; w++) {
			// set the date to the screenshots
			Date date=new Date();
			String updateDate = date.toString();
			String fixedDate = updateDate.replace(":", "-");
			//open the browser
			driver = new ChromeDriver();
			//using random method 
			Random rand = new Random();
			int indexSize = rand.nextInt(0, 6);
			//login process to the Website
			driver.get("https://www.saucedemo.com/inventory.html");
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			// Using a list, locate elements with a specific clasName and then activate the "add to cart" function by clicking on them.
			List<WebElement> addTocart = driver.findElements(By.className("btn_primary"));
			// addTocart.size()==indexSize
			for (int i = 0; i < indexSize; i++) {
				addTocart.get(i).click();
				
				//Cast driver object to JavascriptExecutor
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				//Scroll up by -500 pixels
				jsExecutor.executeScript("window.scrollBy(0,-500)");
				
				// take screenShot after each addTocart process
				TakesScreenshot scrShot = ((TakesScreenshot)driver);
				//File SrcFile = ((org.openqa.selenium.TakesScreenshot) scrShot).getScreenshotAs(OutputType.FILE);
				File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
				File DestFile = new File(".//" + fixedDate + ".png");
				FileUtils.copyFile(SrcFile, DestFile);

			}

		}

	}

}
