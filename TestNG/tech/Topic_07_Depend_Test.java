package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_Depend_Test {
	@Test()
	public void Manage_01_Create() {
		Assert.assertTrue(false);
		
	}
	@Test(dependsOnMethods = "Manage_01_Create")
	public void Manage_02_View() {
		
	}
	@Test()
	public void Manage_03_Edit() {
		
	}
	@Test(dependsOnMethods = {"Manage_01_Create", "Manage_03_Edit" })//phụ thuộc vào nhiều hơn 1
	public void Manage_04_Delete() {

	}

}
