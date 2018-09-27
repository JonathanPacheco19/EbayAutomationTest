package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EbayPage {

    String baseURL="https://www.ebay.com/";
    public  WebDriver webDriver;

    public EbayPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.webDriver= driver;
    }

    @FindBy(id = "gh-ac")
    public WebElement textSearch;

    @FindBy(id = "gh-btn")
    public WebElement btnSearch;

    @FindBy(className = "rcnt")
    public WebElement textTotalResulta;

    @FindBy(xpath = "//div[@id='DashSortByContainer']")
    public WebElement dashSortElement;

    @FindBy(id = "SortMenu")
    public WebElement hiddenSortDropdown;


    @FindBy(xpath = "//ul[@id='ListViewInner']//li/h3/a")
    public List<WebElement> productListNames;

    @FindBy(xpath = "//ul[@id='ListViewInner']//li//ul/li[@class='lvprice prc']")
    public List<WebElement> productListPrices;

    @FindBy(xpath = "//ul[@id='ListViewInner']//li")
    public List<WebElement> productList;

    public void start(){
        webDriver.get(baseURL);
    }

}