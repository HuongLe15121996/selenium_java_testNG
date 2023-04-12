package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// open browser
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		//driver.get("https://www.facebook.com/");
		
		// open register page alada in browser
		driver.get("https://alada.vn/");
	}
// TC01 - Register with empty data
	@Test 
	public void TC_01_Url() {
		driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();		
		//driver.findElement(By.)
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//verify
		//assert.assertTrue -> Kiểm tra 1 điều kiện trả về là đúng
		//assert.assertFalse -> Kiểm tra 1 điều kiện trả về là sai
		//assert.assertEquals -> Kiểm tra kết quả thực tế với kết quả mong đơi
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		//kiểm tra text thông báo lỗi trả ra có đúng = ''Vui lòng nhập họ tên''
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

		
	}
	
// TC02 - Register with invalid email
	@Test 
	public void TC_02_Url() {
		//driver.findElement(By.xpath("//img[@src = 'images/graphics/alada.png']")).click(); // về lại home
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Huong");
		driver.findElement(By.id("txtEmail")).sendKeys("huong");
		driver.findElement(By.id("txtCEmail")).sendKeys("huong");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0332422262");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	
	@Test 
	public void TC_03_Incorrect_Email() {
		
	}
	@Test 
	public void TC_04_Incorrect_Password() {
		
	}
	@Test 
	public void TC_05_Incorrect_confirm_Password() {

	}
	@Test 
	public void TC_06_Incorrect_Phone_number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Huong");
		driver.findElement(By.id("txtEmail")).sendKeys("huong@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("huong@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		//case1 verify nhập 9 kí tự cho sđt
		driver.findElement(By.id("txtPhone")).sendKeys("091234568"); 
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// verify nhập sđt 12 kí tự
		driver.findElement(By.id("txtPhone")).clear(); // xoa dl đã nhập khi verify case 1
		driver.findElement(By.id("txtPhone")).sendKeys("091234567892");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		// verify nhập sđt sai đầu số
		driver.findElement(By.id("txtPhone")).clear(); // xoa dl đã nhập khi verify case 1
		driver.findElement(By.id("txtPhone")).sendKeys("00234567892");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	// tat trinh duyet
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
	//}
	
}
