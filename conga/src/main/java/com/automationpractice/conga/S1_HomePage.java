package com.automationpractice.conga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class S1_HomePage {

	WebDriver driver;
	
	public S1_HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="ul.sf-menu>li>a[title='T-shirts']")
	WebElement tShirtMenu;
	
	public void selectCategory()
	{	
		tShirtMenu.click();
	}
	
}
