package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class TestClass1 extends BaseTest {
	@Test
	public void testClass1_UIElementsPresentAndClickable() {
		System.out.println("--- Executing Class 1: UI Elements Validation ---");
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.loginBtn.isDisplayed(), home.loginBtn.getText() + " should be displayed.");
		Assert.assertTrue(home.registerBtn.isDisplayed(), home.registerBtn.getText() + " should be displayed.");
		Assert.assertTrue(home.searchBox.isDisplayed(), home.searchBox.getText() + " should be displayed.");

		Assert.assertTrue(home.loginBtn.isEnabled(), home.loginBtn.getText() + " should be clickable (enabled).");
		Assert.assertTrue(home.registerBtn.isEnabled(), home.registerBtn.getText() + " should be clickable (enabled).");
		Assert.assertTrue(home.searchBox.isEnabled(), home.searchBox.getText() + " should be clickable (enabled).");
	}
}
