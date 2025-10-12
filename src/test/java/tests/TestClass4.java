package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class TestClass4 extends BaseTest {
	@Test
	public void LoginAndLogoutCheck() {
		System.out.println("--- Executing Class 4: Login Functionality ---");
		HomePage homePage = new HomePage(driver);

		// Click on "LOGIN" button
		homePage.loginBtn.click();

		// Use Below Credential and Click on the 'Login QKart Button'
		LoginPage loginPage = new LoginPage(driver);
		try {
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Verify LogOut link is displaying or not 
		Assert.assertTrue(loginPage.isLogoutDisplayed(), "Login successful, but Logout button is not displayed.");
	}
}
