package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class xPath {
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
		
		// open basic-form
		driver.get("https://automationfc.github.io/basic-form/");
	}

	@Test
	public void TC_01_Url() {
		//driver.findElement(By.xpath("//a[text()= 'Đăng Ký']")).click();
		//driver.findElement(By.)
		//driver.findElement(By.id("txtFirstName")).sendKeys("");
		System.out.println("text của thẻ h5 " + driver.findElement(By.xpath("//h5[@id = 'nested']")).getText()); 
		//dung getText() là của selenium thì sẽ lấy được tất cả text của cha và con
		// còn dùng text() trong xpath thì chỉ lấy được text của chính thẻ đang thao tác
	}
	
	
	
	
	// tat trinh duyet
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
