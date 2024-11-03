package TestBase;

import pages.addEmployee;
import pages.dashboard;
import pages.empProfile;
import pages.loginPage;
 
public class PageInitializer extends BaseClass {
	public static loginPage login;
	public static dashboard dash;
 	public static addEmployee addEmp;
 	public static empProfile profile;
	public static void initialize() {
		 login= new loginPage();
		 dash=new dashboard();
 		 addEmp=new addEmployee();		
 		 profile=new empProfile();
	}
}
