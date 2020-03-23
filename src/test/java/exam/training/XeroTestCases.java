package exam.training;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Venkata Bodepu
 * 
 *
 */
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
	 * with valid credentials Arguments : none createdby : Venkata Bodepu date
	 * :03/21/2020 LastModified Date:03/21/2020
	 */

	@Test
	@Parameters("browser")
	public void test01NavigateToXeroLogin() {

		launchLoginPage();
		logger = report.startTest("test01NavigateToXeroLogin");

		// enter valid email id and password
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavijb@gmail.com'");
		JS.executeScript("document.getElementById('password').value='venkata1'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		logger.log(LogStatus.INFO, "Login details has been entered");

		// click on Login button to submit the details
		WebElement submitButn = ReusableMethods.driver.findElement(By.id("submitButton"));
		submitButn.click();

		logger.log(LogStatus.INFO, "Successfully Logged in with valid email and password");

		// closeBrowser();
		// closeReport();

	}

	/*
	 * name of the method: test02IncorrectPassword() BriefDescription : Logging in
	 * with invalid password
	 *
	 * Arguments : none createdby : venkata date :03/21/2020 LastModified
	 * Date:03/21/2020
	 */
	@Test
	public void test01IncorrectPassword() {

		launchLoginPage();
		logger = report.startTest("test02IncorrectPassword");

		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavijb@gmail.com'");
		JS.executeScript("document.getElementById('password').value='invalid'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, " Login details has been entered ");

		// click on Login button to submit the details
		WebElement submitButn = ReusableMethods.driver.findElement(By.id("submitButton"));
		submitButn.click();

		//
		WebElement actualMessage = ReusableMethods.driver
				.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));
		actualMessage.getText();
		logger.log(LogStatus.INFO, actualMessage.getText());

		String errorMessage = "Your email or password is incorrect";

		if (actualMessage.getText().equals(errorMessage)) {
			logger.log(LogStatus.INFO, "Test Case Passed");
		} else {
			logger.log(LogStatus.INFO, "Test Case Failed");
			fail("Incorrectpassword testcase failed");
		}

	}

	@Test
	public void test01IncorrectEmail() {

		launchLoginPage();
		logger = report.startTest("test01IncorrectEmail");

		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavi@gmail.com'");
		JS.executeScript("document.getElementById('password').value='venkata1'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, " Incorrect Email has been entered ");

		// click on Login button to submit the details
		WebElement submitButn = ReusableMethods.driver.findElement(By.id("submitButton"));
		submitButn.click();

		ReusableMethods.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		WebElement actualMessage = ReusableMethods.driver
				.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));
		actualMessage.getText();
		logger.log(LogStatus.INFO, actualMessage.getText());

		String errorMessage = "Your email or password is incorrect";

		if (actualMessage.getText().equals(errorMessage)) {
			logger.log(LogStatus.INFO, "Test Case Passed");
		} else {
			logger.log(LogStatus.INFO, "Test Case Failed");
			fail("IncorrectEmail testcase failed");
		}
	}

	@Test
	public void test01ForgotPassword() {

		launchLoginPage();
		logger = report.startTest("test01forgotPassword");

		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("document.getElementById('email').value='vasavijb@gmail.com'");
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement forgotpassword = ReusableMethods.driver
				.findElement(By.xpath("//a[@class='forgot-password-advert']"));
		forgotpassword.click();

		WebElement forgotpasswordUsername = ReusableMethods.driver.findElement(By.xpath("//input[@id='UserName']"));

		forgotpasswordUsername.sendKeys("vasavijb@gmail.com");

		WebElement UsernameSend = ReusableMethods.driver.findElement(By.xpath("//span[@class='text']"));
		UsernameSend.click();

		WebElement actualResetMessage = ReusableMethods.driver
				.findElement(By.cssSelector("html.ext-strict body#login.x-page.ext-gecko.ext-gecko3 div.content div.inner div#contentTop.document.clear div.x-boxed.noBorder p"));
		actualResetMessage.getText();
		//logger.log(LogStatus.INFO, actualResetMessage.getText());

		String resetMessage = "A link to reset your password has been sent to:";

		if (actualResetMessage.getText().equals(resetMessage)) {
			logger.log(LogStatus.INFO, "Test Case Passed");
		} else {
			logger.log(LogStatus.INFO, "Test Case Failed");
			fail(" test01ForgotPassword testcase failed");

		}
	}
	
	@Test
	public void test02_A_SignUpToXDC() {
		
		
		createReport();

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get("https://www.xero.com/us/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		logger = report.startTest("test02SignUpToXDC");
		WebElement freeTrialButton = ReusableMethods.driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		freeTrialButton.click();
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement name = ReusableMethods.driver.findElement(By.xpath("//input[@name='FirstName']"));
		name.sendKeys("Vasavi");


		WebElement lastName = ReusableMethods.driver.findElement(By.xpath("//input[@name='LastName']" ));
		lastName.sendKeys("Bodepu");
		
		WebElement email = ReusableMethods.driver.findElement(By.xpath("//input[@name='EmailAddress']" ));
		email.sendKeys("vasavijb@gmail.com");
		
		
		WebElement phone = ReusableMethods.driver.findElement(By.name("PhoneNumber"));
		phone.sendKeys("2148097578");
		
		WebElement country = ReusableMethods.driver.findElement(By.name("LocationCode"));
		country.click();
//		if (country.isDisplayed()) {
//			System.out.println("Pass: " + "United States" + " is  selected");
//			country.click();
//		} else {
//
//			System.out.println("Fail:" + "United States" + " is not present.Please chk application");
//			
//		}
		ReusableMethods.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		//WebElement captcha = ReusableMethods.driver.findElement(By.id("rc-anchor-container"));

		//driver.switchTo().frame("rc-anchor-container");
		//captcha.click();
		
		WebElement TermsAccepted = ReusableMethods.driver.findElement(By.name("TermsAccepted"));
		TermsAccepted.click();
		
		WebElement getStartedBtn = ReusableMethods.driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
		getStartedBtn.click();
		System.out.println("Get Started is selected for test02_A_SignUpToXDC");

		
		
	}
	
	@Test
	public void test02_B_SignUpToXDC() {
		
		
		createReport();

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get("https://www.xero.com/us/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		logger = report.startTest("test02_B_SignUpToXDC");
		WebElement freeTrialButton = ReusableMethods.driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		freeTrialButton.click();
		ReusableMethods.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		
		WebElement getStartedBtn = ReusableMethods.driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
		getStartedBtn.click();
		System.out.println("Get Started is selected for test02_B_SignUpToXDC");
		test02_B_SignUpToXDC.log(LogStatus.PASS,”Test Passed”);
		
		
		
	
	
	}
	@Test
	public void test02_C_SignUpToXDC() {
		
		
		createReport();

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get("https://www.xero.com/us/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		logger = report.startTest("test02BSignUpToXDC");
		WebElement freeTrialButton = ReusableMethods.driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		freeTrialButton.click();
		ReusableMethods.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		
		WebElement termsOfUse = ReusableMethods.driver.findElement(By.xpath("//a[contains(text(),'terms')]"));
		termsOfUse.click();
		ReusableMethods.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		WebElement privacyPolicy = ReusableMethods.driver.findElement(By.xpath("//a[contains(text(),'privacy')]"));
		privacyPolicy.click();
		//a[contains(text(),'privacy')]
		
	}
	
	@AfterClass
	public void closeTests() {
		// closeBrowser();
		closeReport();
	}

}
