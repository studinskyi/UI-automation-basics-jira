package utils;

import org.testng.Assert;

public class WebAssert {

    private WebUtils wUtil;

    public WebAssert(WebUtils wUtil) {
        this.wUtil = wUtil;
    }

    // проверяет наличие xPath элемента на странице
    public void assertIsElementEnabled(String xPath, long timeOutInSeconds) {
        Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
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
