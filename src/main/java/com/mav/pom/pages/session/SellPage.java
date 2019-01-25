package com.mav.pom.pages.session;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mav.pom.base.BasePage;
import com.mav.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SellPage extends BasePage{

	@FindBy(xpath=Constants.SELL_SELLTAB)
	public WebElement sellTab;
	
	@FindBy(xpath=Constants.SELL_PRODUCTNAME)
	public WebElement productName;

	@FindBy(xpath=Constants.SELL_DESCRIPTION)
	public WebElement description;

	@FindBy(xpath=Constants.SELL_PRICE)
	public WebElement price;
	
	@FindBy(xpath=Constants.SELL_SELECTCATEGORY)
	public WebElement selectCategory;

	@FindBy(xpath=Constants.SELL_CATEGORY)
	public WebElement category;
	
	@FindBy(xpath=Constants.SELL_PUBLISHBUTTON)
	public WebElement publish;
	
	
	
	public SellPage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}

	public void addItem(String prodName, String prodDesc, String prodPrice) {
		test.log(LogStatus.INFO, "Adding an Item ..");
		sellTab.click();
		test.log(LogStatus.INFO, "Clicked on SELL Tab");
		
		productName.sendKeys(prodName);
		test.log(LogStatus.INFO, "Entered Product Name as : "+prodName);
		
		description.sendKeys(prodDesc);
		test.log(LogStatus.INFO, "Entered Product Description as : "+prodDesc);
		
		price.sendKeys(prodPrice);
		test.log(LogStatus.INFO, "Entered Product Price : "+prodPrice);
		
		selectElement(selectCategory,"Books");
		publish.click();
		test.log(LogStatus.INFO, "Clicked on Publish button");
		
		boolean prodSuccessMessage=isElementPresent(Constants.SELLPAGE_SUCCESSMESSAGE);
		if(prodSuccessMessage)	
		{
			test.log(LogStatus.PASS, "Product added Successfully");
		}
		else
		{
			test.log(LogStatus.INFO, "Product NOT added");
			reportFailure("Product Not added");
		}
		
		
	}
}
