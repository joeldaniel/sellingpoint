package com.mav.pom.pages.session;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mav.pom.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPage extends BasePage{
	
	public LandingPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}
	
	public SellPage gotoSellPage()
	{
		test.log(LogStatus.INFO, "Going to Sell Page");
		SellPage sellPage=new SellPage(driver,test);
		PageFactory.initElements(driver, sellPage);
		return sellPage;	
	}
}
