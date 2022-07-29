
package step.Definition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import core.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.RetailPageObject;
import utilities.Utils;

public class RetailStepDefinition extends Base {
	RetailPageObject retailpagebbject = new RetailPageObject();

	// Background
	@Given("User is on Retail website")
	public void user_is_on_retail_website() {
		String expectedText = "TEST ENVIRONMENT";
		String actualText = retailpagebbject.getToRetailPage();
		Assert.assertEquals(expectedText, actualText);
		logger.info("User is on the reatil page successfully.");
	}

	@And("User click on My Account")
	public void user_click_on_my_account() {
		retailpagebbject.clickOnMyAccount();
		logger.info("User clicked on my account link successfully.");
	}

	@When("User click on Login")
	public void user_click_on_login() {
		retailpagebbject.clickOnLogin();
		logger.info("User clicked on login link successfully.");
	}

	@And("User enter username {string} and password {string}")
	public void user_enter_username_and_password(String email, String password) {
		retailpagebbject.enterEmailAdd("firooz.afzaly123@gmail.com");
		retailpagebbject.enterPassword("abc2525");
		logger.info("User entered email address and password successfully");
	}

	@And("User click on Login button")
	public void user_click_on_login_button() {
		retailpagebbject.clickOnLoginButton();
		logger.info("User clicked on login button successfully.");
	}

	@Then("User should be logged in to My Account dashboard")
	public void user_should_be_logged_in_to_my_account_dashboard() {
		String expectedText = "My Account";
		String ActulText = retailpagebbject.getLoggedinDashboard();
		Assert.assertEquals(expectedText, ActulText);
		logger.info("User landed on login dashboard successfully.");
		Utils.takeScreenshotForStep();
	}

	// Scenario 1: Register as an Affiliate user with Cheque Payment Method

	@When("User click on Register for an Affiliate Account link")
	public void User_click_on_Register_for_an_Affiliate_Account_link() {
		retailpagebbject.clickOnRegForAffiliateAccountText();
		logger.info("Register account link was clicked successfully");
	}

	@And("User fill affiliate form with below information")
	public void user_fill_affiliate_form_with_below_information(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		retailpagebbject.enterCompanyName(data.get(0).get("company"));
		retailpagebbject.enterWebsite(data.get(0).get("website"));
		retailpagebbject.enterTaxId(data.get(0).get("taxID"));
		retailpagebbject.clickOnChequeRadioButton();
		retailpagebbject.enterChequePayeeName(data.get(0).get("chequepayeename"));
		logger.info("User filled the affiliated account form successfully");
	}

	@And("User check on About us check box")
	public void user_check_on_about_us_check_box() {
		retailpagebbject.clickOnAboutUsCheckBox();
		logger.info("User checked the About Us successfully");
	}

	@And("User click on Continue button")
	public void user_click_on_continue_button() {
		retailpagebbject.clickOnContinueButton();
		logger.info("User clicked on the continue button succcessfully");
	}

	@Then("User should see a success message")
	public void user_should_see_a_success_message() {
		String expectedMessage = "Success: Your account has been successfully updated.";
		String actualMessage = retailpagebbject.getSuccessMessageOnAffiliatedAccount();
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("User see a success message successfully");
		Utils.takeScreenshotForStep();
	}

	// Scenario 2: Edit your affiliate information from Cheque payment method to
	// Bank Transfer
	@When("User click on Edit your affiliate information link")
	public void User_click_on_Edit_your_affiliate_information_link() {
		retailpagebbject.clickOnEditAffiliatedInformationLink();
		logger.info("User clicked on Edit Affiliated Information Link successfully");
	}

	@And("user click on Bank Transfer radio button")
	public void user_click_on_bank_transfer_radio_button() {
		retailpagebbject.clickOnBankRadioButton();
		logger.info("User selected Bank Radio Button successfully");
	}

	@And("User fill Bank information with below information")
	public void user_fill_bank_information_with_below_information(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		retailpagebbject.enterBankName(data.get(0).get("bankName"));
		retailpagebbject.enterBranchNumber(data.get(0).get("abaNumber"));
		retailpagebbject.enterSwiftCode(data.get(0).get("swiftCode"));
		retailpagebbject.enterAccountName(data.get(0).get("accountName"));
		retailpagebbject.enterAccountNumber(data.get(0).get("accountNumber"));
		logger.info("User filled Bank information successfully");
		Utils.takeScreenshotForStep();
	}

	//// Scenario 3: Edit your account Information
	@When("User click on Edit your account information link")
	public void User_click_on_Edit_your_account_information_link() {
		retailpagebbject.clickOnEditYourAccountInfo();
		logger.info("User clicked on edit your account link successfully");
	}

	@And("User modify below information")
	public void user_modify_below_information(DataTable dataTable) {
		retailpagebbject.clearFirstNameField();
		retailpagebbject.clearLastNameField();
		retailpagebbject.clearAccountEmailField();
		retailpagebbject.clearTelephoneNumberField();
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		retailpagebbject.editFirstName(data.get(0).get("firstname"));
		retailpagebbject.editLastName(data.get(0).get("lastName"));
		retailpagebbject.editAccountEmailField(data.get(0).get("email"));
		retailpagebbject.editTelephoneNumber(data.get(0).get("telephone"));
		logger.info("User modified the information successfully");
	}

	@And("User click on continue button")
	public void User_click_on_continue_button() {
		retailpagebbject.clickOncontinueBttnOnEditAccountInfo();
		logger.info("User clicked on the continue button successfully");
	}

	@Then("User should see a message {string}")
	public void user_should_see_a_message(String string) {
		String expectedMessage = "Success: Your account has been successfully updated.";
		String actualMessage = retailpagebbject.getSuccessMessageOnEditAccountInfo();
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("User see a message: Success: Your account has been successfully updated.");
		Utils.takeScreenshotForStep();
	}
}
