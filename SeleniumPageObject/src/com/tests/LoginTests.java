package com.tests;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.common.CommonMethods;
import com.pageobject.HomePage;
import com.pageobject.LoginPage;

public class LoginTests  {
	private WebDriver driver;
	public String getURL;
	public LoginPage LoginPage;
	public HomePage HomePage;
	public CommonMethods CM;

	@BeforeMethod
	public void setUp() throws IOException{
		CM=new CommonMethods(driver);
		Properties prop=CM.pro("config.properties");
		driver=CM.openBrowser(prop.getProperty("browser"));
		System.out.println();
		CM.goToURL(CM.pro("config.properties").getProperty("url"));
		}
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		CM.closeBrowser();
		
	}
	

	@Test
	public void testLogin() throws IOException{
	HomePage = new HomePage(driver);
	HomePage.clickLogyourselfLink();
    LoginPage=new LoginPage(driver);
    String filepath=CM.pro("config.properties").getProperty("excel");
	LoginPage.Login(CM.excel(0,1, 0, filepath), CM.excel(0,1, 1, filepath));
	com.pageobject.HomePage HomePage1 = new HomePage(driver);
	CM.verifyText("Welcome to iBusiness");
	HomePage1.clickLogOff();		
	}
	
	
	@Test
	public void testLoginError(){
		HomePage = new HomePage(driver);
		HomePage.clickLogyourselfLink();
		LoginPage=new LoginPage(driver);
		LoginPage.clickSignIn();
		String ExpectedText="Error: No match for E-Mail Address and/or Password.";
		CM.verifyText(ExpectedText);
	}
	@Test
	public void testLoginbadpass() throws IOException{
	HomePage = new HomePage(driver);
	HomePage.clickLogyourselfLink();
    LoginPage=new LoginPage(driver);
    String filepath=CM.pro("config.properties").getProperty("excel");
	LoginPage.Login(CM.excel(0,2, 0, filepath), CM.excel(0,2, 1, filepath));
	String ExpectedText="Error: No match for E-Mail Address and/or Password.";
	CM.verifyText(ExpectedText);
	HomePage.clickLogOff();		
	}
	
	
}
