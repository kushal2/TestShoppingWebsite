package com.jass.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.jass.common.CommonMethods;

public class HomePage {
  private WebDriver driver;
  public CommonMethods CM;
  @FindBy(how=How.XPATH, using="//*[@id='bodyContent']/div/div[1]/a[2]/u")
  private WebElement CreateAcc;
	public  HomePage(WebDriver driver){
		this.driver=driver;
		CM=new CommonMethods(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		String ExpectedText="Welcome to iBusiness";
	    String ActualText=driver.findElement(By.cssSelector("h1")).getText();
	    System.out.println("ExpectedText is="+ExpectedText+"\n"+"ActualText is="+ActualText);
	    Assert.assertEquals(ExpectedText,ActualText);
	}
	
	public void clickLogyourselfLink(){
		driver.findElement(By.xpath("//*[@id='bodyContent']/div/div[1]/a[1]/u")).click();
		System.out.println("click login link");
		
	}
	public void clickCreateAccLink(){
		//driver.findElement(By.xpath("//*[@id='bodyContent']/div/div[1]/a[2]/u")).click();
		CM.click(CreateAcc);
		
	}
	
	   public void clickLogOff(){
		   driver.findElement(By.xpath("//*[@id='tdb4']/span")).click();
			System.out.println("click the log off ");
		}
  
}
