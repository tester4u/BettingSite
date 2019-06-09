package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class BasePage {
    Utils utils;
    WebDriver driver;

    @FindBy(css = "#wh-preloader")
    WebElement preloader;

    BasePage(WebDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }
}
