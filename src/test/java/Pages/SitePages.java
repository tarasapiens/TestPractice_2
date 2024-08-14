package Pages;

import ConfigHelper.ConfigHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SitePages {

    private final WebDriver driver;
    private final By calendarMonth = By.xpath("//span[@class='ui-datepicker-month']");
    private final By calendarYear = By.xpath("//span[@class='ui-datepicker-year']");
    private final By calendarField = By.id("g1065-selectorenteradate");
    private final By calendarRightNext = By.xpath("//a[@title='Next']");


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

    public void ClickCalendars(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathCalendars()));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public void SetDateCalendar(String month, String day, String year){
        driver.findElement(calendarField).click();

        while (true){
            String currentMonth = driver.findElement(calendarMonth).getText();
            String currentYear = driver.findElement(calendarYear).getText();
            if (currentMonth.equals(month) && currentYear.equals(year)) {
                break;
            }
            driver.findElement(calendarField).click();
            }
        driver.findElement(By.xpath("//table//a[text()='" + day + "']")).click();
    }



}
