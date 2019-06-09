import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EventPage;
import pages.FootballPage;

import java.util.HashMap;
import java.util.Map;

public class MyTestsMobile extends TestBase {

    @Override
    void setUpDriver() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void placeBetTest() {
        final String AMOUNT = "0.05";

        mainPage.cookieAcceptClick();
        mainPage.highlightsClick();
        FootballPage footballPage = mainPage.footballMobileLinkClick();
        mainPage.highlightsClick();
        EventPage eventPage = footballPage.englishPremierLeagueMobileLinkClick();

        String odds = eventPage.getBetButtonOdds();

        eventPage.betButtonMobileClick();
        eventPage.footerBetSlipClick();
        eventPage.betSlipFillMobile(AMOUNT);

        Assert.assertEquals(odds, eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(AMOUNT), eventPage.getOfferedReturn());
    }
}
