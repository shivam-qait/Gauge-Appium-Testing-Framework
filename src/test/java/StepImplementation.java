import com.thoughtworks.gauge.Step;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class StepImplementation {

    public static AndroidDriver<AndroidElement> driver;

    @Step("Navigate to <http://127.0.0.1:4723/wd/hub>")
    public void navigateTo(String url) throws InterruptedException, MalformedURLException {
    	File appDir = new File("src");
		   
	    File app = new File(appDir, "ApiDemos-debug.apk");

	DesiredCapabilities cap=new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
	cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	driver=new AndroidDriver<AndroidElement>(new URL(url),cap );
	System.out.println("Connected");
    }

    @Step("Perform Steps in mobile app")
    public void enterQuery() {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Preference\"]").click();
		System.out.println("Clicked on Preferences");
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]").click();
		System.out.println("Clicked on Preference Dependencies");
		driver.findElementById("android:id/checkbox").click();
		System.out.println("Checbox selected");
		driver.findElementsByClassName("android.widget.RelativeLayout").get(1).click();
		System.out.println("Clicked on wifi settings");
		driver.findElementById("android:id/edit").sendKeys("Text");
		System.out.println("Entered text - Text");
		driver.findElementById("android:id/button1").click();
		System.out.println("Clicked on Ok button");
		assertEquals(driver.findElementById("android:id/checkbox").getAttribute("checked"), "true");
		System.out.println("Assertion Passed: Wifi checkbox is selected");
		driver.findElementById("android:id/checkbox").click();
		System.out.println("Checbox selected");
		assertEquals(driver.findElementById("android:id/checkbox").getAttribute("checked"), "false");
		System.out.println("Assertion Passed: Wifi checkbox is not selected");
		driver.quit();
    }

//    @Step("The search results should show <LambdaTest> as result")
//    public void verifySearchResult(String resultString) {
//        WebElement result = driver.findElement(By.className("LC20lb"));
//        assertTrue(result.getText().contains(resultString));
//        driver.close();
//    }
}