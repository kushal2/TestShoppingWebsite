package com.pageobject;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.common.CommonMethods;

public class LoginPage  {
	public CommonMethods CM;
	private WebDriver driver;
	//Logger logger= Logger.getLogger(LoginPage.class);
	private Logger logger;
	
	public  LoginPage(WebDriver driver){
		logger=LogManager.getLogger(LoginPage.class);
		CM=new CommonMethods(driver);
		this.driver=driver;
		String expected="Welcome, Please Sign In";
		CM.verifyText(expected);
	}
   public void clickSignIn(){
		driver.findElement(By.xpath("//*[@id='tdb5']/span[2]")).click();
		logger.info("click the sign in");
   	}
	
	public void enterEmailID(String stext ){
		driver.findElement(By.name("email_address")).sendKeys(stext);
		logger.info("entering the username ");
	}
	
   public void enterPWD(String stext ){
	   driver.findElement(By.name("password")).sendKeys(stext);
	   logger.info("entering the pwd ");
	}
   
   public void Login(String Username, String PWD){
	   enterEmailID(Username);
	   enterPWD(PWD);
	   clickSignIn();
	  // return new HomePage(driver);
   }
   
   public void LoginWithValidUserName(){
	   enterEmailID("ecalix@test.com");
	   enterPWD("test123");
	   clickSignIn();
   }
   

   
   

}
