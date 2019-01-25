package com.mav.pom.testcases.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.mav.pom.util.Constants;
import com.mav.pom.util.ExtentManager;
import com.mav.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public WebDriver driver;
	public ExtentReports extent=ExtentManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls=new Xls_Reader(Constants.DATA_XLS_PATH);
	public DesiredCapabilities cap=null;


	public void init(String bType)
	{
		if(Constants.LOCAL_SINGLE_BROWSER_RUN)
		{
			if(bType.equals("Mozilla")){
				try{
					System.setProperty("webdriver.gecko.driver",Constants.FIREFOX_DRIVER_EXE);
					FirefoxProfile profile= new FirefoxProfile();
					driver= new FirefoxDriver(profile);
				}

				catch(Exception e)
				{
					reportFailure(bType+" Browser NOT Launched");
				}
			}
			else if(bType.equals("Chrome")){
				try
				{
					System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER_EXE);
					ChromeOptions coptions = new ChromeOptions();
					coptions.addArguments("--disable-extensions");   
					driver= new ChromeDriver(coptions);
				}
				catch(Exception e)
				{
					reportFailure(bType+" Browser NOT Launched");
				}
			}
			else if(bType.equals("IE")){

				try
				{
					/*System.setProperty("webdriver.ie.driver",Constants.IE_DRIVER_EXE);
					driver=new  InternetExplorerDriver();*/
					
					System.setProperty("webdriver.ie.driver",Constants.IE_DRIVER_EXE);
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					capabilities.setCapability("requireWindowFocus", true);
					driver= new InternetExplorerDriver(capabilities);
				}
				catch(Exception e)
				{
					reportFailure(bType+" Browser NOT Launched");
				}
			}

		}
		else if(!Constants.GRID_RUN)
		{
			if(bType.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			}else if(bType.equals("Chrome")){
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			}else if(bType.equals("IE")){
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability("requireWindowFocus", true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
			}try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				reportFailure(bType+" Browser NOT Launched");				
				e.printStackTrace();
			}
		}
		else{//(Constants.GRID_RUN)
			// grid						
			DesiredCapabilities cap=null;
			if(bType.equals("Mozilla")){

				System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_EXE_Linux);
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			}else if(bType.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE_Linux);
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			}			
			try {
				driver = new RemoteWebDriver(new URL("http://hub:4444/wd/hub"), cap);

			} catch (Exception e) {
				reportFailure(bType+" Browser NOT Launched");
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);		
	}

	public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath="./screenshots/"+screenshotFile;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(Constants.REPORTS_PATH+"/screenshots", screenshotFile);

		try {
			//FileUtils.copyFile(scrFile, new File(filePath));
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}
}
