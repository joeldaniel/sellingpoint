package com.mav.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mav.pom.base.BasePage;
import com.mav.pom.pages.session.LandingPage;
import com.mav.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BasePage {

	@FindBy(xpath=Constants.LOGIN_USERNAME)
	public WebElement un;

	@FindBy(xpath=Constants.LOGIN_PASSWORD)
	public WebElement pwd;

	@FindBy(xpath=Constants.LOGIN_BUTTON)
	public WebElement loginButton;


	public LoginPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}

	public Object doLogin(String Un,String Pwd)
	{
		test.log(LogStatus.INFO, "Logging in...");
		un.sendKeys(Un);
		pwd.sendKeys(Pwd);
		loginButton.click();
		boolean loginSuccess=isElementPresent(Constants.LAUNCHPAGE_LINK);
		if(loginSuccess)	
		{
			test.log(LogStatus.INFO, "Login Success");
			LandingPage landingPage=new LandingPage(driver, test);
			PageFactory.initElements(driver, landingPage);
			return landingPage;
		}
		else
		{
			test.log(LogStatus.INFO, "Login Unsuccessfull");
			LoginPage loginPage=new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}

}
