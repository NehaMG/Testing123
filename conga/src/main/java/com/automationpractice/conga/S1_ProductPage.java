package com.automationpractice.conga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S1_ProductPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public S1_ProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,5);
	}
	
	@FindBy(css="div.product-image-container>a[title='Faded Short Sleeve T-shirts']")
	WebElement fadedShirt;
	
	@FindBy(css="input#quantity_wanted")
	WebElement addQuantity;
	
	@FindBy(css="span#our_price_display")
	WebElement capturePrice;
	
	@FindBy(css="button[name='Submit']")
	WebElement addToCart;
	
	@FindBy(css="iframe.fancybox-iframe")
	WebElement addToCartFrame;
	
	@FindBy(css="div.layer_cart_product > h2")
	WebElement successMsg;
	
	public void addProductToCart(int qty)
	{
		fadedShirt.click();
		
		driver.switchTo().frame(addToCartFrame);
		wait.until(ExpectedConditions.visibilityOf(addToCart));
		
		addQuantity.clear();
		addQuantity.sendKeys(String.valueOf(qty));
		
		addToCart.click();
		
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOf(successMsg));
	}
}
