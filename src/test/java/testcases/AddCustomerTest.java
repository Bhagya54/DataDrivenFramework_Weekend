package testcases;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import base.BaseTest;
import utility.DataUtils;

public class AddCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtils.class,dataProvider = "dp")
	public void addCustomer(String firstName,String lastName,String zipcode) {
		click("addCustomerBtn_XPATH");
		type("firstName_XPATH",firstName);
		type("lastName_XPATH",lastName);
		type("zipcode_XPATH",zipcode);
		click("addCustbtn_XPATH");
		Alert al = driver.switchTo().alert();
		System.out.println(al.getText());
		al.accept();
	}
}
