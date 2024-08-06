package PracticeTests;

import Pages.SitePages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    private WebDriver driver;
    private SitePages page;

    public static WebElement getShadow(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
    }

    @BeforeEach
    public void SetDriver() {
        driver = new ChromeDriver();
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


        Thread.sleep(13000);

//        WebElement shadowHost = driver.findElement(By.cssSelector("#delay"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Object shadowRoot = js.executeScript("return arguments[0].shadowRoot", shadowHost);
//
//        List<WebElement> shadowElements = (List<WebElement>) js.executeScript("return arguments[0].querySelectorAll('div')", shadowRoot);
//
//        for (WebElement element : shadowElements) {
//            System.out.println("Element text: " + element.getText());

        WebElement shadowHost = driver.findElement(By.cssSelector("#delay"));
        WebElement shadowRoot = getShadow(shadowHost, driver);
        shadowRoot.findElement(By.tagName("div"));

    }

    @Test
    public void CheckFormFields() {

        page.OpenSite();
        page.ClickFormField();

        WebElement inputElement = driver.findElement(By.cssSelector("input[id='name-input']"));


        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(40))
                        .pollingEvery(Duration.ofMillis(300))
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
    public void promptPopup() {
        page.OpenSite();
        page.ClickPopup();
        driver.findElement(By.xpath("//button[@id='prompt']")).click();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();

        String promptTextOk =
                driver.findElement(By.xpath("//div[@id='columns']//p[@id='promptResult']")).getText();
        assertEquals("Nice to meet you, Test!", promptTextOk);

    }
}
