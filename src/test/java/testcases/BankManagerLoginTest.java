package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest{

	@Test
	public void bankManagerLogin() {
		click("bankManagerLoginbtn_XPATH");
		//isElementPresent("addCustomerBtn_XPATH");//true
		Assert.assertTrue(isElementPresent("addCustomerBtn_XPATH"));
	}
}
