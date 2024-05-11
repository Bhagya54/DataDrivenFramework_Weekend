package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.DbManager;
import utility.ExcelReader;
import utility.MonitoringMail;

public class BaseTest {

	/*
	 * WebDriver
	 * Apache POI
	 * Log4j
	 * Extent Reports
	 * Listeners
	 * Database
	 * Java Mail Api
	 * Properties
	 * TestNG
	 * Implicit and Explicit
	 *  
	 *  
	 *  Sequential Approach
	 *  
	 *  Open Browser
	 *  Testcase 1>Test case 2... Testcase N
	 *  close browser
	 *  
	 *  End to End Approach
	 *  open brwser
	 *  Testcase 1
	 *  Close browser
	 *  
	 *  open brwser
	 *  Testcase 2
	 *  Close browser
	 */
	
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	
	
	@BeforeSuite
	public void setUp()  {
		
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("Test Execution started");
		
		try {
			fis = new FileInputStream("./src/test/resources/properties/Config.properties");
			config.load(fis);
			log.info("Config file has been loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			or.load(fis);
			log.info("OR file has been loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			log.info("Chrome browser has launched");
		}
		
		else if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			log.info("Firefox browser has launched");
		}
		
		try {
			DbManager.setMysqlDbConnection();
			log.info("Database connection established");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		wait=new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
		driver.get(config.getProperty("testsiteurl"));
		
	}
	
	//keyword driven
	
	public static boolean isElementPresent(String keyword) {
		try {
		if (keyword.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(keyword)));
		} else if (keyword.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(keyword)));
		} else if (keyword.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(keyword)));
		} else if (keyword.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(keyword)));
		}
		log.info("Element Present with keyword: " + keyword);
		return true;
		
		}
		catch (Exception e) {
			return false;
		}
		
	}
		public static void type(String keyword, String value) {
			if (keyword.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(keyword))).sendKeys(value);
			} else if (keyword.endsWith("_NAME")) {
				driver.findElement(By.name(or.getProperty(keyword))).sendKeys(value);
			} else if (keyword.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(keyword))).sendKeys(value);
			} else if (keyword.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(keyword))).sendKeys(value);
			}
			log.info("Typed on keyword: " + keyword + " and entered value: " +value);
		}

		public static void click(String keyword) {
			if (keyword.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(keyword))).click();
				;
			} else if (keyword.endsWith("_NAME")) {
				driver.findElement(By.name(or.getProperty(keyword))).click();
			} else if (keyword.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(keyword))).click();
			} else if (keyword.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(keyword))).click();
			}
			log.info("Clicked on keyword: " + keyword);
		}
	
	@AfterSuite
	public void tearDown() {
		
		driver.quit();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
