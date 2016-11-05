package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WebAssert {
    private WebDriver driver;
    public WebUtils wUtil;
    public WebWait wWait;

    public WebAssert(WebUtils wUtil) {
        this.driver = wUtil.getDriver();
        this.wUtil = wUtil;
        this.wWait = new WebWait(this.driver);
    }

    // проверяет наличие xPath элемента на странице
    public void assertIsElementEnabled(String xPath, long timeOutInSeconds) {
        Assert.assertEquals(this.wWait.isElementEnabled(xPath, timeOutInSeconds), true);
    }

    // проверяет наличие текста
    public void assertTextPresent(String xPath, long timeOutInSeconds) {
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }

    // проверяет наличие текста у какого-то UI элемента
    public void assertText(String xPath, long timeOutInSeconds) {
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }

    // проверяет корректность заголовка страницы
    public void assertTitle(String xPath, long timeOutInSeconds) {
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }

    // проверяет значение у поля ввода
    public void assertValue(String xPath, long timeOutInSeconds) {
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }


}
