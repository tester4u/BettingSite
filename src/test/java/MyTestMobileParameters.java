import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EventPage;
import pages.FootballPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class MyTestMobileParameters extends TestBase {
    private String amount;

    @Override
    void setUpDriver() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0.01"}, {"0.1"}, {"1"}, {"999999999999.99"}
        });
    }

    public MyTestMobileParameters(String amount) {
        this.amount = amount;
    }

    @Test
    public void placeBetTestWithParameters() {
        mainPage.cookieAcceptClick();
        mainPage.highlightsClick();
        FootballPage footballPage = mainPage.footballMobileLinkClick();
        mainPage.highlightsClick();
        EventPage eventPage = footballPage.englishPremierLeagueMobileLinkClick();

        String odds = eventPage.getBetButtonOdds();

        eventPage.betButtonMobileClick();
        eventPage.footerBetSlipClick();
        eventPage.betSlipFillMobile(amount);

        Assert.assertEquals(odds, eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(amount), eventPage.getOfferedReturn());
    }
}
