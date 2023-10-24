package federicocalo.botwebscrapingJ.webdriver;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TotalCornerWebscraper {
    private final String url = "https://www.totalcorner.com/match/today";

    private WebDriver driver;

    public TotalCornerWebscraper(){
        this.driver.get(url);
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

    public void mainTotalCorner() throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Risultati TC");
        int indexRow = 1;

        Row row = sheet.createRow(indexRow);

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

        FileOutputStream fos = new FileOutputStream(SaveUtils.risultatiExcelTC);

    }
}
