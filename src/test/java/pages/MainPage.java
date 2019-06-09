package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul#desktop-sidebar-az li#nav-football a")
    private WebElement footballLink;

    @FindBy(css = "[data-panel='panel-highlights'] footer [href*='football']")
    private WebElement footballMobileLink;

    @FindBy(css = "button.cookie-disclaimer__button")
    private WebElement cookieAccept;

    @FindBy(css = "#az-sports-btn-toolbar")
    private WebElement toolbarSports;

    @FindBy(css = ".sidebar-navigation__subnav [data-type='submenu'] div:nth-child(3) li:nth-child(12)")
    private WebElement menuFootball;

    @FindBy(css = "[data-name='Highlights']")
    private WebElement highlights;

    public FootballPage footballLinkClick() {
        footballLink.click();
        return PageFactory.initElements(this.driver, FootballPage.class);
    }

    public FootballPage footballMobileLinkClick() {
        utils.scrollIntoViewAndClick(footballMobileLink);
        return PageFactory.initElements(this.driver, FootballPage.class);
    }

    public void cookieAcceptClick() {
        utils.waitForElement(cookieAccept);
        cookieAccept.click();
    }

    public void highlightsClick() {
        utils.scrollIntoViewAndClick(highlights);
    }
}