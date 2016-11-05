package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtils {

    private WebDriver driver;

    public WebUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    //find web element on the page By.XPath
    public WebElement findByXpath(String xPath) {
        return this.driver.findElement(By.xpath(xPath));
    }
    //find web element on the page By.id
    public WebElement findById(String idElement) {
        return this.driver.findElement(By.id(idElement));
    }
    //find web element on the page By.name
    public WebElement findByName(String nameElement) {
        return this.driver.findElement(By.name(nameElement));
    }

    public void scrollPageUp(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, -250);");
    }




}
