import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class InterviewTest {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://social.ndtv.com/static/Weather/report/#pfrom=home-ndtv_topsubnavigation");
		/*WebElement searchBox=driver.findElement(By.id("searchBox"));
		searchBox.sendKeys("Tirupati");
		WebElement city=driver.findElement(By.xpath("//input[@id='Tirupati']"));
		Actions act=new Actions(driver);
		act.moveToElement(city).click().perform();
		List<WebElement> tempRedTxt=driver.findElements(By.xpath("//span[@class='tempRedText']"));
		List<WebElement> tempWhiteTxt=driver.findElements(By.xpath("//span[@class='tempWhiteText']"));
		double temperaturecelcius;
		double temperaturefahrenheit;
		
		for(WebElement ele:tempRedTxt)
		{
			
			//System.out.println(ele.getText());
			String s=ele.getText().replaceAll("[^0-9.]", "");
			temperaturecelcius=Double.parseDouble(s.replace("?", ""));
			System.out.println("Temperature in Celcius = "+temperaturecelcius);
		}*/
		/*for(WebElement ele:tempWhiteTxt)
		{
			
			//System.out.println(ele.getText());
			String s=ele.getText().replaceAll("[^0-9.]", "");
			temperaturecelcius=Double.parseDouble(s.replace("?", ""));
			System.out.println("temperature in Fahrenheit = "+temperaturefahrenheit);
		}*/
		List<WebElement> selected_cities=driver.findElements(By.xpath("//div[@class='outerContainer']/div[2]"));
		//List<WebElement> temperature=driver.findElements(By.xpath("//div[@class='outerContainer']/div[1]/span[1]"));
		/*List<WebElement> tempRedTxt=driver.findElements(By.xpath("//span[@class='tempRedText']"));
		for(WebElement ele:selected_cities)
		{
				String cities=ele.getText();
				System.out.println(cities);
				for(WebElement temp:tempRedTxt)
				{
					if(cities.equals("Bengaluru"))
					{
					String temperature=temp.getText();
					System.out.println(temperature);
					}
					else
					{
						break;
					}
				}
		}
		for(int i=0;i<=selected_cities.size();i++)
		{
			
		}*/
		
		String path=".\\XLFiles\\weatherdetails.xlsx";
		ExcelLib xlutil=new ExcelLib(path);
		
		xlutil.setCellData("sheet1", 0, 0, "City");
		xlutil.setCellData("sheet1", 0, 1, "Temperature in Celcius");
		//xlutil.setCellData("sheet1", 0, 0, "Temperature in Fahrenheit");
		
		int options=driver.findElements(By.xpath("//input[@class='defaultChecked']")).size();
		for(int i=1;i<=options;i++)
		{
			String city=driver.findElement(By.xpath("(//div[@class='outerContainer']/div[2])["+i+"]")).getText();
			String temperature=driver.findElement(By.xpath("(//div[@class='temperatureContainer']/span[1])["+i+"]")).getText();
			
			
			xlutil.setCellData("sheet1", i, 0, city);
			xlutil.setCellData("sheet1", i, 1, temperature);
		}
		System.out.println("Webscraping is done......");
		driver.close();
	}
	
}
