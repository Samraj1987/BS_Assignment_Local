package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverActions {
    private static DriverActions driverActions;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private DriverActions(String driverName) {
        switch (driverName) {
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        this.actions = new Actions(driver);
    }

    public static DriverActions getChromeInstance() {
        if (driverActions == null) {
            return new DriverActions("Chrome");
        }
        return driverActions;
    }

    public static DriverActions getFFInstance() {
        if (driverActions == null) {
            return new DriverActions("Firefox");
        }
        return driverActions;
    }

    public static DriverActions getEdgeInstance() {
        if (driverActions == null) {
            return new DriverActions("Edge");
        }
        return driverActions;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void maximizeDriverWindow() {
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public List<WebElement> getElements(WebElement element, String lookUpType, String name) {
        List<WebElement> elements = null;
        try {
            switch (lookUpType) {
                case "tagName":
                    elements = element.findElements(By.tagName(name));
                    break;
                case "xpath":
                    elements = element.findElements(By.xpath(name));
                    break;
                case "className":
                    elements = element.findElements(By.className(name));
                    break;
            }
        } catch (Exception ex) {
            return null;
        }
        return elements;
    }

    public WebElement getElement(String lookUpType, String lookUpValue) {
        WebElement result = null;

        try {
            switch (lookUpType) {
                case "tagName":
                    result = driver.findElement(By.tagName(lookUpValue));
                    break;
                case "xpath":
                    result = driver.findElement(By.xpath(lookUpValue));
                    break;
                case "className":
                    result = driver.findElement(By.className(lookUpValue));
                    break;
                case "css":
                    result = driver.findElement(By.cssSelector(lookUpValue));
                    break;
            }
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    public List<WebElement> getElements(String lookUpType, String lookUpValue) {
        List<WebElement> result = null;

        try {
            switch (lookUpType) {
                case "tagName":
                    result = driver.findElements(By.tagName(lookUpValue));
                    break;
                case "xpath":
                    result = driver.findElements(By.xpath(lookUpValue));
                    break;
                case "className":
                    result = driver.findElements(By.className(lookUpValue));
                    break;
                case "css":
                    result = driver.findElements(By.cssSelector(lookUpValue));
                    break;
            }
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    public void moveToElementAndClick(WebElement element) {
        this.actions.moveToElement(element).click().perform();
    }

    public void scrollDownToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        this.actions.scrollToElement(element);
    }

    public WebElement getElement(WebElement element, String lookUpType, String lookUpValue) {

        WebElement result = null;
        //this.moveToElementAndClick(element);
        try {
            switch (lookUpType) {
                case "tagName":
                    result = element.findElement(By.tagName(lookUpValue));
                    break;
                case "xpath":
                    result = element.findElement(By.xpath(lookUpValue));
                    break;
                case "className":
                    result = element.findElement(By.className(lookUpValue));
                    break;
            }
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    public WebElement getElementBasedOnText(List<WebElement> elements, String text, String lookUpType, String lookUpValue) {
        WebElement result = null;
        try {
            for (WebElement element : elements) {
                if (lookUpType != null && lookUpValue != null) {
                    wait.until(ExpectedConditions.visibilityOf(element));
                    result = this.getElement(element, lookUpType, lookUpValue);
                } else {
                    result = element;
                }
                if (result != null && this.getText(result).equalsIgnoreCase(text)) {
                    break;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    public void sendText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void pressKey(WebElement element, Keys key) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(key);
    }


    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException ex) {
            this.moveToElementAndClick(element);
        }
    }

    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getHrefAttributeValue(WebElement element) {
        return getAttributeValue(element, "href");
    }

    public String getSrcAttributeValue(WebElement element) {
        return getAttributeValue(element, "src");
    }

    public String getValueAttributeValue(WebElement element) {
        return getAttributeValue(element,"value");
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        String result = null;
        switch (attributeName.toLowerCase()) {
            case "href":
                result = element.getAttribute("href");
                break;
            case "src":
                result = element.getAttribute("src");
                break;
            case "value":
                result = element.getAttribute("value");
                break;
        }
        return result;
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void quitDriver() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        this.waitUntilPageLoaded();
        return driver.getCurrentUrl();
    }

    public void waitUntilPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(30L)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilUrlChangesTo(String url){
        wait.until(ExpectedConditions.urlToBe(url));
    }

}
