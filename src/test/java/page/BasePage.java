package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    abstract boolean isPageLoaded();
}
