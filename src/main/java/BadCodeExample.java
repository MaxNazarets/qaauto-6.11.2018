import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://google.com");
        WebElement searchField = webDriver.findElement(By.xpath("//input[@name='q']"));
        searchField.sendKeys("Selenium", Keys.ENTER);
        List<WebElement> resultsList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Results on screen are :" + resultsList.size());

//        for (int i = 0; i < resultsList.size(); i++) {
//            if (resultsList.isEmpty()) {
//                System.out.println("Here is Empty");
//            }
//            else {
//                System.out.println(resultsList.get(i).getText());
//            }
//        }

        webDriver.quit();
    }
}
