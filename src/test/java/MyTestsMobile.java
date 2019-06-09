
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.EventPage;
import pages.FootballPage;

public class MyTestsMobile extends TestBase {

    @Test
    public void placeBetTest() {
        final String AMOUNT = "0.05";

        mainPage.cookieAcceptClick();
        FootballPage footballPage = mainPage.footballMobileLinkClick();
//        EventPage eventPage = footballPage.englishPremierLeagueMobileLinkClick();

        driver.get("https://sports.williamhill.com/betting/en-gb/football/OB_EV14079962/english-premier-league-outright");
        EventPage eventPage= PageFactory.initElements(this.driver, EventPage.class);

        String odds = eventPage.getBetButtonOdds();

        eventPage.betButtonMobileClick();
        eventPage.footerBetSlipClick();
        eventPage.betSlipFillMobile(AMOUNT);

        Assert.assertEquals(odds, eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(AMOUNT), eventPage.getOfferedReturn());

    }
}
