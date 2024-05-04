package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class TestBase {
	static WebDriver driver;
	static FileInputStream fis;
	
	static Properties or1;
	static Logger log = Logger.getLogger(TestBase.class);

	@BeforeSuite
	public void setUp() throws IOException {
		fis = new FileInputStream("./src/test/resources/Properties/log4j.properties");
		PropertyConfigurator.configure(fis);

		/*
		 * config1 = new Properties(); fis = new
		 * FileInputStream("./src/test/resources/Properties/Config.properties");
		 * config1.load(fis); log.info("Config1 file has loaded");
		 */

		or1 = new Properties();
		fis = new FileInputStream("./src/test/resources/Properties/OR.properties");
		or1.load(fis);
		log.info("OR1 file has loaded");
	}
	
	@Parameters({"browser","siteurl"})
	@BeforeMethod
	public void launchBrowser(String browserName,String url) {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			log.info("Chrome browser has launched");
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			log.info("Firefox browser has launched");
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
			log.info("Edge browser has launched");
		}
		driver.manage().window().maximize();
		log.info("browser is maximized");
		driver.get(url);

	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
	

	public static void type(String keyword, String value) {
		if (keyword.endsWith("_ID")) {
			driver.findElement(By.id(or1.getProperty(keyword))).sendKeys(value);
		} else if (keyword.endsWith("_NAME")) {
			driver.findElement(By.name(or1.getProperty(keyword))).sendKeys(value);
		} else if (keyword.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or1.getProperty(keyword))).sendKeys(value);
		} else if (keyword.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or1.getProperty(keyword))).sendKeys(value);
		}
		log.info("Typed:" + keyword + "and" + "Entered:" + value);
	}

	public static void click(String keyword) {
		if (keyword.endsWith("_ID")) {
			driver.findElement(By.id(or1.getProperty(keyword))).click();
	}
		else if (keyword.endsWith("_NAME")) {
			driver.findElement(By.name(or1.getProperty(keyword))).click();
	}
		else if (keyword.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or1.getProperty(keyword))).click();
	}
		else if (keyword.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or1.getProperty(keyword))).click();
	}
		log.info("Clicked:"+keyword);
}
}