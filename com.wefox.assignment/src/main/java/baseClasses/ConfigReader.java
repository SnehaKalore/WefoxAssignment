package baseClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties properties;
	private final String propertyFilePath= "Configs//config.properties";
			
	public ConfigReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getChromeDriverPath(){
		String chromeDriverPath = properties.getProperty("chromeDriverPath");
		if(chromeDriverPath!= null) return chromeDriverPath;
		else throw new RuntimeException("chromeDriverPath not specified in the Configuration.properties file.");		
	}
	
	public String getFirefoxDriverPath(){
		String firefoxDriverPath = properties.getProperty("firefoxDriverPath");
		if(firefoxDriverPath!= null) return firefoxDriverPath;
		else throw new RuntimeException("firefoxDriverPath not specified in the Configuration.properties file.");		
	}
	
	public String getUrl(){
		String url = properties.getProperty("url");
		if(url!= null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getReportPath() {		
		String reportPath = properties.getProperty("reportpath");
		if(reportPath != null) return reportPath;
		else throw new RuntimeException("reportPath not specified in the Configuration.properties file.");		
	}
}
