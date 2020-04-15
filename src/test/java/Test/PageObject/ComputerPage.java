package Test.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


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
        if(driver.getTitle().contains(title))
            return true;
        else
            return false;

    }
}
