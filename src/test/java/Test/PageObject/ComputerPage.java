package Test.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ComputerPage {
    private WebDriver driver;
    private WebElement notebookButton;
    public ComputerPage(WebDriver driver){
        this.driver = driver;
        notebookButton = driver.findElement(By.xpath("//li//a[contains(.,\"Ноутбуки\")]"));
    }
    public boolean goToNotebookPage(String title){

        new WebDriverWait(driver,10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        notebookButton.click();
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
