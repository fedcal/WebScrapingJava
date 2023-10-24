package federicocalo.botwebscrapingJ.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class WebScraping {
    private String url;
    private WebDriver driver;

    public WebScraping(String url){
        this.url=url;
        driver = new ChromeDriver();
    }
    public void startDriver(){
        driver.get(url);
    }

    public void quitDriver(){
        this.driver.quit();
    }

    public List<Vector<Object>> getTableById(String id, WebDriver driver){

        List<Vector<Object>> tableData = new ArrayList<>();

        WebElement table = driver.findElement(new By.ById(id));
        List<WebElement> trTable = table.findElements(By.tagName("tr"));
        for (WebElement e : trTable){
            List<WebElement> tdTable = e.findElements(By.tagName("td"));
            Vector<Object> rawElements = new Vector<>();
            for (WebElement td : tdTable){
                rawElements.add(td.getText());
            }
            tableData.add(rawElements);
        }

        return tableData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
