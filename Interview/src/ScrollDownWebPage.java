import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ScrollDownWebPage {
	public static void main(String[] args) throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		WebElement flag=driver.findElement(By.xpath("(//span[@class='vjs-icon-placeholder'])[2]"));
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", flag);
		Thread.sleep(5000);
		Actions act=new Actions(driver);
		act.moveToElement(flag).click().perform();
		Thread.sleep(5000);
		driver.close();
	
	}

}


