package com.mav.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mav.pom.base.BasePage;
import com.mav.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LaunchPage extends BasePage {	
	

	public LaunchPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}

	public Object gotoLoginPage()
	{
		test.log(LogStatus.INFO, "Go to URL "+Constants.getEnvDetails().get("url"));
		driver.get(Constants.getEnvDetails().get("url"));
		/*LoginPage loginPage=new LoginPage(driver,test);
		PageFactory.initElements(driver, loginPage);*/
		
		boolean UrlLaunchSuccess=isElementPresent(Constants.LOGIN_USERNAME);
		if(UrlLaunchSuccess)	
		{	
			test.log(LogStatus.INFO, "URL Launch SUCCESSFUL");
			LoginPage loginPage=new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;		
		}
		else
		{
			//test.log(LogStatus.INFO, "URL Launch NOT Successfull");
			LaunchPage lPage=new LaunchPage(driver, test);
			PageFactory.initElements(driver, lPage);
			reportFailure("URL Launch UNSUCCESSFUL");
			return lPage;
			
		}
		
	}

}
