import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.PageFactory;
import pages.EventPage;
import pages.FootballPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MyTestMobileParameters extends TestBase {
    private String amount;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"0.01"}, {"0.1"},  {"1"}, {"999999999999.99"}
        });
    }

    public MyTestMobileParameters(String amount) {
        this.amount = amount;
    }

    @Test
    public void placeBetTestWithParameters() throws InterruptedException {

        mainPage.cookieAcceptClick();
        FootballPage footballPage = mainPage.footballMobileLinkClick();
//        EventPage eventPage = footballPage.englishPremierLeagueMobileLinkClick();

        driver.get("https://sports.williamhill.com/betting/en-gb/football/OB_EV14079962/english-premier-league-outright");
        EventPage eventPage= PageFactory.initElements(this.driver, EventPage.class);

        String odds = eventPage.getBetButtonOdds();
        eventPage.betButtonMobileClick();

        eventPage.footerBetSlipClick();
        eventPage.betSlipFillMobile(amount);

        Assert.assertEquals(odds, eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(amount), eventPage.getOfferedReturn());
    }
}
