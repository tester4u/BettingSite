package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;


public class EventPage extends BasePage {

    public EventPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({@FindBy(css = ".betbutton__odds")})
    private WebElement betButton;

    @FindBy(css = ".bets-container input[type='text']")
    private WebElement betSlip;

    @FindBy(css = "selection-price")
    private WebElement offeredOdds;

    @FindBy(css = ".betslip-selection__data  [id^='estimated-returns'] span")
    private WebElement offeredReturn;

    @FindBy(css = "a.toggle-betslip")
    private WebElement footerBetslip;

    @FindAll({@FindBy(css = "button.betslip-numberpad__button")})
    private List<WebElement> numpadButtons;

    public void betButtonClick() {
        utils.waitForElementNotDisplayed(preloader);
        utils.scrollIntoView(betButton);
        betButton.click();
    }

    public void betButtonMobileClick() {
        utils.waitForElement(betButton);
        betButton.click();
    }

    public String getBetButtonOdds() {
        utils.waitForElement(betButton);
        return betButton.getText();
    }

    public void betSlipFill(String amount) {
        utils.scrollIntoView(betSlip);
        betSlip.sendKeys(amount);
    }

    public String getOfferedOdds() {
        return offeredOdds.getText();
    }

    public String getOfferedReturn() {
        return offeredReturn.getText();
    }

    public String getExpectedReturn(String amount) {     //calculation of expected return
        BigDecimal numerator = new BigDecimal(getOfferedOdds().substring(0, getOfferedOdds().indexOf('/')));
        BigDecimal denominator = new BigDecimal((getOfferedOdds().substring(getOfferedOdds().indexOf('/') + 1)));

        BigDecimal expectedReturn = numerator.divide(denominator, 22, RoundingMode.DOWN).add(BigDecimal.ONE).multiply(new BigDecimal(amount)).setScale(2, RoundingMode.DOWN);

        return String.format(Locale.US,"%.2f", expectedReturn);
    }

    public void footerBetSlipClick() {
        footerBetslip.click();
    }

    public void betSlipFillMobile(String amount) {
        betSlip.click();
        for (char ch : amount.toCharArray()) {
            numpadButtonClick(ch);
        }
    }

    private void numpadButtonClick(char number) {
        for (WebElement element : numpadButtons) {
            if (element.getText().equals(Character.toString(number))) {
                element.click();
            }
        }
    }

}

