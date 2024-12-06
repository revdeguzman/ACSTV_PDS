package test_case;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseFiles;
import base.Screenshot;

public class ChangePassword extends BaseFiles {
	
	//Add Screenshot
	Screenshot screenshot = new Screenshot();
	String folderPath = System.getProperty("user.dir") + "\\src\\test\\screenshots";
	
	@Test(priority = 1)
	public void changePass() {
		
		//Click Profile
		driver.findElement(By.className("dropdown-toggle")).click();
		
		//Click Change Password
		driver.findElement(By.className("setadminpassword")).click();
		
		//Wait for Elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Wait for the Element to Load
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"notes\"]/div[2]")));
		
		driver.findElement(By.name("curr_pass")).sendKeys(ChangePass.getProperty("current_pass"));
		driver.findElement(By.name("curr_pass_new")).sendKeys(ChangePass.getProperty("new_pass"));
		driver.findElement(By.name("curr_pass_renew")).sendKeys(ChangePass.getProperty("retype_pass"));
		
		//Click Change
		driver.findElement(By.name("sendChanPss")).click();

	}

	@Test(priority = 2, dependsOnMethods = "changePass")
	public void Logout() {
		
		//wait for alert to be present
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		
		//Click OK of Alert
		Alert alert = driver.switchTo().alert();
		
		//Accept Alert
		alert.accept();

		//Logout Button
		driver.findElement(By.className("dropdown-toggle")).click();

		//Click Logout
		driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/span[1]/ul/li/ul/li[3]/a")).click();

	}

	@Test(priority = 3, dependsOnMethods = "Logout")
	public void reloginUser() throws InterruptedException, IOException {

		//Username Field
		driver.findElement(By.name("input_user")).sendKeys(ChangePass.getProperty("username"));

		//Password Field
		driver.findElement(By.name("input_pass")).sendKeys(ChangePass.getProperty("new_pass"));

		//Submit Button
		driver.findElement(By.name("submit_login")).click();

		//Click User Module
		driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/ul/li[3]/a")).click();

		//Wait for Elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		//Wait Logo to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/header")));
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
	}

}