package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Main class for all PageObject classes.
 */
public abstract class BasePage {
    protected static GMailService gMailService;

    protected WebDriver driver;

    protected void waitUntilElementIsVisible(WebElement elementToWaitFor) {
        waitUntilElementIsVisible(elementToWaitFor, 5);
    }

    protected void waitUntilElementIsVisible(WebElement elementToWaitFor, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    public abstract boolean isPageLoaded();
}
