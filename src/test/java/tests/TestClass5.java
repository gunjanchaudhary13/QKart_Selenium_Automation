package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TestClass5 extends BaseTest {
	@Test
	public void PurchaseWorkflow() {
		System.out.println("--- Executing Class 5: Purchase process ---");
		HomePage homePage = new HomePage(driver);
		//String username = prop.getProperty("register_username");
		int randomNumber = (int) ((Math.random() * 9000000) + 1000000);
		String username = "Gunjan_" + randomNumber;
		String password = "Gunjan_" + randomNumber;
		//String password = prop.getProperty("register_password");
		
		String item1 = "YONEX Smash Badminton Racquet";
		String item2 = "Roadster Mens Running Shoes";
		// String expectedSuccessMsg = "Product has been successfully added to cart";
		String expectedDuplicateMsg = "Item already in cart. Use the cart sidebar to update quantity or remove item.";
		String address = "123 Test Street, New York, USA";
		int noOfItemsAdded = 0;
        
        try {
        	//homePage.loginBtn.click();
    		homePage.registerBtn.click();
            RegisterPage registerPage = new RegisterPage(driver);
			registerPage.register(username, password, password);
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(username, password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// 4.) Add yonex racquet in cart
		homePage.searchForProduct("Yonex");
		homePage.addProductToCart(item1);
		noOfItemsAdded++;
		
		homePage.waitToDisapperToastMessage();
		
		// 5.) Validate the message "Product has been successfully added to cart".
		// String actualSuccessMsg = homePage.getToastMessageText();
		// Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg), "Success
		// message mismatch.");

		// 6.) Try clicking "Add to Cart " Button again and validate the message
		homePage.addProductToCart(item1);

		String actualDuplicateMsg = homePage.waitForToastMessage();
		// System.out.println("Gunjan:" + actualDuplicateMsg);
		Assert.assertTrue(actualDuplicateMsg.contains(expectedDuplicateMsg), "Duplicate item message mismatch.");

		homePage.waitToDisapperToastMessage();
		
		// 5.b) Add Roadster Mens Running Shoes and pick size from the dropdown menu
		homePage.searchForProduct("Roadster");
		homePage.selectItemSize("7");
		homePage.addProductToCart(item2);
		noOfItemsAdded++;

		homePage.waitToDisapperToastMessage();
		
		// 6.) Validate the cart items
		List<String> cartItems = homePage.getCartItemNames(noOfItemsAdded);
		Assert.assertTrue(cartItems.contains(item1), item1 + " should be present in cart.");
		Assert.assertTrue(cartItems.contains(item2), item2 + " should be present in cart.");

		// 7.) Click on checkout button.
		homePage.clickCheckout();

		// 8.) Verify the title of the next webpage
		Assert.assertEquals(homePage.getTitle(), "QKart", "Page Title mismatch, expected 'QKart'.");

		CheckoutPage checkoutPage = new CheckoutPage(driver);
     
        
		// 9.) Click on add new Address and type the respective addresses
		// 10.) click on ADD button and verify the address entered is displayed
		checkoutPage.addNewAddress(address);
		Assert.assertTrue(checkoutPage.isAddressDisplayed(address.substring(0, 10)),
				"Newly added address should be displayed on the checkout page.");

		// 11.) click on delete button to remove one address.
		checkoutPage.deleteFirstAddress();

		// Verify the address is gone
		Assert.assertFalse(checkoutPage.isAddressDisplayed(address.substring(0, 10)),
				"The address should be successfully deleted and no longer visible.");
	}
}
