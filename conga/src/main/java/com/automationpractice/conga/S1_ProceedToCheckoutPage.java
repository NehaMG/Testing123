package com.automationpractice.conga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class S1_ProceedToCheckoutPage {

	WebDriver driver;

	public S1_ProceedToCheckoutPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="a[title='Proceed to checkout']")
	WebElement proceedToCheckout;

	@FindBy(css="div.layer_cart_product > h2")
	WebElement successMsg;

	@FindBy(css="span.product-name")
	WebElement productName;

	@FindBy(css="span#layer_cart_product_attributes")
	WebElement productDetails;

	@FindBy(css="span#layer_cart_product_quantity")
	WebElement productQuantity;

	@FindBy(css="span#layer_cart_product_price")
	WebElement totalPrice;

	public void proceedToCheckout()
	{
		proceedToCheckout.click();
	}

	public String verifySuccessMsg()
	{
		System.out.println("Success message : " + successMsg.getText());
		return successMsg.getText();
	}

	public String verifyProductName()
	{
		System.out.println("Product Name : " + productName.getText());
		return productName.getText();
	}

	public String verifyProductDetails()
	{
		System.out.println("Product Detail : " + productDetails.getText());
		return productDetails.getText();
	}

	public int verifyQuantity()
	{
		System.out.println("Quantity : " + productQuantity.getText());
		return Integer.parseInt(productQuantity.getText());
	}

	public String verifyPrice()
	{
		System.out.println("Total Price : " + totalPrice.getText());
		return totalPrice.getText();
	}
}
