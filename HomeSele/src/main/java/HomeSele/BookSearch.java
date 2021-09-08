package HomeSele;

import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.remote.MobileCapabilityType;

public class BookSearch {
	
	public static void searchForBook() throws MalformedURLException {
		
		AndroidDriver<AndroidElement> driver ;
		
		DesiredCapabilities descap = new DesiredCapabilities();
		
		descap.setCapability(MobileCapabilityType.DEVICE_NAME, "prachi phone");
		descap.setCapability("allowGrantPermissions", true);
		
		descap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
		descap.setCapability("unicodeKeyboard", "true");                                     
		descap.setCapability("resetKeyboard", "true");
		descap.setCapability(MobileCapabilityType.APP, "C:\\Users\\prach\\eclipse-workspace\\HomeSele\\src\\main\\resources\\app-debug.apk");
		driver = new AndroidDriver<>(new URL("http:\\127.0.0.1:4723/wd/hub"),descap);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.switchTo().alert().accept();
		
		//driver.findElementByAndroidUIAutomator("text(\"OK\")");
		
		driver.findElementByClassName("android.widget.ImageView").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("The Design Of");

		driver.getKeyboard().sendKeys(Keys.ENTER);
			
		driver.manage().timeouts().implicitlyWait(25000, TimeUnit.MILLISECONDS);

//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.RelativeLayout")));
		
		int noOfBooks = driver.findElementsByClassName("android.widget.RelativeLayout").size();
		
		String bookAuthor = null;
		List<AndroidElement> Books = driver.findElementsByClassName("android.widget.RelativeLayout");
		for(int i = 0; i <= noOfBooks -1 ; i++ ) {
			Books.get(i).click();
			bookAuthor =driver.findElementsByClassName("android.widget.TextView").get(1).getText();
			System.out.print(bookAuthor);
					if(bookAuthor.trim().equalsIgnoreCase("Sir Walter Scott")) {
						Assert.assertEquals(bookAuthor, "Sir Walter Scott","Correct Author found");
						break;
					}else {
						driver.navigate().back();
					}
		}
		
		driver.navigate().back();	
		
	}

}
