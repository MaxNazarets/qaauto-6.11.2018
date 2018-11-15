import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        String searchTerm = "Selenium";
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        searchField.sendKeys(searchTerm, Keys.ENTER);
        List<WebElement> resultsList = driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Results on screen are :" + resultsList.size());
//
//        for (int i = 0; i < resultsList.size(); i++) {
//            System.out.println(resultsList.get(i).getText());
//
//            if (resultsList.contains(searchTerm)) {
//                System.out.println("searchTerm found");
//            } else {
//                System.out.println("searchTerm is not found");
//            }
//        }

        //for each WebElement 'result' in List of WebElements 'resultsList' print text

        for (WebElement result : resultsList) {
            String resultText = result.getText();

            if (resultText.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("searchTerm " + searchTerm + " found in block:\n" + resultText);
            } else {
                System.out.println("searchTerm " + searchTerm + "is NOT found in block:\n" + resultText);
            }
        }

        driver.quit();
    }
}
