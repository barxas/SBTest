package Test.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MarketPage {
    private WebDriver driver;
    private WebElement computerButton;

    public MarketPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://market.yandex.ru/");
        computerButton = driver.findElement(By.xpath("//div[@data-zone-name=\"category-link\"]//span[contains(.,\"Компьютеры\")]"));
    }

    public boolean goToComputerPage(String title){

        new WebDriverWait(driver,10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        computerButton.click();
        boolean bool =false;
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for(String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(title))
                bool = true;
        }
        return bool;
    }
}