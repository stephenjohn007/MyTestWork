package com.sample.testruns;

import org.testng.annotations.Test;
import com.sample.baseclass.BasePages;
import com.sample.testcases.SignIn;

public class SignInPage extends BasePages {

	@Test(description = "Sign_In_Page")
	public void signIn() throws Exception {
		childTest = test.createNode("Sign_In_Page").assignAuthor("Owner").assignCategory("Smoke")
				.assignDevice("Pixel 4");
		System.out.println("extent report");
		SignIn signinpage = new SignIn();
		signinpage.signIn();
	}
}
