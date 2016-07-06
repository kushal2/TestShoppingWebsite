package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateAccountSuccess {
  private WebDriver driver;
  
	public  CreateAccountSuccess(WebDriver driver){
		this.driver=driver;
		String ExpectedText="Your Account Has Been Created!";
	    String ActualText=driver.findElement(By.cssSelector("h1")).getText();
	    System.out.println("ExpectedText is="+ExpectedText+"\n"+"ActualText is="+ActualText);
	    Assert.assertEquals(ExpectedText,ActualText);
	}
	

	
	   public void clickContinue(){
		   driver.findElement(By.xpath("//*[@id='tdb5']/span")).click();
		   }
  
}
