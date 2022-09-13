import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Task {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://social.ndtv.com/static/Weather/report/#pfrom=home-ndtv_topsubnavigation");
		String cityName="Tirupati";
		WebElement searchBox=driver.findElement(By.id("searchBox"));
		searchBox.sendKeys(cityName);
		WebElement city=driver.findElement(By.xpath("//input[@id='Tirupati']"));
		Actions act=new Actions(driver);
		act.moveToElement(city).click().perform();
		
		double temp;
		String path=".\\XLFiles\\TaskPageweatherdetails.xlsx";
		ExcelLib xlutil=new ExcelLib(path);
		
		xlutil.setCellData("sheet1", 0, 0, "City");
		xlutil.setCellData("sheet1", 0, 1, "Temperature in Celcius");
		
		HashMap<String,Double> details=new HashMap<String,Double>();
		int options=driver.findElements(By.xpath("//div[@class='outerContainer']")).size();
		for(int i=1;i<=options;i++)
		{
			String location=driver.findElement(By.xpath("(//div[@class='outerContainer']/div[2])["+i+"]")).getText();
			String temperature=driver.findElement(By.xpath("(//div[@class='temperatureContainer']/span[1])["+i+"]")).getText();
			
			String s=temperature.replaceAll("[^0-9.]", "");
			temp=Double.parseDouble(s.replace("?", ""));
			details.put(location, temp);
			
			
			
			xlutil.setCellData("sheet1", i, 0, location);
			xlutil.setCellData("sheet1", i, 1, temperature);
		}
		//System.out.println(details.keySet());
		//System.out.println(details.values());
		for(Map.Entry entry:details.entrySet())
		{
			System.out.println(entry.getKey()+"     "+entry.getValue());
		}
		System.out.println(details.containsKey(cityName));
		System.out.println("Webscraping is done......");
		driver.close();
		
		/*String temp=driver.findElement(By.xpath("//div[text()='"+cityName+"']/preceding-sibling::div/span[@class='tempRedText']")).getText();
		double temperature;
		
		String s=temp.replaceAll("[^0-9.]", "");
		temperature=Double.parseDouble(s.replace("?", ""));
		System.out.println(temperature);
		driver.close();*/
	}

}
