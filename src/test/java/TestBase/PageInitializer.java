package TestBase;

import pages.addEmployee;
import pages.dashboard;
import pages.demoGuru99;
import pages.employeeList;
import pages.empProfile;
import pages.loginPage;
 
public class PageInitializer extends BaseClass {
	public static loginPage login;
	public static dashboard dash;
 	public static addEmployee addEmp;
 	public static empProfile profile;
 	public static demoGuru99 demoGuru;
 	public static employeeList empList;
	public static void initialize() {
		 login= new loginPage();
		 dash=new dashboard();
 		 addEmp=new addEmployee();		
 		 profile=new empProfile();
 		 demoGuru=new demoGuru99();
 		 empList=new employeeList();
	}
}
