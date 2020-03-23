package finalTraining;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XeroTestCases extends ReusableMethods {

	
	
	public void launchLoginPage() {

		createReport();

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get("https://www.xero.com/us/");

		

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	
		WebElement loginbt = ReusableMethods.driver
				.findElement(By.xpath("/html/body/div[4]/header/nav/div[2]/div/div[1]/div/div/ul/li[2]/a"));

		loginbt.click();

		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	/*
	 * name of the method: test01NavigateToXeroLogin() BriefDescription : Logging in
	 * with valid credentials Arguments : none 
	 * createdby : Venkata
	 * date :03/21/2020 LastModified Date:03/21/2020
	 */

	@Test
	public void test01NavigateToXeroLogin() {
		

		launchLoginPage ();
		logger = report.startTest("test01NavigateToXeroLogin");

		// enter valid email id and password
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavijb@gmail.com'");
		JS.executeScript("document.getElementById('password').value='Steps100@'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		logger.log(LogStatus.INFO, "Login details has been entered");

		// click on Login button to submit the details
		WebElement submitButn = ReusableMethods.driver.findElement(By.id("submitButton"));
		submitButn.click();

		logger.log(LogStatus.INFO, "Successfully Logged in with valid email and password");

		//closeBrowser();
		//closeReport();

	}

	/*
	 * name of the method: test02IncorrectPassword() BriefDescription : Logging in
	 * with invalid credentials Arguments : none 
	 * createdby : venkata 
	 * date :03/21/2020 LastModified Date:03/21/2020
	 */
	@Test
	public void test02IncorrectPassword() {

		launchLoginPage();
		 logger = report.startTest("test02IncorrectPassword");

		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavijb@gmail.com'");
		JS.executeScript("document.getElementById('password').value='Steps1000'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, " Login details has been entered ");

		// click on Login button to submit the details
		WebElement submitButn = ReusableMethods.driver.findElement(By.id("submitButton"));
		submitButn.click();

		//
		WebElement actualMessage = ReusableMethods.driver
				.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));

		logger.log(LogStatus.INFO, actualMessage.getText());

		String errorMessage = "Your email or password is incorrect";

		if (actualMessage.getText().equals(errorMessage)) {
			logger.log(LogStatus.INFO, "Test Case Passed");
		} else {
			logger.log(LogStatus.INFO, "Test Case Failed");
			fail("Incorrectpassword testcase failed");
		}

	

	}
	
	@AfterClass
	public void closeTests() {
		//closeBrowser();
		closeReport();
	}

}
