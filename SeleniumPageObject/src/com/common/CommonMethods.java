package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class CommonMethods {
	private  WebDriver driver;
	
	public CommonMethods(WebDriver driver){
		this.driver = driver;
	}
	
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
	public WebDriver openBrowser(String browserType){
		if (browserType.contentEquals("Firefox")){
			driver=new FirefoxDriver();
			System.out.println("opening browser");
		}else if (browserType.contentEquals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			DesiredCapabilities Capabilities = DesiredCapabilities.chrome();
	   		Capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
	   		driver = new ChromeDriver(Capabilities); 
	   		System.out.println("opening browser");
		}else if (browserType.contentEquals("IE")){
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			DesiredCapabilities Capabilities = DesiredCapabilities.internetExplorer();
	      	Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	      	    driver = new InternetExplorerDriver();    
	   		System.out.println("opening browser");
		}
		else{
			System.out.println("Please select the Firefox or Chome Browser");
			Assert.fail("Browser not selected");
		}
			
		driver.manage().window().maximize();
		System.out.println(" Browser is mazimized");
		driver.manage().deleteAllCookies();
		System.out.println("deleted all cookies");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
	public void goToURL(String sURL){
		driver.get(sURL);
		System.out.println("opening url="+sURL);
	}

	public String excel(int a,int i,int j, String filepath){
		String val=null;
		try {
			FileInputStream file = new FileInputStream(new File(filepath));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
    		HSSFSheet sheet = workbook.getSheetAt(a);
	        Row row =sheet.getRow(i);
			Cell cell = row.getCell(j);
			switch(cell.getCellType()){
			case Cell.CELL_TYPE_BOOLEAN:
			val=(cell.getBooleanCellValue()+"");
			break;
			case Cell.CELL_TYPE_NUMERIC:
			val=((int)(cell.getNumericCellValue()) + "");
			break;
			case Cell.CELL_TYPE_STRING:
			val=(cell.getStringCellValue() + "");
			break;
			}
			file.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return val;
	} 
	public Properties pro(String filename) throws IOException{
		Properties prop = new Properties();
		InputStream input = new FileInputStream(filename);
		prop.load(input);
		return prop;
	}
	
	public void closeBrowser(){
		driver.quit();
		System.out.println("close the browser ");
	}
	
	public void verifyText(String expected){
		 try{
			 driver.findElement(By.xpath("//*[contains(text(),'"+ expected.trim() +"')]"));
			 System.out.println("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" verified");
			// return true;
		 }
		 catch(NoSuchElementException e){
			 System.out.println("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
			 Assert.fail("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
		 }
		 
	}
	
	public void click(WebElement slocator){
		    slocator.click();
			//System.out.println("click the link "+slocator.toString());
			}
	public void alertcheck(String a){
		   Alert alert =driver.switchTo().alert();
		   String expected=a.replaceAll("\\s+", "");
		   String actual=alert.getText();
		   actual=actual.replaceAll("\\s+", "");
		   if(actual.toLowerCase().contains(expected.toLowerCase()))
		   {Assert.assertEquals(expected, expected);
		   alert.accept();
		   }
		   else
		   {System.out.println("click the log off ");
		   Assert.assertEquals(actual.trim(), expected);}
	   }
	public void setValue(WebElement slocator,String sValue){
		//slocator.clear();
		slocator.sendKeys(sValue);
		System.out.println(sValue+" entered");
	}
	
	
	
}
