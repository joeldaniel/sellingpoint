package com.mav.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mav.pom.pages.LaunchPage;
import com.mav.pom.pages.LoginPage;
import com.mav.pom.pages.session.LandingPage;
import com.mav.pom.pages.session.SellPage;
import com.mav.pom.util.Constants;
import com.mav.pom.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;
import com.mav.pom.testcases.base.BaseTest;

public class Publish_Item extends BaseTest {
	String testCaseName="Publish_Item";
	public String actualResult="";

	@Test(dataProvider="getData")
	public void addItem(Hashtable<String, String> data)
	{
		test=extent.startTest(testCaseName+" - "+data.get("Browser"),"Add an item into respective category");
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}

		test.log(LogStatus.INFO,"Starting Publish_Item");
		init(data.get("Browser"));
		
		if(driver ==null)
		{
			reportFailure("Browser NOT Launched");
		}
		
		test.log(LogStatus.INFO, data.get("Browser")+" Browser opened Successfully - ");

		LaunchPage launchpage=new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchpage);

		Object pageLauch=launchpage.gotoLoginPage();	
		if(pageLauch instanceof LoginPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("URL of an Application NOT Launched");
		}
		//test.log(LogStatus.PASS, "URL Launched Successfully");
		
		Object page=((LoginPage) pageLauch).doLogin(data.get("Username"), data.get("Password"));		
		
		String actualResult="";		
		if(page instanceof LandingPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("User NOT Logged in");
		}
		test.log(LogStatus.PASS, "Login Test Passed");
		
		SellPage sellPage=new SellPage(driver,test);
		PageFactory.initElements(driver, sellPage);
		sellPage.addItem(data.get("productName"),data.get("productDesc"),data.get("productPrice"));
	}


	@AfterMethod
	public void quit()
	{
		if(extent!=null)
		{
			extent.endTest(test);
			extent.flush();		
		}
		if(driver!=null)
		{
			driver.quit();
		}
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
