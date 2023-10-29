package federicocalo.botwebscrapingJ;

import federicocalo.botwebscrapingJ.utils.SaveUtils;
import federicocalo.botwebscrapingJ.webdriver.AsianBetWebScraping;
import federicocalo.botwebscrapingJ.webdriver.TotalCornerWebscraper;

import java.io.IOException;
import java.util.Set;

public class Launcher {
    public static void main(String[] args) throws IOException {

        SaveUtils.checkFolder();

        TotalCornerWebscraper totalCornerWebscraper = new TotalCornerWebscraper();
        totalCornerWebscraper.mainTotalCorner(totalCornerWebscraper.getDriver());
        Set<Integer> righe = totalCornerWebscraper.getDatiRigheFiltrate();
        totalCornerWebscraper.generaFiltrati(righe);

        totalCornerWebscraper.puliziaFiltrati();

        AsianBetWebScraping asianBetWebScraping = new AsianBetWebScraping();
        asianBetWebScraping.mainAsianBet(asianBetWebScraping.getDriver());

    }
}