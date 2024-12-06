package test_case;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseFiles;
import base.Screenshot;

public class EditUser extends BaseFiles {
	
	//Add Screenshot
	Screenshot screenshot = new Screenshot();
	String folderPath = System.getProperty("user.dir") + "\\src\\test\\screenshots\\Edit User\\";
	
	@Test(priority = 1)
	public void EditDetails () throws InterruptedException, IOException {
	
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
	
	//Take screenshot
	screenshot.FullPageScreenShot(folderPath);
	
	 // Locate the table rows
    List<WebElement> rows = driver.findElements(By.xpath("//table[@id='JColResizer0']/tbody/tr"));

    // Loop through rows to find the one with Login based on edit user properties
    for (WebElement row : rows) {
        WebElement loginColumn = row.findElement(By.xpath(".//td[2]")); // Login column
        String loginText = loginColumn.getText();

	      if (loginText.equals(EditUser.getProperty("username"))) {
	          // Perform click action on the row or any element within it      	
	          System.out.println("Found the row with Login: " + EditUser.getProperty("username"));
	          
	          // Clicking the <a> link in the Login column
	          WebElement link = row.findElement(By.xpath(".//td[2]/a"));
	          link.click();
	
	          break;
        }
    }

    //Validate if Password is not empty
	WebElement password = driver.findElement(By.name("password1"));
	
	String password1 = password.getAttribute("value");
	
	if (!password1.isEmpty()) {
		password.clear();
		password.sendKeys(EditUser.getProperty("password1"));
	} else {
		System.out.println("Password Field is already empty.");
	}
	
    //Validate if Retype Password is not empty
	WebElement retpassword2 = driver.findElement(By.name("password2"));
	
	String password2 = retpassword2.getAttribute("value");
	
	if (!password2.isEmpty()) {
		retpassword2.clear();
		retpassword2.sendKeys(EditUser.getProperty("password1"));
	} else {
		System.out.println("Retype Password Field is already empty.");
	}
	
    //Validate if Expiration Date is not empty
	WebElement expDate = driver.findElement(By.name("expiration"));
	
	String expDateValue = expDate.getAttribute("value");
	
	if (!expDateValue.isEmpty()) {
		expDate.clear();
		expDate.sendKeys(EditUser.getProperty("expiration"));
	} else {
		System.out.println("Expiration Date Field is already empty.");
	}
	
	//Click Save
	driver.findElement(By.name("save")).click();
	
	//Take screenshot
    screenshot.FullPageScreenShot(folderPath);
		
	}
			
	@Test(priority = 2, dependsOnMethods = "EditDetails")
	public void Logout() {

	//Logout Button
	driver.findElement(By.className("dropdown-toggle")).click();

	//Click Logout
	driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/span[1]/ul/li/ul/li[3]/a")).click();
	
	}
		
	@Test(priority = 3, dependsOnMethods = "Logout")
	public void reloginUser() throws InterruptedException, IOException {
			
	//Username Field
	driver.findElement(By.name("input_user")).sendKeys(EditUser.getProperty("username"));

	//Password Field
	driver.findElement(By.name("input_pass")).sendKeys(EditUser.getProperty("password1"));

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
