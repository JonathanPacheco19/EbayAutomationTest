package FeaturepImplementations;

import BaseConfig.BaseUtil;
import Pages.EbayPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class EbaySearchStepdefs extends BaseUtil {

    private BaseUtil configInfo;
    private EbayPage ebayPage;

    public EbaySearchStepdefs(BaseUtil configInfo) {
        this.configInfo = configInfo;
    }


    @Given("^I navigate to Ebay application$")
    public void webInitNavigation() {
      try{
         // code to implement Page Object Model logique
        ebayPage= new EbayPage(configInfo.webDriver);
        ebayPage.start();
        Thread.sleep(3000);
      }catch(Exception e){
        e.printStackTrace();
      }
    }


    @And("^I search for shoes \"([^\"]*)\" with size \"([^\"]*)\"$")
    public void iSearchForShoesWithSize(String brand, String size) throws Throwable {
      try{
          ebayPage.textSearch.sendKeys("Shoes: "+brand+ " Size: "+size);
          ebayPage.btnSearch.click();
          Thread.sleep(2000);
          // Printing Number of results
          System.out.println("**************************************************************** ");
          System.out.println("El total de  resultados encontrados de la búsqueda es de = "+ebayPage.textTotalResulta.getText()+ "\n");

      }catch(Exception e){
        e.printStackTrace();
      }
    }


    @And("^I order By Price ascendant$")
      public void iOrderByPriceAscendant() throws Throwable {
        try{
            ebayPage.dashSortElement.click();
            Thread.sleep(5000L);
            List<WebElement> sortTypeList=ebayPage.hiddenSortDropdown.findElements(By.tagName("li"));
            for (WebElement li : sortTypeList) {
              if(li!=null)  {
                  if (li.getText().equals("Precio + Envío: más bajo primero")) {
                      li.click();
                      break;
                  }
              }
            }
            Thread.sleep(5000L);
        }catch(Exception e){
          e.printStackTrace();
         }
    }


    @Then("^I assert the (\\d+) results and print them$")
    public void iAssertTheResultsAndPrintThem(int topnumber) throws Throwable {

        List<String> elements = new ArrayList<String>();
        List<String> names = new ArrayList<String>();
        List<String> prices = new ArrayList<String>();

        for(WebElement ele0:ebayPage.productList){
            elements.add(ele0.getAttribute("r"));
        }

        for(WebElement ele1:ebayPage.productListNames){
            names.add(ele1.getAttribute("data-mtdes"));
        }

        for(WebElement ele2:ebayPage.productListPrices){
            prices.add(ele2.getAttribute("innerHTML").replaceAll("\\<.*?>","").trim());
        }


        for(int i=0; i <topnumber; i++){
            //Asserting the order taking the first results according to the parameter
            System.out.println("**************************************************************** ");
            List<WebElement> elements2 = configInfo.webDriver.findElements(By.tagName("r"+elements.get(i)));
            for(WebElement elements1 : elements2)
                    Assert.assertEquals("Element not displayed",elements1.isDisplayed(),true);

            //Printing same first products with their respectives prices
            System.out.println("\nProduct Name : " + names.get(i) + " \n  Price : " + prices.get(i));
        }
        System.out.println("\n");
    }


    @Then("^I order and print products by name ascendant$")
    public void iOrderAndPrintProductsByNameAscendant() throws Throwable {
        ArrayList<String> names = new ArrayList<String>();
        for(WebElement ele1:ebayPage.productListNames){
            names.add(ele1.getAttribute("data-mtdes"));
        }

        System.out.println("\n**********************************");
        System.out.println("Ordered products by name ascendantly");
        Collections.sort(names);
        for(String counter: names){
            System.out.println(counter);
        }

    }

    @Then("^I order and print products by price descendant$")
    public void iOrderAndPrintProductsByPriceDescendant() throws Throwable {
        try{
            ebayPage.dashSortElement.click();
            Thread.sleep(5000L);
            WebElement dropdownElement = ebayPage.hiddenSortDropdown.findElement(By.id("SortMenu"));
            List<WebElement> sortTypeList=dropdownElement.findElements(By.tagName("li"));
            for (WebElement li : sortTypeList) {
                if(li!=null)  {
                    if (li.getText().equals("Precio + Envío: más alto primero")) {
                        li.click();
                        break;
                    }
                }
            }
            Thread.sleep(5000L);
            List<String> names = new ArrayList<String>();
            for(WebElement ele1:ebayPage.productListNames)
                names.add(ele1.getAttribute("data-mtdes"));

            System.out.println(" \n\nProduct Ordered by price in descendant mode ");
            System.out.println(" **************************************************************** ");
            for(int i=0; i <names.size(); i++)
                System.out.println("Product: "+ names.get(i));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
