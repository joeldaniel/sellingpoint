package com.mav.pom.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.mav.pom.pages.common.TopMenu;
import com.mav.pom.util.Constants;

public class BasePage {

	public 	WebDriver driver;
	public TopMenu menu;
	public ExtentTest test;

	public BasePage(){}

	public BasePage(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
		menu=new TopMenu(driver, test);
		PageFactory.initElements(driver, menu);

	}

	public String verifyTitle(String expTitle)
	{
		return "";
	}

	public String verifyText(String locator, String expText)
	{
		return "";
	}

	
	public TopMenu getMenu()
	{
		return menu;
	}

	public boolean isElementPresent(String locator){
		test.log(LogStatus.INFO, "Trying to find element -> "+locator);
		int s = driver.findElements(By.xpath(locator)).size();
		if(s==0){
			//test.log(LogStatus.INFO, "Element not found");
			reportFailure("Element not found");
			return false;
		}
		else{
			test.log(LogStatus.INFO, "Element found");
			return true;
		}
			
	}
	

	public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath="./screenshots/"+screenshotFile;
		// store screenshot in that file
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
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	
	public void selectElement(WebElement selectCategory,String value)
	{
		WebElement mySelectElement = selectCategory;
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(value);
		test.log(LogStatus.INFO,"Selected "+value+" from DropDown" );
	}
}
