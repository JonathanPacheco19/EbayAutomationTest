package FeaturepImplementations;

import BaseConfig.BaseUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;

public class EbayPageHook extends BaseUtil {

    private BaseUtil baseconf;

    public EbayPageHook(BaseUtil baseconf) {
        this.baseconf = baseconf;
    }

    @Before
    public void InitializeActions(){

        System.out.println("---Opening Chrome-----");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\PruebaTesting\\chromedriver.exe");
        baseconf.webDriver = new ChromeDriver();
    }


    @After
    public void CloseActions(Scenario scenario){
        System.out.println("---Closing-----");
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
            System.out.println(scenario.getName());
            try{
                Thread.sleep(5000);
                baseconf.webDriver.quit();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
