package com.wefox.assignment;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import baseClasses.BaseClass;
import baseClasses.ConfigReader;
import baseClasses.ExtentManager;
import pageObjects.ArticlesPageObjects;

public class ArticalAddToCartTest extends ExtentManager{
  
  static ArticlesPageObjects ap = new ArticlesPageObjects();
  Logger logger4j = LogManager.getLogger(ArticalAddToCartTest.class.getName());
  static ConfigReader configReader = new ConfigReader();
  public static WebDriver driver = null;
  
  @Parameters("browser")
  @BeforeMethod
  public static void navigateToPage(String browser)
  {
		if(browser.equalsIgnoreCase("CH")) 
		{
			System.setProperty("webdriver.chrome.driver", configReader.getChromeDriverPath());
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("FF"))
		{
			System.setProperty("webdriver.gecko.driver", configReader.getFirefoxDriverPath());
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(configReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().to(configReader.getUrl());
		BaseClass.waitForPageLoadLong(driver);
		if(BaseClass.getObjectByXpath(ap.getContinueBtn_xpath(), driver).isDisplayed())
			BaseClass.getObjectByXpath(ap.getContinueBtn_xpath(), driver).click();
		if(BaseClass.getObjectByXpath(ap.getCookieAcceptBtn(), driver).isDisplayed())
			BaseClass.getObjectByXpath(ap.getCookieAcceptBtn(), driver).click();
  }
  
  @DataProvider (name = "productNames")
  public Object[][] dpMethod(){
	 return new Object[][] {{"Diversión Oceánica Creativa"}};
  }  
  
  public ArticalAddToCartTest() {
	// TODO Auto-generated constructor stub
	  super();
  }
  
  @Test(dataProvider = "productNames")
  public void verifyAddCartFunctionality(String productName) 
  {
	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();  
	logger = extent.createTest(methodName);
	SoftAssert softAssert = new SoftAssert();
	Actions actions = new Actions(driver);
	BaseClass.waitForPageLoad(driver);
	BaseClass.getObjectByXpath(ap.getToBuyBtn_xpath(), driver).click();
	BaseClass.getObjectByXpath(ap.getAgesBtn_xpath(), driver).click();
	BaseClass.getObjectByXpath(ap.getAgeRange_xpath(), driver).click();
	BaseClass.waitForPageLoad(driver);
	actions.moveToElement(BaseClass.getObjectByXpath(ap.getPriceRangeChkBox2_xpath(), driver));
	actions.perform();
	BaseClass.getObjectByXpath(ap.getPriceRangeChkBox2_xpath(), driver).click();
	BaseClass.waitForPageLoad(driver);
	actions.moveToElement(BaseClass.getObjectByXpath(ap.getShowAllProductsBtn_xpath(), driver));
	actions.perform();
	BaseClass.getObjectByXpath(ap.getShowAllProductsBtn_xpath(), driver).click();
	int i=0;
	while(BaseClass.getObjectByXpath(ap.getBackToTop_xpath(), driver).isDisplayed() && i<45)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,300)");
        BaseClass.waitForPageLoadLong(driver);
        i++;
	}
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", BaseClass.getObjectByXpath(ap.getBackToTop_xpath(), driver));
	int productCount = BaseClass.getObjects(ap.getTotalNoOfDataLeaf_xpathList(), driver).size();
	softAssert.assertTrue(BaseClass.getObjectByXpath(ap.getTotalNoOfProduct_xpath(), driver).getText().contains(String.valueOf(productCount)), "Total number of products matches with number of products displayed");
	BaseClass.waitForPageLoad(driver);
	HashMap<String, String> expectedMap = new HashMap<String, String>();
	expectedMap.put("ProductName", BaseClass.getObjectByXpath(BaseClass.getDynamicXpath(ap.getFirstProductName_xpath(), '1'), driver).getText());
	expectedMap.put("ProductPrice", BaseClass.getObjectByXpath(BaseClass.getDynamicXpath(ap.getFirstProductPrice_xpath(), '1'), driver).getText());
	expectedMap.put("ProductQuantity", "Uds. 1");
	//getObjectByXpath(getDynamicXpath(ap.getAddToCartBtn_xpath(), productName), driver).click();
	BaseClass.getObjectByXpath(ap.getAddToCartBtn_xpath(), driver).click();
	BaseClass.getObjectByXpath(ap.getViewMyBagBtn_xpath(), driver).click();
	BaseClass.waitForPageLoad(driver);	
	BaseClass.getObjectByXpath(ap.getCheckoutBtn_xpath(), driver).click();
	HashMap<String, String> actualMap = new HashMap<String, String>();
	actualMap.put("ProductName", BaseClass.getObjectByXpath(ap.getProductName_xpath(), driver).getText());
	actualMap.put("ProductPrice", BaseClass.getObjectByXpath(ap.getProductPrice_xpath(), driver).getText());
	actualMap.put("ProductQuantity", BaseClass.getObjectByXpath(ap.getProductQuantity_xpath(), driver).getText());
	softAssert.assertEquals(expectedMap, actualMap, "Product Details Matching on payment page");
	double additionalCharges = Double.parseDouble(BaseClass.getObjectByXpath(ap.getAdditionalTax_xpath(), driver).getText().replaceAll(",", ".").replaceAll(" €", ""));
	double actualPrice = Double.parseDouble(BaseClass.getObjectByXpath(ap.getProductPrice_xpath(), driver).getText().replaceAll(",", ".").replaceAll(" €", "").replaceAll("Price", ""));
	double totalCharge = additionalCharges + actualPrice;
	softAssert.assertTrue(BaseClass.getObjectByXpath(ap.getTotalAmount_xpath(), driver).getText().contains(String.valueOf(Math.round(totalCharge * 100.0) / 100.0).replace('.',',')), "Expected total charges matches with actual");
	softAssert.assertAll();
  }
  
  	@AfterTest
	public void endTest()
	{
		extent.flush();
		driver.quit();
	}
}
