package utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileOutputStream;
import java.util.EmptyStackException;

public class Browser {

    public static WebDriver driver;

    public WebDriver initDriver(String browser) {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/home/grozavu/IdeaProjects/999.md/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/home/grozavu/IdeaProjects/999.md/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Nu ai introdus browserul corect");
                break;
        }
        driver.manage().window().maximize();
        return driver;

    }

    public void selectCheck(String name) {
        String rbXpath = "//input[@id='%s']";
        if (!driver.findElement(By.xpath(String.format(rbXpath, name))).isSelected())
            driver.findElement(By.xpath(String.format(rbXpath, name))).click();
    }

    public void verificationCheck(String name) {
        String rbXpath = "//input[@id='%s']";
        System.out.println("CheckBox is selected -" + driver.findElement(By.xpath(String.format(rbXpath, name))).isSelected());

    }

    public void createExcel() {
        try {
            String filename = "/home/grozavu/IdeaProjects/999.md/NewExcelFile.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Nume");
            rowhead.createCell(1).setCellValue("Pret");
            for (int i = 1; i <= 50; i++) {
                WebElement link = driver.findElement(By.xpath("//li[@class=('ads-list-photo-item')][" + i + "]/div[@class=('ads-list-photo-item-title ')]/a"));
                WebElement pret = driver.findElement(By.xpath("//li[@class=('ads-list-photo-item')][" + i + "]/div[@class=(' ads-list-photo-item-price ')]/span"));
                HSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(link.getText());
                row.createCell(1).setCellValue(pret.getText());
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file has been generated!");
        } catch (Exception ex) {
            throw new EmptyStackException();
        }
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
    }
}
