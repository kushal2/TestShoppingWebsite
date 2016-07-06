package com.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.common.CommonMethods;
import com.pageobject.CreateAccountPageFactory;
import com.pageobject.CreateAccountSuccess;
import com.pageobject.HomePage;
import com.pageobject.LoginPage;

public class CreateAccountTest  {
	Properties prop = new Properties();
	String propFileName = "config.properties";
	String url = prop.getProperty("url");
	public String getURL=url;
	private WebDriver driver;
	public LoginPage LoginPage;
	public HomePage HomePage;
	public CreateAccountSuccess CreateAccountSuccess;
	public CreateAccountPageFactory CreateAnAccountPage;
	public CommonMethods CM;
	
	@BeforeMethod
	public void setUp() throws IOException{
		Properties prop = new Properties();
		InputStream input = new FileInputStream("config.properties");
		prop.load(input);
		getURL = prop.getProperty("url");
		CM=new CommonMethods(driver);
		driver=CM.openBrowser(prop.getProperty("browser"));
		CM.goToURL(getURL);
		}
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		CM.closeBrowser();
		}
	@Test
	public void wrongfn() throws InterruptedException, IOException{
	HomePage = new HomePage(driver);
	HomePage.clickCreateAccLink();
	CreateAnAccountPage =new CreateAccountPageFactory(driver);
    String filepath=CM.pro("config.properties").getProperty("excel");
    CreateAnAccountPage.SignUp(CM.excel(1,2, 1, filepath),CM.excel(1,2, 2, filepath), CM.excel(1,2, 3, filepath),CM.excel(1,2,4, filepath), CM.excel(1,2, 5, filepath),CM.excel(1,2, 6, filepath), CM.excel(1,2, 7, filepath),CM.excel(1,2, 8, filepath), CM.excel(1,2, 9, filepath),CM.excel(1,2, 10, filepath), CM.excel(1,2, 11, filepath), CM.excel(1,2, 12, filepath));
	CreateAnAccountPage.clickContinue();
	CM.alertcheck("YourFirstNamemustcontainaminimumof2characters.");
		}
	@Test
	public void wrongln() throws InterruptedException, IOException{
	HomePage = new HomePage(driver);
	HomePage.clickCreateAccLink();
	CreateAnAccountPage =new CreateAccountPageFactory(driver);
    String filepath=CM.pro("config.properties").getProperty("excel");
    CreateAnAccountPage.SignUp(CM.excel(1,3, 1, filepath),CM.excel(1,3, 2, filepath), CM.excel(1,3, 3, filepath),CM.excel(1,3, 4, filepath), CM.excel(1,3, 5, filepath),CM.excel(1,3, 6, filepath), CM.excel(1,3, 7, filepath),CM.excel(1,3, 8, filepath), CM.excel(1,3, 9, filepath),CM.excel(1,3, 10, filepath), CM.excel(1,3, 11, filepath), CM.excel(1,3, 12, filepath));
    CreateAnAccountPage.clickContinue();
	CM.alertcheck("YourLastNamemustcontainaminimumof2characters.");
		}
	
	}