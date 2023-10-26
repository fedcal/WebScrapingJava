package federicocalo.botwebscrapingJ.webdriver;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.*;
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

    public void generaFiltrati(Set<Integer> righe) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Risultati TC Filtrati");


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

        partiteFiltrate(sheet,righe);

        FileOutputStream fos = new FileOutputStream(SaveUtils.risultatiExcelTC_Filtrati);
        workbook.write(fos);
        fos.close();

    }

    private void partiteFiltrate(Sheet sheet, Set<Integer> righe) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(SaveUtils.risultatiExcelTC)));

        XSSFSheet sheet2 = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet2.iterator();
        int indexRow = 1;
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(righe.contains( row.getRowNum())){
                Row row2 = sheet.createRow(indexRow);
                Cell cell0 = row2.createCell(0);
                cell0.setCellValue(String.valueOf(row.getCell(0)));

                Cell cell6 = row2.createCell(1);
                cell6.setCellValue(String.valueOf(row.getCell(1)));

                Cell cell1 = row2.createCell(2);
                cell1.setCellValue(String.valueOf(row.getCell(2)));

                Cell cell2 = row2.createCell(3);
                cell2.setCellValue(String.valueOf(row.getCell(3)));

                Cell cell3 = row2.createCell(4);
                cell3.setCellValue(String.valueOf(row.getCell(4)));

                Cell cell4 = row2.createCell(5);
                cell4.setCellValue(String.valueOf(row.getCell(5)));

                Cell cell5 = row2.createCell(6);
                cell5.setCellValue(String.valueOf(row.getCell(6)));
                indexRow++;
            }
        }
    }

    public Set<Integer> getDatiRigheFiltrate() throws IOException {
        Set<Integer> righe = new TreeSet<>();

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(SaveUtils.risultatiExcelTC)));

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            boolean handicaB = false;
            boolean goalLinB = false;
            String handicap = row.getCell(4).getStringCellValue();
            String goalLine = row.getCell(6).getStringCellValue();

            handicaB = valutazioneHandicap(handicap);
            goalLinB = valutazioneGoalLine(goalLine);

            if(handicaB == true || goalLinB == true){
                righe.add(row.getRowNum());
            }
        }


        return righe;
    }

    private boolean valutazioneGoalLine(String goalLine) {
        String split = goalLine;
        if (goalLine.contains("(")){
            split = goalLine.split(" ")[0];
        }
        if(split.contains("\n")){
            split = split.split("\n")[0]
;        }
        split.trim();
        if(split.length()>4 && !split.equals("Goal Line")){
            split = split.substring(0,5);
        }

        Float goalLineConvert = 0.0F;
        if(!split.equals("Goal Line") && !split.equals(""))
            goalLineConvert = Float.valueOf(split);

        if(goalLineConvert >= 3.25F){
            return true;
        }else {
            return false;
        }
    }

    private boolean valutazioneHandicap(String handicap) {
        String numero = handicap;
        if(handicap.contains("(")){
            numero = handicap.split(" ")[0];
            
        }
        if(numero.contains("+")){
            numero.replace("+","");
        }else if(numero.contains("-")){
            numero.replace("-","");
        }
        numero.trim();

        Float conversioneNumero = 0.0F;

        if(numero!= null && !numero.equals("") &&!numero.equals("Handicap")){
            conversioneNumero = Float.valueOf(numero);
        }

        
        if(conversioneNumero >= 1.75F)
            return true;
        else 
            return false;
    }

    public void puliziaFiltrati() throws IOException {
        // da implementare?
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(SaveUtils.risultatiExcelTC)));

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        List<String> goalLinList = new ArrayList<>();

        
    }

    public void quitDriver(){
        this.quitDriver();
    }
}
