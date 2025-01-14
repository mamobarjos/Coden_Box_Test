package codenboxautomationlab;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MainTest {
	WebDriver driver = new ChromeDriver();
	String MyWebsite = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	//this is related to test number 11
    Actions action = new Actions(driver);
	@BeforeTest
	public void Mysetup() {
		driver.get(MyWebsite);
		driver.manage().window().maximize();
		//في حال العنصر ما بين استنى عليه اكم ثانية
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

// this Test is modifiy later test priority = 9 description ="Test Hide and show buttons"
	@Test(priority = 1, description = "RadioButton", invocationCount = 1, enabled = false)
	public void RadioButtonExample() throws InterruptedException {
		List<WebElement> AllRadioButton = driver.findElements(By.className("radioButton"));
		System.out.println(AllRadioButton.size());
		Thread.sleep(2000);
		int RandomIndex = rand.nextInt(AllRadioButton.size());
		AllRadioButton.get(RandomIndex).click();
		boolean ExpectedResult = true;
		boolean ActualResult = AllRadioButton.get(RandomIndex).isSelected();
		Assert.assertEquals(ActualResult, ExpectedResult);

	}

//this Test is modifiy later test priority = 9 description ="Test Hide and show buttons"
	@Test(priority = 2, description = "Dynamic Dropdown Example and check if the same data input", enabled = false)
	public void DynamicDropdownExample() throws InterruptedException {
		// web element to the input field country
		WebElement TypetoSelectCountries = driver.findElement(By.id("autocomplete"));
		// generate random string in a static way because i dont need my test to iclude
		// othre data
		String[] randomTowChar = { "ab", "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES" };
		// random index based on the length of the above array
		int randomIndex = rand.nextInt(randomTowChar.length);
		// send an random item from my array to the web elemen inputfield
		TypetoSelectCountries.sendKeys(randomTowChar[randomIndex]);
		Thread.sleep(1000);
		// it will press an arrow down + enter to select the first item from the list
		TypetoSelectCountries.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		boolean ExpectedResult = true;
		// ما في اي طريقة اجيب القيمة الحقيقية غير بالجافا سكريبت معرفة في الأعلى
        //i need to capture take the country name that selenum already selected
		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", TypetoSelectCountries);
		// the country name for example Jordan contains capital letters and small ones
		// so what i did i make all the letters capital
		String UpdateDataInMyInput = DataInsideMyInput.toUpperCase();
        //System.out.println(DataInsideMyInput.contains(randomTowChar[randomIndex]));
        //عند  الطباعة يعطي خطأ بأن الحروف التي اختارها غير موجودة في الكلمة اللي اختارها والسبب انه يوجد اختلاف بين الحروف الكابيتل والسمول
		System.out.println(UpdateDataInMyInput.contains(randomTowChar[randomIndex]));
		boolean ActualResult = UpdateDataInMyInput.contains(randomTowChar[randomIndex]);
		Assert.assertEquals(ExpectedResult, ActualResult);
		
	}

	@Test(priority = 3, description = "Static Dropdown Example", enabled = false)
	public void StaticDropdownExample() {
		WebElement dropdownclassexample = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(dropdownclassexample);
		int randomIndex = rand.nextInt(3);
		sel.selectByIndex(randomIndex);

//		sel.selectByValue("option1");
//		sel.selectByVisibleText("Selenium");
	}
	// this Test is modifiy later test priority = 9 description ="Test Hide and show buttons"
	@Test(priority = 4, description = "Checkbox check", invocationCount = 1, enabled = false)
	public void Checkbox() throws InterruptedException {
		List<WebElement> Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(Checkboxes.size());
		//int randomIndex = rand.nextInt(Checkboxes.size());
		Thread.sleep(1000);
		//Checkboxes.get(randomIndex).click();
		for (int i = 0; i < Checkboxes.size(); i++) {
			Checkboxes.get(i).click();
			//لأتأكد انه انعمل عليهم كليك
			boolean ActualResult = Checkboxes.get(i).isSelected();
			boolean expectedResult = true;
			Assert.assertEquals(ActualResult, expectedResult);
			LocalDate date = LocalDate.now();
			String DayAfterTomorrow=
					Integer.toString(date.plusDays(2).getDayOfMonth());
			System.out.println(DayAfterTomorrow);
		}
	}

	@Test(priority = 5, description = "Move from windows to another one", enabled = false)
	public void Switch_Window_Example() throws InterruptedException {
		WebElement Open_Window = driver.findElement(By.id("openwindow"));
		Open_Window.click();
		Thread.sleep(1000);
		List<String> WindowsHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(WindowsHandle.size());
		driver.switchTo().window(WindowsHandle.get(1));
		WebElement About = driver.findElement(By.xpath("//*[@id=\"menu-item-9680\"]/a/span[1]"));
		About.click();
	}

	@Test(priority = 5, description = "Move from windows to another one", enabled = false)
	public void Switch_Window() throws InterruptedException {
		WebElement Open_Window = driver.findElement(By.id("openwindow"));
		Open_Window.click();
		List<String> WindowsHandle = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(1000);
		System.out.println(WindowsHandle.size());
		driver.switchTo().window(WindowsHandle.get(1));
		WebElement About = driver.findElement(By.xpath("//*[@id=\"menu-item-9680\"]/a/span[1]"));
		About.click();
		System.out.println(driver.getTitle() + " hello from second window");
	}

	@Test(priority = 6, description = "check moving to another tab", enabled = false)
	public void Switch_Tab_Example() throws InterruptedException {
		WebElement TabButton = driver.findElement(By.id("opentab"));
		TabButton.click();
		List<String> WindowsHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(WindowsHandle.size());
		Thread.sleep(2000);
		driver.switchTo().window(WindowsHandle.getLast());
		System.out.println(driver.getTitle());

	}

	@Test(priority = 7, description = "Switch To Alert", enabled = false)
	public void SwitchToAlert() throws InterruptedException {
		WebElement name_box = driver.findElement(By.id("name"));
		name_box.sendKeys("mahmood");
//	WebElement Alert = driver.findElement(By.id("alertbtn"));
//	Alert.click();
//	Thread.sleep(1000);
//	driver.switchTo().alert().accept();
//	driver.switchTo().alert().dismiss();
		WebElement Confirm = driver.findElement(By.id("confirmbtn"));
		Confirm.click();
		Thread.sleep(1000);
//	driver.switchTo().alert().dismiss();
		System.out.println(driver.switchTo().alert().getText());
	}

	@Test(priority = 8, description = "Play With All Table Data", enabled = false)
	public void Web_Table() {
		WebElement TheTable = driver.findElement(By.id("product"));
		List<WebElement> TheDataInsideTheTable = TheTable.findElements(By.tagName("td"));
		// print all data
		for (int i = 0; i < TheDataInsideTheTable.size(); i++) {
			System.out.println(TheDataInsideTheTable.get(i).getText());
		}
		/*
		 * List<WebElement> TheRawInsideTheTable =
		 * TheTable.findElements(By.tagName("tr")); // print Raw data for(int i=0;
		 * i<TheRawInsideTheTable.size() ;i++) {
		 * System.out.println(TheRawInsideTheTable.get(i).getText()); }
		 */
		/*
		 * List<WebElement> TheDataInsideTheTable =
		 * TheTable.findElements(By.tagName("tr")); // print first column for(int i=1;
		 * i<TheDataInsideTheTable.size() ;i++) {
		 * System.out.println(TheDataInsideTheTable.get(i).findElements(By.tagName("td")
		 * ).get(0).getText()); } }
		 */
		/*
		 * List<WebElement> TheDataInsideTheTable =
		 * TheTable.findElements(By.tagName("tr")); // print colum 3 for(int i=1;
		 * i<TheDataInsideTheTable.size() ;i++) {
		 * System.out.println(TheDataInsideTheTable.get(i).findElements(By.tagName("td")
		 * ).get(2).getText()); }
		 */
		/*
		 * List<WebElement> TheDataInsideTheTable =
		 * TheTable.findElements(By.tagName("tr")); // print last colum for(int i=1;
		 * i<TheDataInsideTheTable.size() ;i++) { int TotalTdInTheRow =
		 * TheDataInsideTheTable.get(i).findElements(By.tagName("td")).size();
		 * System.out.println(
		 * TheDataInsideTheTable.get(i).findElements(By.tagName("td")).get(
		 * TotalTdInTheRow-1).getText()); }
		 */
	}

	/*
	 * @Test(priority = 9, description ="Test Hide and show buttons", enabled=false)
	 * public void Element_Displayed_Example() { WebElement HideButton =
	 * driver.findElement(By.id("hide-textbox")); WebElement ShowButton =
	 * driver.findElement(By.id("show-textbox")); HideButton.click(); WebElement
	 * displayedTest = driver.findElement(By.id("displayed-text")); // وبتالي راح
	 * ينجح التيست false انا متوقع النتيجة تكون
	 * Assert.assertEquals(displayedTest.isDisplayed(), false); ShowButton.click();
	 * Assert.assertEquals(displayedTest.isDisplayed(), true);
	 * 
	 * }
	 */
	@Test(priority = 9, description = "Test Hide and show buttons", enabled = false)
	// Use java Script
	public void Element_Displayed() throws InterruptedException {
		// this code to coding in Javascript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// to skip any falier test
		SoftAssert myAssertion = new SoftAssert();
		js.executeScript("alert('mahmood')");
		Thread.sleep(6000);
		// driver.switchTo().alert().dismiss();
		driver.switchTo().alert().accept();
		Thread.sleep(4000);
		js.executeScript("window.scrollTo(0,1500)");
		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		HideButton.click();
		WebElement displayedTest = driver.findElement(By.id("displayed-text"));

		// وبتالي راح ينجح التيست false انا متوقع النتيجة تكون
		// hardassert if one test failed it will stop all the execuation all stop
		// Assert.assertEquals(displayedTest.isDisplayed(), false);

		// Softassert if one test failed it will continue to the rest code skip test
		myAssertion.assertEquals(displayedTest.isDisplayed(), true);
		Thread.sleep(4000);
		ShowButton.click();
		myAssertion.assertEquals(displayedTest.isDisplayed(), true);
		myAssertion.assertAll();
	}
	@Test(priority=10, description ="test enable disable button", enabled =false )
	public void Enabled_Disabled_Example () throws InterruptedException {
		WebElement DisapleButton = driver.findElement(By.id("disabled-button"));
		WebElement EnableButton = driver.findElement(By.id("enabled-button"));
		WebElement EnabledField = driver.findElement(By.id("enabled-example-input"));
		DisapleButton.click();
		boolean ActualResult=EnabledField.isEnabled();
		boolean ExpectedResult = false;
		Assert.assertEquals(ActualResult, ExpectedResult);
		Thread.sleep(1000);
		EnableButton.click();
		boolean ActualResult2 =EnabledField.isEnabled();
		EnabledField.sendKeys("mahmood");
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);
	}
@Test(priority=11,description = "check the hover to certain element", enabled =false)
public void Mouse_Hover_Example ( ) throws InterruptedException {
	js.executeScript("window.scrollTo(0,1800)");
	Thread.sleep(2000);
	WebElement MouseHover = driver.findElement(By.id("mousehover"));
	action.moveToElement(MouseHover).perform();
	//fined elemnt with selectorshub-xpath-helper
	//driver.findElement(By.cssSelector("body > div:nth-child(18) > div:nth-child(3) > div:nth-child(1) > main:nth-child(1) > article:nth-child(1) > div:nth-child(2) > div:nth-child(12) > div:nth-child(1) > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)"));
	//driver.findElement(By.linkText("Top")).click();
	// i use part of link text type <a> </a>
	driver.findElement(By.partialLinkText("Rel")).click();
}
@Test(priority=12,description="Open calender in new tap", enabled =false)	
public void Calendar_Example ( ) throws InterruptedException {
	//js.executeScript("window.scrollTo(0,1900)");
	//Thread.sleep(1000);
	//driver.findElement(By.linkText("Booking Calendar")).click();
	WebElement Calendar = driver.findElement(By.partialLinkText("Booking"));
	Calendar.click();
	Thread.sleep(1000);
	List<String> WindowsHandle = new ArrayList<String>(driver.getWindowHandles());
	//System.out.println(WindowsHandle.size());
	driver.switchTo().window(WindowsHandle.get(1));
	System.out.println(driver.getTitle());
	// لمعرفة عدد الأيام التي يمكن حججزها بالشهر  
	// must know how data-available we have
	System.out.println(driver.findElements(By.className("date_available")).size());
	int TotalAvailbleDates = driver.findElements(By.className("date_available")).size();
	//في حال بدي اياه يضغط على اول واخر يوم متاح للحجز
	driver.findElements(By.className("date_available")).get(0).click();
	driver.findElements(By.className("date_available")).get(TotalAvailbleDates-1).click();	
}
	@Test(priority=13 , description ="Switch to frame inside the main page", enabled= false )
	public void iFrame_Example () {
		// there is only one frame
		driver.switchTo().frame(0);
		// i search the frame tag and add id
		//WebElement FrameWindow = (WebElement) driver.switchTo().frame("courses-iframe");
		String TheText = driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();
		System.out.println(TheText);
	}
	@Test(priority=14 , description ="dawnload the file inside the main page", enabled= false )
	public void Download_file_to_test () {
		//WebElement TheFile = driver.findElement(By.xpath("//a[@href=\'http://codenboxautomationlab.com/wp-content/uploads/2022/12/APKFiles-1.zip\']"));
		//i found cssselector by but (.) before the to class i have
		//WebElement TheFile = driver.findElement(By.cssSelector(".wp-block-button__link.wp-element-button"));
		//Xpath from class
		WebElement TheFile = driver.findElement(By.xpath("//a[@class='wp-block-button__link wp-element-button\']"));
		TheFile.click();
	}
	
	
	  @Test(priority=15, description ="Check Title", enabled=true) public void
	  Check_Title ( ) { String expected
	  ="Automation Practice - CodenBox AutomationLab"; String actual =
	  driver.getTitle(); Assert.assertEquals(actual,expected); }
	 

}
