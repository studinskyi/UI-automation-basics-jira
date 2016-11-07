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

    // проверяет наличие текста у какого-то UI элемента (в указанном xPath элементе на странице)
    public void assertTextPresent(String xPath, long timeOutInSeconds, String TextToFind) {
        String textToCampare = wWait.waitWebElement(xPath, 10).getText();
        Assert.assertEquals(textToCampare, TextToFind);
    }

    // проверяет наличие текста на странице
    public void assertPageContainsText(String TextToFind) {
        Assert.assertEquals(driver.getPageSource().contains(TextToFind), true);
    }

    // проверяет что заголовок страницы содержит подстроку
    public void assertTitleContainsText(String TextToFind) {
        //System.out.println("driver.getTitle() = " + driver.getTitle());
        Assert.assertEquals(driver.getTitle().contains(TextToFind), true); //проверка наличия в заголовке подстроки TextToFind
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }

    // проверяет значение у поля ввода
    public void assertValue(String xPath, long timeOutInSeconds) {
        //Assert.assertEquals(this.wUtil.isElementEnabled(xPath, timeOutInSeconds), true);
    }


}
