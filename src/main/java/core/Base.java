package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static WebDriver driver;
	// properties callss belongs to java.util package
	// properties is a sub class of Hashtable
	// properties class can be used to stor our data
	public static Properties properties;
	// it is reliable fast and flexable framework (APIS)
//		logger: it is responsible for capturing logging informatiom
//		appenders: it is responsible for publishing logging informatiom to  different destination
//		layouts: it is responsible for formating logging informatiom
	public static Logger logger;
//		we will store the path to the property.properties file
	public static String propertypath = ".\\src\\test\\resources\\input\\property.properties";
//		we will store the path to log4j.properties file
	public static String log4jpath = ".\\src\\test\\resources\\input\\log4j.properties";

	public Base() {
		try {
//				read the property.properties file we can write the method to read the data from property.properties
//				file to make sure that we handle both Exceptions we surrounded with try  catch method block
			BufferedReader reader = new BufferedReader(new FileReader(propertypath));
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger = Logger.getLogger("logger_file");
		PropertyConfigurator.configure(log4jpath);
	}

//			we will write a method to get url from property.properties file
	public static String getUrl() {
		String url = properties.getProperty("url");
		return url;
	}

//		we will write  a method to get the browser name from property.properties file
	public static String getBrowser() {
		String browser = properties.getProperty("browser");
		return browser;
	}

//		we will write a method to launch the browser
	public static void openBrowser() {
		driver.get(getUrl());
	}

//		we will close one/ all of the browser opened by selenium
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	public static void browser() {
		String browserName = getBrowser();
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

}
