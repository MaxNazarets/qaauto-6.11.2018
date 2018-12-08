package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

/**
 * Main class for all PageObject classes.
 */
public abstract class BasePage {

    protected static GMailService gMailService;

    protected WebDriver driver;

    public abstract boolean isPageLoaded();
}
