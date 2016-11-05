package utils;

import com.sun.deploy.trace.Trace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Объект содержит в себе всего одну функцию until, которая представляет собой правило,
//по которому стоит ожидать элемент. Существует набор уже определенных правил для ожиданий.
// Находятся они в классе ExpectedConditions:
// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("table"))));
//Часто используемые правила:
//    titleContains(String title)
//    presenceOfElementLocated(By locator)
//    presenceOfAllElementsLocatedBy(By locator)
//    visibilityOfElementLocated(By locator)
//    visibilityOf(WebElement element)
//    textToBePresentInElement(By locator, String text)
//    invisibilityOfElementLocated(By locator)
//    invisibilityOfElementWithText(By locator, String text)
//    elementToBeClickable(By locator)
//    stalenessOf(WebElement element)
//    alertIsPresent()
//    Есть возможность использовать негативные правила:
//    wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.id("link"))));
public class WebWait {
    private WebDriver driver;

    public WebWait(WebDriver driver) {
        this.driver = driver;
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

    public WebElement waitElementClickableByXpath(String xPath) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

    }

    public void waitForTextPresent(String text) {
        int waitRetryDelayMs = 100; //шаг итерации (задержка)
        int timeOut = 500;  //время тайм маута
        boolean first = true;

        System.out.println("wWait.waitForTextPresent(\"" + text + "\")");

        for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs) {
            if (milliSecond > timeOut * 1000) {
                //Trace.WriteLine("Timeout: Text '" + text + "' is not found ", "Document");
                System.out.println("Timeout: Text '" + text + "' is not found ");
                break; //если время ожидания закончилось (элемент за выделенное время не был найден)
            }

            if (driver.getPageSource().contains(text)) {
                if (!first)
                    System.out.println("Text is found: '" + text + "'");
                //Trace.WriteLine("Text is found: '" + text + "'", "Document");
                break; //если элемент найден
            }

            if (first)
                System.out.println("Waiting for text is present: '" + text + "'");
            //Trace.WriteLine("Waiting for text is present: '" + text + "'", "Document");

            first = false;
            try {
                Thread.sleep(waitRetryDelayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
