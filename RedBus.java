package redBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedBus {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 0);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.redbus.in/");
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElementById("src").clear();
		driver.findElementById("src").sendKeys("Chennai (All Locations)",Keys.TAB);
		Thread.sleep(3000);
		driver.findElementByXPath("//*[text()='Chennai (All Locations)']").click();
		driver.findElementById("dest").clear();
		driver.findElementById("dest").sendKeys("Bangalore (All Locations)",Keys.TAB);
		Thread.sleep(3000);
		driver.findElementByXPath("//*[text()='Bangalore (All Locations)']").click();
		Thread.sleep(3000);
		WebElement onward = driver.findElementById("onward_cal");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", onward);

		Date date = new Date(); // Get the current date
		DateFormat sdf = new SimpleDateFormat("dd"); //Get only the date (and not month, year, time etc)
		String today = sdf.format(date); // Get today's date
		System.out.println("today"+today);
		driver.findElementByXPath("(//table[@class='rb-monthTable first last'])[2]//td[text()='"+today+"']").click();
		driver.findElementById("search_btn").click();
		driver.findElementByXPath("//label[@for='dtBefore 6 am']").click();
		driver.findElementByXPath("(//label[@for='bt_SLEEPER'])[1]").click();
		driver.findElementByXPath("//a[text()='Seats Available']").click();
		String text = driver.findElementByXPath("//div[@class='seat-left m-top-30']").getText();
		System.out.println("SEATS"+text);
		driver.findElementByXPath("(//div[text()='View Seats'])[1]").click();
		
			
	
		
	}

}
