import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.PageFactory;
import pages.EventPage;
import pages.FootballPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MyTestsParameters extends TestBase {
    private String amount;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0.01"}, {"0.1"}, {"1"}, {"999999999999.99"}
        });
    }

    public MyTestsParameters(String amount) {
        this.amount = amount;
    }

    @Test
    public void placeBetTestWithParameters() {
        MainPage mainPage = PageFactory.initElements(this.driver, MainPage.class);
        FootballPage footballPage = mainPage.footballLinkClick();
        EventPage eventPage = footballPage.englishPremierLeagueLinkClick();
        eventPage.betButtonClick();
        eventPage.betSlipFill(amount);

        Assert.assertEquals(eventPage.getBetButtonOdds(), eventPage.getOfferedOdds());
        Assert.assertEquals(eventPage.getExpectedReturn(amount), eventPage.getOfferedReturn());
    }
}
