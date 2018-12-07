package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

public abstract class BasePage {

    protected static GMailService gMailService;

    protected WebDriver driver;

    public abstract boolean isPageLoaded();
}
