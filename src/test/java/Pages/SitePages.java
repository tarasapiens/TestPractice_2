package Pages;

import ConfigHelper.ConfigHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

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
    private final By newWindowBtn = By.xpath("//button[@onclick='newWindow()']");
    private final By pageTitle = By.xpath("//h2/strong[text()='Start learning']");
    private final By newTabBtn = By.xpath("//button[@onclick='newTab()']");
    private final By hover = By.id("mouse_over");
    private final By map = By.tagName("canvas");


    public SitePages(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenSite() {
        driver.get(ConfigHelper.getBaseURL());
    }

    public void ClickJSDelay() {
        driver.findElement(By.xpath(ConfigHelper.getXPathJSDelays())).click();
    }

    public void ClickFormField() {
        driver.findElement(By.xpath(ConfigHelper.getXpathFormField())).click();
    }

    public void ClickStart() {
        driver.findElement(By.xpath("//button[@id='start']")).click();
    }

    public void ClickPopup() {
        driver.findElement(By.xpath(ConfigHelper.getXpathPopup())).click();
    }

    public void ClickSlider() {
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathSlider()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public void ClickCalendars() {
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathCalendars()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public void SetDateCalendar(String month, String day, String year) {
        driver.findElement(calendarField).click();

        while (true) {
            String currentMonth = driver.findElement(calendarMonth).getText();
            String currentYear = driver.findElement(calendarYear).getText();
            if (currentMonth.equals(month) && currentYear.equals(year)) {
                break;
            }
            driver.findElement(calendarField).click();
        }
        driver.findElement(By.xpath("//table//a[text()='" + day + "']")).click();
    }

    public void ClickModals() {
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathModals()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public void SendMessageModal(String name, String email, String message) {

        driver.findElement(modalName).sendKeys(name);
        driver.findElement(modalEmail).sendKeys(email);
        driver.findElement(modalMessage).sendKeys(message);
        driver.findElement(modalSubmit).click();
    }

    public void ClickTables() {
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathTables()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public String getItemPrice(String item) {
        return driver.findElement(By.xpath("//td[text()='" + item + "']/following-sibling::td")).getText();
    }

    public void ClickWindow() {
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathWindow()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }
    public void ClickHover(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathHover()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void ClickNewWindow() {
        driver.findElement(newWindowBtn).click();
    }

    public void SwitchToNewWindow() {
        String currentWindow = getWindowHandle();
        Set<String> handles = getWindowHandles();
        Iterator<String> iter = handles.iterator();
        String newWindow = null;

        while (iter.hasNext()) {
            newWindow = iter.next();
            if (!currentWindow.equals(newWindow)) {
                driver.switchTo().window(newWindow);
            }
        }
    }

    public void ClickNewTab(){
        driver.findElement(newTabBtn).click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void hoverOverElement(By locator){
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public SitePages doHover(){
        hoverOverElement(hover);
        return this;
    }

    public String getHoverText(){
        return driver.findElement(hover).getText();
    }

    public void clickGestures(){
        WebElement element = driver.findElement(By.xpath(ConfigHelper.getXpathGestures()));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);

    }

    public void dragAndDropByOffset(By locator, int x, int y){
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.dragAndDropBy(element, x, y).perform();
    }

    public void dragMap(int x, int y){
        dragAndDropByOffset(map, x, y);
    }






}
