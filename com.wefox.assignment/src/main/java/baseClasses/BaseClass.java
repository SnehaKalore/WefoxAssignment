package baseClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageObjects.ArticlesPageObjects;

public class BaseClass extends ExtentManager{

	
	static ConfigReader configReader = new ConfigReader();
	static ArticlesPageObjects ap = new ArticlesPageObjects();
	static Logger logger4j = LogManager.getLogger(BaseClass.class.getName());
		
	public BaseClass() {
		// TODO Auto-generated constructor stub
		DOMConfigurator.configure("log4j.xml");
	}
	
	public static void waitForPageLoad(final WebDriver driver) {
		ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	            try {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            } catch (Exception e) {
	                return Boolean.FALSE;
	            }
	        }
	    };
	    WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(javascriptDone);
	}

	public static void waitForPageLoadLong(final WebDriver driver) {
		ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	            try {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            } catch (Exception e) {
	                return Boolean.FALSE;
	            }
	        }
	    };
	    WebDriverWait wait = new WebDriverWait(driver, 300);
	    wait.until(javascriptDone);
	}
	
	public static List<WebElement> getObjects(String xpath, WebDriver driver)
	{
		List<WebElement> e = driver.findElements(By.xpath(xpath));
		return e;
	}
	
	public static WebElement getObjectByXpath(String xpathval, WebDriver driver)
	{
		WebElement e=null;
		try
		{
		   e = driver.findElement(By.xpath(xpathval));
		   WebDriverWait wait = new WebDriverWait(driver,10);
		   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathval)));
		}		
		catch(Throwable t)
		{
			logger4j.info("Not able to find" + xpathval);
			return null;
		}
		return(e);
	}
	
	public WebElement getObjectByCSSSelector(String xpathval, WebDriver driver)
	{
		WebElement e=null;
		try
		{
			e = driver.findElement(By.cssSelector(xpathval));
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(xpathval)));
		}
		catch(Throwable t)
		{
			logger4j.info("Not able to find" + xpathval);
			return null;
		}
		return(e);
	}	
	
	public WebElement getObjectById(String xpathval, WebDriver driver)
	{
		WebElement e=null;
		try
		{
			e = driver.findElement(By.id(xpathval));
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(xpathval)));
		}
		catch(Throwable t)
		{
			logger4j.info("Not able to find" + xpathval);
			return null;
		}
		return(e);
	}
	
	public WebElement getObjectByName(String xpathval, WebDriver driver)
	{
		WebElement e=null;
		try
		{
			e = driver.findElement(By.name(xpathval));
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(xpathval)));
		}			
		catch(Throwable t)
		{
			logger4j.info("Not able to find" + xpathval);
			return null;
		}
		return(e);		
	}
	
	public static String getDynamicXpath(String xpath, char toReplace)
	{
		return xpath.replace('$', toReplace);
	}
	
	public String getDynamicXpath(String xpath, String toReplace)
	{
		return xpath.replaceAll("$", toReplace);
	}
		
	public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("screenshotFolderPath")+"\\"+screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@AfterMethod
	public void getResults(ITestResult result, WebDriver driver) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method. 
			//String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			String screenshotPath = takeScreenshot(driver, result.getName());
			//To add it in the extent report 
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}		
	}	
}
