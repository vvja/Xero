package finalTraining;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author Venkata Bodepu
 * 
 *
 */
public class ReusableMethods {
	static ExtentTest logger;
	static ExtentReports report;

	public static WebDriver driver;

	/*
	 * name of the Method: CreateReport BriedDescriotion : This will create a empty
	 * Extent Report. Arguments : No Arguments Created By : Automation Team created
	 * Date : 14/02/2020 Last Modified : 14/02/2020
	 */
	public static void createReport() {
		String fileName = new SimpleDateFormat("'XeroReport_'YYYYMMddHHmmss'.html'").format(new Date());

		String path = "C:\\ExtentReport\\" + fileName;
		
		System.out.println("path : " + path);
		report = new ExtentReports(path);

	}

	

	public static void closeBrowser() {
		driver.quit();
	}

	public static void closeReport() {
		report.flush();
	}

}