import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.EventPage;
import pages.FootballPage;
import pages.MainPage;

public class MyTests extends TestBase {

    @Test
    public void placeBetTest() {
        final String AMOUNT = "0.05";

        MainPage mainPage = PageFactory.initElements(this.driver, MainPage.class);
        FootballPage footballPage = mainPage.footballLinkClick();
        EventPage eventPage = footballPage.englishPremierLeagueLinkClick();
        eventPage.betButtonClick();
        eventPage.betSlipFill(AMOUNT);

        Assert.assertEquals(eventPage.getBetButtonOdds(), eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(AMOUNT), eventPage.getOfferedReturn());
    }
}
