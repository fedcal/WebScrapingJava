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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TotalCornerWebscraper {
    private final String url = "https://www.totalcorner.com/match/today";

    private WebDriver driver;

    public TotalCornerWebscraper(){
        this.driver = new  ChromeDriver();
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

    public void mainTotalCorner(WebDriver driver) throws IOException {


        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Risultati TC");


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

        registraPartite(sheet);

        FileOutputStream fos = new FileOutputStream(SaveUtils.risultatiExcelTC);
        workbook.write(fos);
        fos.close();
        driver.quit();
    }

    private void registraPartite(Sheet sheet) {
        WebElement bodyTable = driver.findElement(By.className("tbody_match"));
        List<WebElement> tr = bodyTable.findElements(By.tagName("tr"));

        AtomicInteger indexRow = new AtomicInteger(1);
        tr.stream().forEach(e -> {
            Row row = sheet.createRow(indexRow.get());

            Cell cell0 = row.createCell(0);
            cell0.setCellValue(e.findElements(By.tagName("td")).get(2).getText());

            Cell cell6 = row.createCell(1);
            cell6.setCellValue(e.findElements(By.tagName("td")).get(1).getText());

            Cell cell1 = row.createCell(2);
            cell1.setCellValue(e.findElements(By.tagName("td")).get(4).getText());

            Cell cell2 = row.createCell(3);
            cell2.setCellValue(e.findElements(By.tagName("td")).get(6).getText());

            Cell cell3 = row.createCell(4);
            cell3.setCellValue(e.findElements(By.tagName("td")).get(7).getText());

            Cell cell4 = row.createCell(5);
            cell4.setCellValue(e.findElements(By.tagName("td")).get(8).getText());

            Cell cell5 = row.createCell(6);
            cell5.setCellValue(e.findElements(By.tagName("td")).get(9).getText());
            indexRow.getAndIncrement();
        });
    }
}
