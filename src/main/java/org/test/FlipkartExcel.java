package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FlipkartExcel {
	static String newWindow;
	public static void main(String[] args) throws InterruptedException, Throwable {
		
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Desktop\\Srilakshmi\\Maven\\drivers\\chromedriver.exe");
	   WebDriver driver=new ChromeDriver();
	   driver.get("https://www.flipkart.com/");
	
	  try {
		  WebElement close = driver.findElement(By.xpath("//button[text()='✕']"));
		  close.isDisplayed();
		  close.click();
	  }
	catch (Exception e) {
	System.out.println("pop up not displayed");
	}
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mobiles",Keys.ENTER);
		
		File f=new File("C:\\Users\\hp\\Desktop\\Srilakshmi\\Maven\\drivers\\FlipKartTask.xlsx");
		FileOutputStream f1=new FileOutputStream(f);
		Workbook wb=new XSSFWorkbook();
		Sheet sheet = wb.createSheet("flipKartTask");
		Thread.sleep(3000);
		List<WebElement> mobName = driver.findElements(By.xpath("//div[@id='container']//div//div//a//div[contains(text(),'GB')]"));
		for (int i = 0; i < mobName.size(); i++) {
			
			String name = mobName.get(i).getText();
			
			//System.out.println("in");
			//System.out.println(name);
			
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);										
			cell.setCellValue(name);
			
				
		}
		
		wb.write(f1);
		
		mobName.get(3).click();
		String parent = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		for (String x : child) {
			if (!parent.equals(x)) {
				
				driver.switchTo().window(x);
				
				WebElement newName = driver.findElement(By.xpath("//div[@id='container']//span[contains(text(),'GB')]"));
				 newWindow = newName.getText();
				System.out.println("new window mobile name is :" + newWindow);
				
			}
			
		}
		FileInputStream f2=new FileInputStream(f);
		Cell cell1 = sheet.getRow(3).getCell(0);
		String excelValue = cell1.getStringCellValue();
		
		System.out.println("value from excel :"+excelValue);
		
		if (excelValue.equals(newWindow)) {
			
			System.out.println("pass");
			
		}else {
			System.out.println("fail");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}	
	
}