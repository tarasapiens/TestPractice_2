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
    private final By modalName = By.xpath("//input[@id='g1051-name']");
    private final By modalEmail = By.xpath("//input[@id='g1051-email']");
    private final By modalMessage = By.xpath("//textarea[@id='contact-form-comment-g1051-message']");
    private final By modalSubmit = By.xpath("//button[@class='pushbutton-wide']");


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

    public void ClickModals(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathModals()));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public void SendMessageModal(String name, String email, String message){

        driver.findElement(modalName).sendKeys(name);
        driver.findElement(modalEmail).sendKeys(email);
        driver.findElement(modalMessage).sendKeys(message);
        driver.findElement(modalSubmit).click();
    }

    public void ClickTables(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathTables()));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public String getItemPrice(String item){
        return driver.findElement(By.xpath("//td[text()='"+ item + "']/following-sibling::td")).getText();
    }



}
