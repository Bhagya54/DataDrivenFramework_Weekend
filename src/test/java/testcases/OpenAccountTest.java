package testcases;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import base.BaseTest;
import utility.DataUtils;

public class OpenAccountTest extends BaseTest{

	@Test(dataProviderClass = DataUtils.class,dataProvider = "dp")
	public void openAccount(String customerName,String Currency) {
		click("openAccountbtn_XPATH");
		select("customerName_ID", customerName);
		select("currency_ID", Currency);
		click("processBtn_XPATH");
		Alert al = driver.switchTo().alert();
		System.out.println(al.getText());
		al.accept();
	}
}
