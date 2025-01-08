package codenboxautomationlab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTest {
	WebDriver driver = new ChromeDriver();
	String MyWebsite = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();

	@BeforeTest
	public void Mysetup() {
		driver.get(MyWebsite);
		driver.manage().window().maximize();
	}

	@Test(priority = 1, description = "RadioButton", invocationCount = 5, enabled = false)
	public void RadioButtonExample() throws InterruptedException {
		List<WebElement> AllRadioButton = driver.findElements(By.className("radioButton"));
		System.out.println(AllRadioButton.size());
		Thread.sleep(2000);
		int RandomIndex = rand.nextInt(AllRadioButton.size());
		AllRadioButton.get(RandomIndex).click();
	}

	@Test(priority = 2, description = "Dynamic Dropdown Example", enabled = false)
	public void DynamicDropdownExample() throws InterruptedException {
		WebElement TypetoSelectCountries = driver.findElement(By.id("autocomplete"));
		String[] randomTowChar = { "ab", "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES" };
		int randomIndex = rand.nextInt(randomTowChar.length);

		TypetoSelectCountries.sendKeys(randomTowChar[randomIndex]);
		Thread.sleep(1000);
		TypetoSelectCountries.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

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

	@Test(priority = 4, description = "Checkbox check", invocationCount = 5, enabled = false)
	public void Checkbox() throws InterruptedException {
		List<WebElement> Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(Checkboxes.size());
		int randomIndex = rand.nextInt(Checkboxes.size());
		Thread.sleep(1000);
		Checkboxes.get(randomIndex).click();
		for (int i = 0; i < Checkboxes.size(); i++) {
			Checkboxes.get(i).click();
		}
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
		System.out.println(driver.getTitle()+ " hello from second window");
	}
	@Test(priority=6,description = "check moving to another tab")
	public void Switch_Tab_Example () throws InterruptedException {
		WebElement TabButton = driver.findElement(By.id("opentab"));
		TabButton.click();
		List <String> WindowsHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(WindowsHandle.size());
		Thread.sleep(2000);
		driver.switchTo().window(WindowsHandle.getLast());
		System.out.println(driver.getTitle());
		
	}
}
