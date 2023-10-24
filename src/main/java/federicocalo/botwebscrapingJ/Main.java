package federicocalo.botwebscrapingJ;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import federicocalo.botwebscrapingJ.webdriver.WebScraping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        WebScraping scraping = new WebScraping("https://www.totalcorner.com/match/today");
        scraping.startDriver();

        List<Vector<Object>> tableData = scraping.getTableById("inplay_match_table",scraping.getDriver());

        SaveUtils.checkFolder();

        scraping.quitDriver();
    }
}