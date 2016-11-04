package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtils {

    private WebDriver driver;

    public WebUtils(WebDriver driver) {
        this.driver = driver;
    }

    //find web element on the page
    public WebElement findByXpath(String xPath) {
        // wUtil.findByXpath("");
        return this.driver.findElement(By.xpath(xPath));
    }

    //wait for webElement until that appears( wait during 10 sec)
    public WebElement waitWebElement(String xPath, long timeOutInSeconds) {
        // wUtil.waitWebElement("", 5);
        return (new WebDriverWait(driver, timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    //return true if the Element is available on the page
    public boolean isElementEnabled(String xPath, long timeOutInSeconds) {
        return waitWebElement(xPath, timeOutInSeconds).isEnabled();
    }
}
