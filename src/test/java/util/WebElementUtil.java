package util;

import org.openqa.selenium.*;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.awaitility.Awaitility.await;

public class WebElementUtil {

    WebElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    public boolean isPresent(By by) {
        return isNotEmpty(getElements(by));
    }

    public boolean isNotPresent(By by) {
        return !isPresent(by);
    }

    public void waitForElementIsPresent(By by) {
        await().until(() -> isPresent(by));
    }

    public void waitForElementIsNotPresent(By by) {
        await().until(() -> isNotPresent(by));
    }

    public void waitForElementDisplayed(WebElement element) {
        await().until(element::isDisplayed);
    }

    public void waitForElementIsNotDisplayed(By by) {
        await().until(() -> !getElement(by).isDisplayed());
    }

    public void waitForElementEnabled(WebElement element) {
        await().until(element::isEnabled);
    }

    public void waitUntilHasValueAttribute(WebElement element, String value) {
        await().until(() -> element.getAttribute("value").equals(value));
    }

    public void waitForElementPresentAndDisplayed(By by) {
        waitForElementIsPresent(by);
        waitForElementDisplayed(driver.findElement(by));
    }

    public void waitForElementAttributeHasValue(By locator, String attribute, String value) {
        waitForElementIsPresent(locator);
        await().until(() -> getElement(locator).getAttribute(attribute).equals(value));
    }

    public boolean isElementPresentAndDisplayed(By by) {
        return isPresent(by) && getElement(by).isDisplayed();
    }
}
