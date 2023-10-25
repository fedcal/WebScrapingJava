package federicocalo.botwebscrapingJ.webdriver;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;

public class AsianBetWebScraping {
    private final String url = "https://www.asianbetsoccer.com/it/livescore.html";

    private WebDriver driver;

    public AsianBetWebScraping(){
        this.driver = new ChromeDriver();
        driver.get(url);
    }

    public String getUrl() {
        return url;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void mainAsianBet(WebDriver driver) throws IOException {


        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Risultati TC");
        WebElement table = driver.findElement(By.id("tablematch2"));

        System.out.println(table.getText());

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);

        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("Time");

        Cell cell6 = row.createCell(1);
        cell6.setCellValue("League");

        Cell cell1 = row.createCell(2);
        cell1.setCellValue("Home");

        Cell cell2 = row.createCell(3);
        cell2.setCellValue("Away");

        Cell cell3 = row.createCell(4);
        cell3.setCellValue("Handicap");

        Cell cell4 = row.createCell(5);
        cell4.setCellValue("Corner");

        Cell cell5 = row.createCell(6);
        cell5.setCellValue("Goal Line");


        FileOutputStream fos = new FileOutputStream(SaveUtils.risultatiExcelASB);
        workbook.write(fos);
        fos.close();
        driver.quit();
    }
}
