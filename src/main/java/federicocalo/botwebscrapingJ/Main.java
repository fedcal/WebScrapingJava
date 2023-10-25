package federicocalo.botwebscrapingJ;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import federicocalo.botwebscrapingJ.webdriver.AsianBetWebScraping;
import federicocalo.botwebscrapingJ.webdriver.TotalCornerWebscraper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SaveUtils.checkFolder();

        TotalCornerWebscraper totalCornerWebscraper = new TotalCornerWebscraper();
        totalCornerWebscraper.mainTotalCorner(totalCornerWebscraper.getDriver());

        AsianBetWebScraping asianBetWebScraping = new AsianBetWebScraping();
        asianBetWebScraping.mainAsianBet(asianBetWebScraping.getDriver());
    }
}