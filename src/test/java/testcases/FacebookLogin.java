package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import utility.DataUtils;


public class FacebookLogin extends BaseTest{

	
	@Test(dataProviderClass = DataUtils.class,dataProvider = "dp")
	public void doLogin(String username,String password)
	{
		type("username_ID",username);
		type("pswd_NAME",password);
		click("loginbtn_XPATH");
		
	}
	
	
	
}
