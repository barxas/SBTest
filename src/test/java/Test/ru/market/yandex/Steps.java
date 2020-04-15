package Test.ru.market.yandex;

import Test.PageObject.ComputerPage;
import Test.PageObject.MarketPage;
import Test.PageObject.NotebookPage;
import Test.PageObject.YandexSearch;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.util.List;

public class Steps {
    @Step("Переход на страницу {title}")
    public static void goToMarketPage(YandexSearch searchPage, String title) {
        Assert.assertTrue("Error! page "+title+" didn't open",searchPage.goToMarketPage(title));
    }
    @Step("Переход на страницу {title}")
    public static void goToComputerPage(MarketPage marketPage, String title){
        Assert.assertTrue("Error! page "+title+" didn't open",marketPage.goToComputerPage(title));
    }
    @Step("Переход на страницу {title}")
    public static void goToNotebookPage(ComputerPage computerPage, String title){
        Assert.assertTrue("Error! page "+title+" didn't open",computerPage.goToNotebookPage(title));
    }
    @Step("Установить фильты")
    public static void setFilters(NotebookPage notebookPage,String filter, String minPrice, String maxPrice, List<String> setManufactures) throws InterruptedException {
        notebookPage.setPriceFilters(minPrice,maxPrice);

        System.out.println("before manufactures");
        notebookPage.setFilters(filter,setManufactures);
        System.out.println("after manufactures");
        notebookPage.changeAmountOfElements();
    }
    @Step("Проверить имя продукта")
    public static void checkName(NotebookPage notebookPage,String productName){
        notebookPage.findProduct(productName);
        Assert.assertTrue("Error! different names",notebookPage.checkSearchProduct(productName));
    }

}
