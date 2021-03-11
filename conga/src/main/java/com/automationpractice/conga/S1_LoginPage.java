package com.automationpractice.conga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class S1_LoginPage 
{
	WebDriver driver;
	
	public S1_LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy(css="a.login")
    WebElement signIn;
    
    @FindBy(css="input#email")
    WebElement username;
    
    @FindBy(css="input#passwd")
    WebElement password;
    
    @FindBy(css="button#SubmitLogin")
    WebElement login;
    
    public void login(String uname, String pwd)
    {
    	signIn.click();
    	username.sendKeys(uname);
    	password.sendKeys(pwd);
    	login.click();
    }
}
