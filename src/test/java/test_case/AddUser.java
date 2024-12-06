package test_case;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseFiles;
import base.Screenshot;

public class AddUser extends BaseFiles {
	
	//Add Screenshot
	Screenshot screenshot = new Screenshot();
	String folderPath = System.getProperty("user.dir") + "\\src\\test\\screenshots";

//	@Test(priority = 1)
	public void CreateUser() throws InterruptedException, IOException {
		
		//Click User Module
		driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/ul/li[3]/a")).click();
		
		//Wait for Elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Wait for the Element to Load
		WebElement usersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='index.php?menu=userlist']/span[text()='Users']")));
		
		//Click the Element
		usersLink.click();

		//Wait if Buttons appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/a/div/button")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/div[1]/button")));
		
		//Create New User
		driver.findElement(By.xpath("//*[@id=\"idformgrid\"]/div[1]/a/div/button")).click();
		
		//Validate Module
		String description = driver.findElement(By.xpath("//*[@id=\"neo-contentbox-maincolumn\"]/div/form/table/tbody/tr[2]/td/fieldset[1]/legend/b")).getText();
		Assert.assertEquals(description, "Main Fields");
		
		//Login Field
		driver.findElement(By.name("name")).sendKeys(AddUser.getProperty("username"));
		
		//Password Field
		driver.findElement(By.name("password1")).sendKeys(AddUser.getProperty("password1"));
		
		//Retype Password Field
		driver.findElement(By.name("password2")).sendKeys(AddUser.getProperty("password2"));
		
		//Expiration Date Field
		WebElement expDate = driver.findElement(By.name("expiration"));
		
		String expDateValue = expDate.getAttribute("value");
		
		if (!expDateValue.isEmpty()) {
			expDate.clear();
			expDate.sendKeys(AddUser.getProperty("expiration"));
		} else {
			System.out.println("Field is already empty.");
		}
		
		//Group Field
		WebElement dropdownElement = driver.findElement(By.id("group"));
		
		//Create Group Dropdown Object
		Select GroupDropdown = new Select(dropdownElement);
		
		//Select Value from dropdown
		GroupDropdown.selectByVisibleText(AddUser.getProperty("group"));
		
		//Name Field
		driver.findElement(By.name("description")).sendKeys(AddUser.getProperty("name"));
		
		//Extension Field
		WebElement extensionDropdown = driver.findElement(By.name("extension"));
		
		//Create Extension Dropdown
		Select ExtensionField = new Select(extensionDropdown);
		
		//Select Value from Dropdown
		ExtensionField.selectByVisibleText(AddUser.getProperty("extension"));
		
		//Webmail User Field
		driver.findElement(By.name("webmailuser")).sendKeys(AddUser.getProperty("webmail_user"));
		
		//Webmail Password Field
		driver.findElement(By.name("webmailpassword1")).sendKeys(AddUser.getProperty("webmail_password"));
		
		//Webmain Domain Field
		driver.findElement(By.name("webmaildomain")).sendKeys(AddUser.getProperty("webmail_domain"));
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
		//Save Button
		driver.findElement(By.name("save")).click();
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
	}
	
//	@Test(priority = 2, dependsOnMethods = "CreateUser")
	public void Logout() {
		
		//Logout Button
		driver.findElement(By.className("dropdown-toggle")).click();
				
		//Click Logout
		driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/span[1]/ul/li/ul/li[3]/a")).click();
		
	}
	
//	@Test(priority = 3, dependsOnMethods = "Logout")
	public void reloginUser() throws InterruptedException, IOException {
		
		//Username Field
		driver.findElement(By.name("input_user")).sendKeys(AddUser.getProperty("username"));
		
		//Password Field
		driver.findElement(By.name("input_pass")).sendKeys(AddUser.getProperty("password1"));
		
		//Submit Button
		driver.findElement(By.name("submit_login")).click();
		
		//Click User Module
		driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/ul/li[3]/a")).click();

		//Wait for Elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				
		//Wait for the Element to Load
		WebElement usersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='index.php?menu=userlist']/span[text()='Users']")));
				
		//Click the Element
		usersLink.click();

		//Wait if Buttons appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/a/div/button")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/div[1]/button")));
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
	}
		
}
