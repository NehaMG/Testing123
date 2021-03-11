package com.automationpractice.conga;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.S1_ReadPropertyFile;

public class Scenario1 
{
	WebDriver driver;
	public S1_ReadPropertyFile prop;
	
	@BeforeTest
	public void launchBrowser() throws IOException {
		
		prop = new S1_ReadPropertyFile();
		
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get(prop.getProperties("url"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void shopping()
	{
		int quantity = 2;
		
		S1_LoginPage login = new S1_LoginPage(driver);
		S1_HomePage home = new S1_HomePage(driver);
		S1_ProductPage product = new S1_ProductPage(driver);
		S1_ProceedToCheckoutPage checkout = new S1_ProceedToCheckoutPage(driver);
		
		//Login to AUT
		login.login(prop.getProperties("username"), prop.getProperties("password"));
		
		//Select Category
		home.selectCategory();
		
		//Add Product to cart
		product.addProductToCart(quantity);
		
		//Verify product successfully added to cart
		Assert.assertEquals(checkout.verifySuccessMsg(), "Product successfully added to your shopping cart");
		Assert.assertEquals(checkout.verifyProductName(), "Faded Short Sleeve T-shirts");
		Assert.assertEquals(checkout.verifyProductDetails(), "Orange, S");
		Assert.assertEquals(checkout.verifyQuantity(), quantity);
		Assert.assertEquals(checkout.verifyPrice(), "$" + 16.51*quantity);
		
		//Proceed to checkout
		checkout.proceedToCheckout();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
	}
}
