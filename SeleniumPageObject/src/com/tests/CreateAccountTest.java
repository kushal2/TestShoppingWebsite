package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.common.CommonMethods;
import com.common.DatabaseConSSH;
import com.pageobject.CreateAccountPageFactory;
import com.pageobject.CreateAccountSuccess;
import com.pageobject.HomePage;
import com.pageobject.LoginPage;

public class CreateAccountTest  {
	Properties prop = new Properties();
	String propFileName = "config.properties";
	String url = prop.getProperty("url");
	int testno=0;
	public String getURL=url;
	private WebDriver driver;
	public LoginPage LoginPage;
	public HomePage HomePage;
	public CreateAccountSuccess CreateAccountSuccess;
	public CreateAccountPageFactory CreateAnAccountPage;
	public CommonMethods CM;
	public DatabaseConSSH DB;
	
	@BeforeMethod
	public void setUp() throws IOException{
		Properties prop = new Properties();
		InputStream input = new FileInputStream("config.properties");
		prop.load(input);
		getURL = prop.getProperty("url");
		CM=new CommonMethods(driver);
		DB=new DatabaseConSSH();
		driver=CM.openBrowser(prop.getProperty("browser"));
		CM.goToURL(getURL);
		}
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String filepath="screenshots\\Testno"+Integer.toString(testno);
	    testno++;
	    String filename=filepath+"_"+CreateAccountTest.class;
	    FileUtils.copyFile(scrFile, new File(filename));
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
	public void validsignup() throws InterruptedException, IOException{
	HomePage = new HomePage(driver);
	
	HomePage.clickCreateAccLink();
	CreateAnAccountPage =new CreateAccountPageFactory(driver);
    String filepath=CM.pro("config.properties").getProperty("excel");
    String mail1,mail2;
    mail1=UUID.randomUUID().toString().substring(0,5);
    mail2="@gmail.com";
    mail2=mail1+mail2;
    CreateAnAccountPage.SignUp(CM.excel(1,1, 1, filepath),CM.excel(1,1, 2, filepath), "06/05/1991",mail2, CM.excel(1,1, 5, filepath),CM.excel(1,1, 6, filepath), CM.excel(1,1, 7, filepath),CM.excel(1,1, 8, filepath), CM.excel(1,1, 9, filepath),CM.excel(1,1, 10, filepath), CM.excel(1,1, 11, filepath), CM.excel(1,1, 12, filepath));
    CreateAnAccountPage.clickContinue();
    CreateAccountSuccess=new CreateAccountSuccess(driver);
    String ExpectedData=CM.excel(1,1, 1, filepath)+CM.excel(1,1, 2, filepath)+mail2+CM.excel(1,1, 10, filepath)+ CM.excel(1,1, 5, filepath)+CM.excel(1,1, 6, filepath)+ CM.excel(1,1, 7, filepath)+CM.excel(1,1, 8, filepath);
    String ActualData=DB.testdatabase("select * from customers where customers_email_address='"+mail2+"';");
    Assert.assertEquals(ActualData, ExpectedData);
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