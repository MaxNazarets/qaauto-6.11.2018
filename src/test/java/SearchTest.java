import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTest {

    WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.login("max.nazarets.test@gmail.com","test12345678");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

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
        String searchTerm = "hr";
        Assert.assertTrue(homePage.isPageLoaded(),"Home page is not loaded");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(),"SearchResults pahe is not loaded");

//        WebElement list, size(), getResultCount, assert сравнивать числа
//        цикл, вернуть результат в тест, а в тесте сделать ассерт

    }
}
