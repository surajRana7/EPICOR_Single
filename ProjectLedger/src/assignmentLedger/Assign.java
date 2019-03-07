package assignmentLedger;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class Assign {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException{
		
		//System.setProperty("webdriver.chrome.driver", "D:\\Softwares_Installed\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
		System.setProperty("webdriver.ie.driver", "C:\\Users\\Sach\\Desktop\\assignment\\IEDriver_32bit\\IEDriverServer.exe");
		WebDriver driver=new InternetExplorerDriver();
		/*DesiredCapabilities cap=DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		driver=new InternetExplorerDriver(cap);*/
		
		
		driver.get("http://125.18.224.122/ERP10Demo-EWA");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("manager");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("manager");
		driver.findElement(By.xpath("//button[@id='btnLogin']")).click();
		
		//Thread.sleep(2000);
		driver.switchTo().frame("menu");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='dynatree-title'][contains(text(),'Epicor Education')]")).click();
		//driver.findElement(By.xpath("(//a[contains(text(),'Epicor Education')])[2]")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("(//a[contains(text(),'Epicor Education')])[2]")).click();

		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[@class='dynatree-title'][contains(text(),'Main')]")).click();
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='dynatree-title'][contains(text(),'Financial Management')]")).click();
		//driver.findElement(By.xpath("//a[contains(text(),'Financial Management')])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'General Ledger')]")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[contains(text(),'General Ledger')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='dynatree-node dynatree-exp-c dynatree-ico-c']//a[@class='dynatree-title'][contains(text(),'Setup')]")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[@class='dynatree-node dynatree-exp-c dynatree-ico-c']//a[@class='dynatree-title'][contains(text(),'Setup')]")).click();
		//Thread.sleep(2000);
		/*WebDriverWait d=new WebDriverWait(driver,10);
		d.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='General Ledger Account']")));*/
		driver.findElement(By.xpath("//div[@title='General Ledger Account']")).click();
		//Thread.sleep(2000);
		
		//driver.findElement(By.xpath("//body//span[15]")).click();
		//driver.findElement(By.xpath("//div[@class='epiListContainer']//span[15]")).click();
	
		Set<String> ids=driver.getWindowHandles();
		Iterator <String>it=ids.iterator();
		String parent=it.next();
		String child=it.next();

		driver.switchTo().window(child);
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//input[@id='glaeGLAccount_dropText']")).clear();
		File f =new File("C:\\Users\\Sach\\Desktop\\GLAccount.xls");
		FileInputStream fis=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		HSSFSheet ws=wb.getSheet("Sheet1");
		
		String abc=NumberToTextConverter.toText(ws.getRow(1).getCell(0).getNumericCellValue());
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='glaeGLAccount_dropText']")).sendKeys(abc);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(2000);
		Robot robo=new Robot();
		robo.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		Thread.sleep(2000);
		String sc=driver.findElement(By.xpath("//div[@class='TreeItem active']")).getText();
		Assert.assertEquals("1238-01-20", sc);
		
		
	}
	

}
