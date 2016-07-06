package com.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.common.CommonMethods;

public class CreateAccountPageFactory  {
	public CommonMethods CM;
	@FindBy(how=How.XPATH, using="//*[@id='tdb4']/span[2]")
	private WebElement ContinueButton;
	@FindBy(how=How.XPATH, using="//*[@id='bodyContent']/form/div/div[2]/table/tbody/tr[1]/td[2]/input[1]")
	private WebElement Genderm;
	@FindBy(how=How.NAME, using="firstname")
	private WebElement firstname;
	@FindBy(how=How.NAME, using="lastname")
	private WebElement lastname;
	@FindBy(how=How.NAME, using="dob")
	private WebElement dob;
	@FindBy(how=How.NAME, using="email_address")
	private WebElement email_address;
	@FindBy(how=How.NAME, using="street_address")
	private WebElement street_address;
	@FindBy(how=How.NAME, using="postcode")
	private WebElement postcode;
	@FindBy(how=How.NAME, using="city")
	private WebElement city;
	@FindBy(how=How.NAME, using="state")
	private WebElement state;
	@FindBy(how=How.NAME, using="country")
	private WebElement country;
	@FindBy(how=How.NAME, using="telephone")
	private WebElement telephone;
	@FindBy(how=How.NAME, using="password")
	private WebElement password;
	@FindBy(how=How.NAME, using="confirmation")
	private WebElement confirmation;
	
	public  CreateAccountPageFactory(WebDriver driver){
		CM=new CommonMethods(driver);
		PageFactory.initElements(driver, this);
		String expected="My Account Information";
		CM.verifyText(expected);
	}
   public void clickContinue(){
	  	CM.click(ContinueButton);
		}
   public void SignUp(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12){
	   CM.click(Genderm);
	   CM.setValue(firstname, string);
	   CM.setValue(lastname, string2);
	   CM.setValue(dob, string3);
	   CM.setValue(email_address, string4);	
	   CM.setValue(street_address, string5);
	   CM.setValue(postcode, string6);
	   CM.setValue(city, string7);
	   CM.setValue(state, string8);
	   CM.setValue(country, string9);
	   CM.setValue(telephone, string10);
	   CM.setValue(password, string11);
	   CM.setValue(confirmation, string12);
   }
   }
