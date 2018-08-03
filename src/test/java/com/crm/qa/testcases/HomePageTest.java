package com.crm.qa.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{

    TestUtil testUtil;
	LoginPage loginpage;
	HomePage homepage;
	ContactPage contactpage;

	public HomePageTest() {
		super(); }
	
	//test cases should be separated -- independent with each other
		//before each test case -- launch the browser and login
		//@test -- execute test case
		//after each test case -- close the browser

	@BeforeMethod
	public void setUp() {
		iniatilization();
	loginpage = new LoginPage();
	homepage=new HomePage();
	testUtil=new TestUtil();
	homepage = loginpage.Login(prop.getProperty("username"),prop.getProperty("password"));

	}
	
	@Test(priority=1)
	public void HomePageTitleTest(){
		String htitle= homepage.verfiyHomePageTitle();
		Assert.assertEquals(htitle, "CRMPRO","Home Page tite Does not matched");
	}

	@Test(priority=2)
	public void userNameLabelTest(){
		testUtil.switchToFrame();
		boolean flag=homepage.verifyCorrectUserName();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority=3)
	public void clinkOnContactLinkTest(){
		testUtil.switchToFrame();
		contactpage=homepage.clickOnContactLink();
		
			
	}
	
	
		

	@AfterMethod()
	public void tearDown(){
		driver.quit();
	}
}
