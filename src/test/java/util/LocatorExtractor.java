package util;

import lombok.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.*;

import java.util.Optional;

public class LocatorExtractor {

    @SneakyThrows
    public static String getLocatorStringFromWebElement(WebElement element) {
        Object proxyOrigin = FieldUtils.readField(element, "h", true);
        Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
        Object findBy = FieldUtils.readField(locator, "by", true);
        return Optional.of(findBy)
                .map(Object::toString)
                .orElseThrow(() -> new IllegalStateException("Unable to retrieve By locator string representation from WebElement object"));
    }

    public static By getLocator(WebElement element) {
        val split = getLocatorStringFromWebElement(element).split(": ");
        val selector = split[0].trim();
        val value = split[1].trim();
        switch (selector) {
            case "By.id":
                return By.id(value);
            case "By.className":
                return By.className(value);
            case "By.tagName":
                return By.tagName(value);
            case "By.xpath":
                return By.xpath(value);
            case "By.cssSelector":
                return By.cssSelector(value);
            case "By.linkText":
                return By.linkText(value);
            case "By.name":
                return By.name(value);
            case "By.partialLinkText":
                return By.partialLinkText(value);
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
    }
}
