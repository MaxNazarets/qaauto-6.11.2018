import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage{

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;

        boolean isPageLoaded() {
            return searchFilterBar.isDisplayed()
                    && driver.getTitle().contains("Search | LinkedIn")
                    && driver.getCurrentUrl().contains("/search/results");
        }
    }
