package TestBase;

import pages.loginPage;

public class PageInitializer extends BaseClass {
	public static loginPage login;
	public static void initialize() {
		 login= new loginPage();

	}
}
