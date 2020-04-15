package Test.PageObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotebookPage {
    private WebDriver driver;

    private String filtersID;
    private String selectedFiltersID;

    private List<String> selectedFiltersListID;

    private WebElement searchField;
    private WebElement searchButton;
    private WebElement minPriceElement;
    private WebElement maxPriceElement;

    private List<WebElement> listOfElements;
    private List<WebElement> listOfFilters;

    private Map<String, String> mapOfManufactures;

    public void createMapManufactures(){
        for (WebElement listOfFilter : listOfFilters) {
            mapOfManufactures.put(listOfFilter.getText(), getID(listOfFilter));
        }
    }

    public String getID(WebElement we){
        JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(we.findElement(By.xpath("./../../..")).getAttribute("data-zone-data")).getAsJsonObject();
        return objectFromString.get("filterId").toString().replaceAll("\"","");

    }
    public NotebookPage(WebDriver driver){
        this.driver = driver;
        listOfFilters = driver.findElements(By.xpath("//legend[@class=\"ShXb4FpS5R\"]"));

        selectedFiltersListID = new ArrayList<>();


        mapOfManufactures = new HashMap<>();
        searchButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        searchField = driver.findElement(By.xpath("//input[@id=\"header-search\"]"));
        maxPriceElement = driver.findElement(By.xpath("//input[@name=\"Цена до\"]"));
        minPriceElement = driver.findElement(By.xpath("//input[@name=\"Цена от\"]"));


    }
    public void setFilters(String filter, List<String> setManufactures) throws InterruptedException {
        createMapManufactures();
        for (int i = 0; i < mapOfManufactures.size(); i++) {
            selectedFiltersID = mapOfManufactures.get(filter);
            if (mapOfManufactures.containsKey(filter)) {
                List<WebElement> manufacturesList = driver.findElements(By.xpath("//legend[@class=\"ShXb4FpS5R\" and contains(.,\"" + filter + "\")]/..//div[@class=\"LhMupC0dLR\"]"));
                for (String setManufacture : setManufactures) {
                    for (WebElement element : manufacturesList) {
                        if (element.getText().equals(setManufacture)) {
                            selectedFiltersListID.add(element.findElement(By.xpath("./../input")).getAttribute("id").split("_")[1]);
                            element.click();
                            elementsAreReady();
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    public void elementsAreReady() throws InterruptedException {
        boolean bool = true;
        try {
            WebElement filterCheck = driver.findElement(By.xpath("//div[@class=\"n-pager i-bem n-pager_js_inited\"]"));
            JsonParser jsonParser = new JsonParser();
            JsonObject objectFromString = jsonParser.parse(filterCheck.getAttribute("data-bem")).getAsJsonObject();
            filtersID = objectFromString.getAsJsonObject("n-pager").get("link").getAsJsonObject().getAsJsonObject("params").get("glfilter").toString();
            if(filtersID.contains(selectedFiltersID)) {
                System.out.println(filtersID.contains(selectedFiltersID)+"  "+filtersID+"  "+selectedFiltersID);
                for (int i = 0; i < selectedFiltersListID.size(); i++) {
                    System.out.println(i);
                    if(!filtersID.contains(selectedFiltersListID.get(i))){
                        System.out.println(filtersID.contains(selectedFiltersListID.get(i))+"  "+filtersID+"  "+selectedFiltersListID.get(i));
                        bool = false;
                    }
                }
            }
            else
                bool = false;
        }catch (Exception e){
            bool = false;
        }
        if(!bool){
            Thread.sleep(5000);
        }
    }

    public void changeAmountOfElements() throws InterruptedException {
        pageIsReady(driver);
        elementsAreReady();
        WebElement showButton = driver.findElement(By.xpath("//button[@type=\"button\" and @role=\"listbox\"]"));
        showButton.click();
        driver.findElement(By.xpath("//div[@class=\"select__item\"]")).click();
    }
    public void setPriceFilters(String minPrice,String maxPrice){
        pageIsReady(driver);
        minPriceElement.click();
        minPriceElement.sendKeys(minPrice);

        maxPriceElement.click();
        maxPriceElement.sendKeys(maxPrice);
    }
    public String getFirstElement() throws InterruptedException {
        elementsAreReady();
        listOfElements = driver.findElements(By.xpath("//div[@class=\"layout layout_type_search i-bem\"]//h3[@class=\"n-snippet-card2__title\"]"));

        return listOfElements.get(0).getText();
    }
    public void findProduct(String productName){
        pageIsReady(driver);
        searchField.click();
        searchField.sendKeys(productName);
        searchButton.click();
    }

    public void pageIsReady(WebDriver driver){
        new WebDriverWait(driver,10).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public boolean checkSearchProduct(String productName){
        pageIsReady(driver);

        listOfElements = driver.findElements(By.xpath("//div[@class=\"layout layout_type_search i-bem\"]//h3[@class=\"n-snippet-card2__title\"]"));
        boolean bool = false;
        for (WebElement we : listOfElements) {
            if (we.getText().equals(productName)) {
                System.out.println(productName+"   "+we.getText());
                bool = true;
            }
        }
        return bool;
    }
}
