package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class FootballPage extends BasePage {

    public FootballPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({@FindBy(css = "a[title*='English Premier League']")})
    private List<WebElement> englishPremierLeagueLinks;

    public EventPage englishPremierLeagueLinkClick() {
        utils.waitForElementNotDisplayed(preloader);
        utils.scrollIntoView(englishPremierLeagueLinks.get(0));
        this.englishPremierLeagueLinks.get(0).click();
        return PageFactory.initElements(this.driver, EventPage.class);
    }

    public EventPage englishPremierLeagueMobileLinkClick() {
        utils.scrollIntoViewAndClick(englishPremierLeagueLinks.get(0));
        return PageFactory.initElements(this.driver, EventPage.class);
    }

}
