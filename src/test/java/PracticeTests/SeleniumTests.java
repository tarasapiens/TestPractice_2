package PracticeTests;

import Pages.SitePages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    private WebDriver driver;
    private SitePages page;

    public static WebElement getShadow(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
    }

    public SearchContext expandRootElement(WebElement element) {
        SearchContext shadowRoot = (SearchContext) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot", element);
        return shadowRoot;
    }


    @BeforeEach
    public void SetDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(20));
        driver.manage().window().maximize();
        page = new SitePages(driver);
    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void CheckJavaScriptDelays() throws InterruptedException {
        page.OpenSite();
        page.ClickJSDelay();
        page.ClickStart();

        Thread.sleep(Long.parseLong("15000"));

        WebElement shadowHost = driver.findElement(By.cssSelector("#delay"));
        SearchContext shadowRoot = expandRootElement(shadowHost);
        WebElement shadowRootText = shadowRoot.findElement(By.tagName("div"));
        shadowRootText.getText();


//        WebElement shadowHost = driver.findElement(By.cssSelector("#delay"));
//        SearchContext shadowRoot = shadowHost.getShadowRoot();
//        String text = shadowRoot.findElement(By.tagName("div")).getText();
//        System.out.println(text);

//
//        WebElement shadowHost = driver.findElement(By.cssSelector("#delay"));
//        WebElement shadowRoot = getShadow(shadowHost, driver);
//        shadowRoot.findElement(By.tagName("div"));
    }

    @Test
    public void CheckFormFields() {

        page.OpenSite();
        page.ClickFormField();

        WebElement inputElement = driver.findElement(By.cssSelector("input[id='name-input']"));


        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(ofSeconds(40))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(ElementNotInteractableException.class);

        wait.until(
                d -> {
                    WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver).executeScript(
                            "return arguments[0].shadowRoot", inputElement);
                    WebElement shadowElement = shadowRoot.findElement(By.cssSelector("div"));
                    shadowElement.sendKeys("123");

                    return true;
                });
    }

    @Test
    public void alertPopup() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='alert']")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void confirmPopupCancel() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='confirm']")).click();
        driver.switchTo().alert().dismiss();

        String confirmTextCancel =
                driver.findElement(By.xpath("//div[@id='columns']//p[@id='confirmResult']")).getText();
        assertEquals("Cancel it is!", confirmTextCancel);
    }

    @Test
    public void confirmPopupOk() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='confirm']")).click();
        driver.switchTo().alert().accept();

        String confirmTextOk =
                driver.findElement(By.xpath("//div[@id='columns']//p[@id='confirmResult']")).getText();
        assertEquals("OK it is!", confirmTextOk);
    }

    @Test
    public void promptPopupOk() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='prompt']")).click();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();

        String promptTextOk =
                driver.findElement(By.xpath("//div[@id='columns']//p[@id='promptResult']")).getText();
        assertEquals("Nice to meet you, Test!", promptTextOk);
    }

    @Test
    public void promptPopupCancel() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='prompt']")).click();
        driver.switchTo().alert().dismiss();

        String promptCancel =
                driver.findElement(By.xpath("//div[@id='columns']//p[@id='promptResult']")).getText();
        assertEquals("Fine, be that way...", promptCancel);
    }

    @Test
    public void checkSlider() {
        page.OpenSite();
        page.ClickSlider();

        WebElement slider = driver.findElement(By.xpath("//input[@id='slideMe']"));
        slider.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(slider, 350, 0).click().build().perform();
    }

    @Test
    public void checkCalendars(){
        page.OpenSite();
        page.ClickCalendars();
        page.SetDateCalendar("August", "17", "2024");
    }

    @Test
    public void checkSimpleModals() throws InterruptedException {
        page.OpenSite();
        page.ClickModals();
        driver.findElement(By.xpath("//button[@id='simpleModal']")).click();

        Thread.sleep(3000L);

        WebElement element = (driver.findElement
                (By.xpath("//div[@id='popmake-1318']//div[@class='pum-content popmake-content']//p")));

        String text = element.getText();
        assertEquals("Hi, I’m a simple modal.", text);
    }

    @Test
    public void checkFormsModal() {
        page.OpenSite();
        page.ClickModals();
        driver.findElement(By.xpath("//button[@id='formModal']")).click();
        page.SendMessageModal("Sergey", "test@mail.ru", "Test message");
    }

    @Test
    public void checkTables(){
        page.OpenSite();
        page.ClickTables();
        String orangesPrice = page.getItemPrice("Oranges");
        assertEquals(orangesPrice, "$3.99", "Price Oranges incorrect");

        String laptopPrice = page.getItemPrice("Laptop");
        assertEquals(laptopPrice, "$1200.00", "Price Laptop incorrect");

        String marblesPrice = page.getItemPrice("Marbles");
        assertEquals(marblesPrice, "$1.25", "Price Marbles incorrect");
    }

    @Test
    public void checkNewWindow(){
        page.OpenSite();
        page.ClickWindow();
        page.ClickNewWindow();
        page.SwitchToNewWindow();
        assertEquals(page.getPageTitle(), "automateNow | The Best FREE Software Online Training Platform");
    }

    @Test
    public void checkNewTab() {
        page.OpenSite();
        page.ClickWindow();
        page.ClickNewTab();
        assertEquals(page.getPageTitle(), "Window Operations | Practice Automation");
    }

    @Test
    public void checkHover(){
        page.OpenSite();
        page.ClickHover();
        page.doHover();
        String hoverText = page.getHoverText();
        assertEquals(hoverText, "You did it!");
    }




}
