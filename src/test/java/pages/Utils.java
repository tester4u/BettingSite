package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Utils {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    Utils(WebDriver driver) {
        this.driver = (RemoteWebDriver) driver;
        wait = new WebDriverWait(driver, 60);
    }

    void scrollIntoView(WebElement element) {
        JavascriptExecutor js = driver;
        js.executeScript("window.scrollTo(" + element.getLocation().getX() + "," + (element.getLocation().getY() - 100) + ");");
    }

    void scrollIntoViewAndClick(WebElement element) {
        Actions action = new Actions(driver);
        boolean elementNotClicked = true;
        while (elementNotClicked) {
            action.sendKeys(Keys.DOWN).build().perform();
            try {
                Thread.sleep(1000);            //I'm not happy with 'sleep', but I couldn't find another way to scroll in emulator
                element.click();
                elementNotClicked = false;
            } catch (ElementClickInterceptedException e) {
                elementNotClicked = true;
            } catch (InterruptedException ex) {
            }
        }
    }

    void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitForElementNotDisplayed(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

}
