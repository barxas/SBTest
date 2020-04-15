package Test.PageObject;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class YandexSearch {
    private WebDriver driver;
    private WebElement marketButton;

    public YandexSearch(WebDriver driver){
        this.driver = driver;
        marketButton = driver.findElement(By.xpath("//*[@data-id= \"market\"]"));
    }

    public boolean goToMarketPage(String title)  {

        new WebDriverWait(driver,10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        marketButton.click();
        boolean bool =false;
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for(String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(title)) {
                bool = true;
                break;
            }
        }
        return bool;
    }
}
