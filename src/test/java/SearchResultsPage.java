import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;

    @FindBy(css = "ul.search-results__list li.search-result")
    private List<WebElement> searchResultsList;

    public boolean isPageLoaded() {
        return searchFilterBar.isDisplayed()
                && driver.getTitle().contains("Search | LinkedIn")
                && driver.getCurrentUrl().contains("/search/results");
    }

    public int getSearchResultsCount() {
        return searchResultsList.size();
    }

//    public List<WebElement> getSearchResults2() {
//        for (int i = 0; i <= getSearchResultsCount(); i++) {
//            searchResultsList.get(i).getText();
//        }
//        return searchResultsList;
//}

    public List<String> getSearchResults() {
        List<String> searchResultsLists = new ArrayList<String>();
        for (WebElement searchResult : searchResultsList) {
            String searchResultText = searchResult.getText();
            searchResultsLists.add(searchResultText);
        }
        return searchResultsLists;
    }
}
