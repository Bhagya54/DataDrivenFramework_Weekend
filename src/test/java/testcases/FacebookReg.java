package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utility.DataUtils;

public class FacebookReg extends BaseTest{

	
	@Test(dataProviderClass = DataUtils.class,dataProvider = "dp")
	public void doReg(String firstname,String lastname) {
		System.out.println(firstname+ " " + lastname);
	}
	
	
}
