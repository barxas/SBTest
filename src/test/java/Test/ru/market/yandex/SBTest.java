package Test.ru.market.yandex;

import Test.PageObject.ComputerPage;
import Test.PageObject.MarketPage;
import Test.PageObject.NotebookPage;
import Test.PageObject.YandexSearch;
import Test.WebSettings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class SBTest extends WebSettings{
    private String url = "https://yandex.ru/";
    //private String url = "https://market.yandex.ru/catalog--noutbuki/54544/list?hid=91013&local-offers-first=0&onstock=1";


    @Test
    public void yandexMarletTest() throws InterruptedException {
        try {
            driver.get(url);
        }catch (Exception e){
            Assert.fail("Не удалось открыть страницу " + url);
        }
        try {
            YandexSearch searchPage = new YandexSearch(driver);
            Steps.goToMarketPage(searchPage,"Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов");
        }catch (Exception e){
            Assert.fail("Не удалось открыть страницу Яндекс.Маркет");
        }
        try {
            MarketPage marketPage = new MarketPage(driver);
            Steps.goToComputerPage(marketPage,"Компьютерная техника — купить на Яндекс.Маркете");
        }catch (Exception e){
            Assert.fail("Не удалось открыть страницу Компьютерная техника");
        }
        try {
            ComputerPage computerPage = new ComputerPage(driver);
            Steps.goToNotebookPage(computerPage,"Ноутбуки — купить на Яндекс.Маркете");
        }catch (Exception e){
            Assert.fail("Не удалось открыть страницу Ноутбуки");
        }

        try {

            NotebookPage notebookPage = new NotebookPage(driver);

            List<String> manufactures = new ArrayList<>();
            manufactures.add("HP");
            manufactures.add("Lenovo");

            Steps.setFilters(notebookPage,"Производитель","10000","30000",manufactures);

        }
        catch (Exception e){
            Assert.fail("Не удалось установить фильтры");
        }
        try {
            NotebookPage notebookPage = new NotebookPage(driver);
            Steps.checkName(notebookPage,notebookPage.getFirstElement());
        }catch (Exception e){
            Assert.fail("Ошибка при сравнении имен товаров");
        }
        }

    }
//
