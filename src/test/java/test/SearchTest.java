package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultsPage;

import java.util.List;

public class SearchTest extends BaseTest{

    /**
     * Precondition
     * - Open Browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Login with valid credentials
     * - Verify Home Page is loaded
     * - Enter "HR" into SearchField
     * - Press Enter on keyboard
     * - Verify SearchResult Page is loaded
     * - Verify resultsList contains 10 items
     * - Verify each item contains searchTerm
     *
     * PostCondition:
     * - Close browser
     */
    @Test
    public void basicSearchTest() {
        HomePage homePage = loginPage.login("max.nazarets.tst@gmail.com","makrusnet123");
        String searchTerm = "HR";

        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not loaded");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(),"SearchResultsPage is not loaded");
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10, "Count of searchResultsList is not equal 10");

        List<String> searchResultsList = searchResultsPage.getSearchResults();

        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm " + searchTerm + " not found in:\n" + searchResult);
        }
    }
}
