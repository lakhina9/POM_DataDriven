package com.crm.qa.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase {

	TestUtil testUtil;
	LoginPage loginpage;
	HomePage homepage;
	ContactPage contactpage;

	public ContactPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		iniatilization();
		loginpage = new LoginPage();
		contactpage = new ContactPage();
		// homepage=new HomePage();
		testUtil = new TestUtil();
		homepage = loginpage.Login(prop.getProperty("username"),
				prop.getProperty("password"));
		testUtil.switchToFrame();
		contactpage = homepage.clickOnContactLink();
	}

	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactpage.verfiyContactLabel());
	}

	@Test(priority = 2)
	public void selectSingleContactsTest() {
		contactpage.selectContactsByName("Automation Testing");
	}

	@Test(priority = 3)
	public void multipleSingleContactsTest() {
		contactpage.selectContactsByName("Automation Testing");
		contactpage.selectContactsByName("shilpa pandey");
	}

	@DataProvider
	public Object[][] getCRMTestData(){
		Object[][] data=TestUtil.getTestData("contact");
				
		return data;
		
	}
	
	@Test(priority = 4,dataProvider="getCRMTestData")
	
	public void addNewContactDetailTest(String title,String fName,String lName,String company) {
		homepage.clickOnNewContact();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactpage.addNewContactDetail(title,fName,lName,company);
	}
	


	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
