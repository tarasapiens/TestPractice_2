package Pages;

import ConfigHelper.ConfigHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SitePages {

    private final WebDriver driver;

    public SitePages(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenSite(){
        driver.get(ConfigHelper.getBaseURL());
    }

    public void ClickJSDelay(){ driver.findElement(By.xpath(ConfigHelper.getXPathJSDelays())).click();}
    public void ClickFormField(){driver.findElement(By.xpath(ConfigHelper.getXpathFormField())).click();}

    public void ClickStart(){driver.findElement(By.xpath("//button[@id='start']")).click();}

    public void ClickPopup(){driver.findElement(By.xpath(ConfigHelper.getXpathPopup())).click();}

    public void ClickSlider(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathSlider()));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

}
