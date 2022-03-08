package baseClasses;

import java.io.File;
import java.util.Date;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public ExtentReports extent;
	public String screenshotFolderPath;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest logger;
	
	@BeforeTest
	public void getInstance()
	{
		ConfigReader config = new ConfigReader();
		if(extent==null)
		{
			String reportPath = config.getReportPath();
			String filename="Report.html";
			Date d = new Date();
			String foldername = d.toString().replace(":", "_").replace(" ","_");			
			new File(reportPath+foldername+"\\screenshots").mkdirs();
			reportPath = reportPath+foldername+"\\";
			screenshotFolderPath = reportPath+"screenshots";
			System.setProperty("screenshotFolderPath", screenshotFolderPath);
			htmlReporter = new ExtentHtmlReporter(reportPath+filename);
	    	// Create an object of Extent Reports
			extent = new ExtentReports();  
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "Sneha Kalore");
		    htmlReporter.config().setDocumentTitle("Wefox Assignment"); 
			htmlReporter.config().setReportName("Automation Test Report"); 
		    htmlReporter.config().setTheme(Theme.STANDARD);						
		}		
	}
}
