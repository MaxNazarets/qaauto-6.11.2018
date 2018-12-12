package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * PageObject class for SearchResultsPage.
 */
public class SearchResultsPage extends BasePage {

    /**
     * Constructor of SearchResultsPage class.
     *
     * @param driver - webDriver instance from Test.
     */
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementIsVisible(searchFiltersBar, 5);
    }

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFiltersBar;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    /**
     * Method to check if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return searchFiltersBar.isDisplayed()
                && driver.getTitle().contains("Search | LinkedIn")
                && driver.getCurrentUrl().contains("/search/results");
    }

    /**
     * Method to get SearchResults count of elements.
     *
     * @return count of elements.
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }

    /**
     * Method to get Array of String searchResultsList and get Text values from each element.
     *
     * @return Array of searchResultsList text.
     */
    public List<String> getSearchResults() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchResult);
            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }
}
