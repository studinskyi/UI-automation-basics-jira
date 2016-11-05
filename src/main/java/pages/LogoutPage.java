package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebAssert;
import utils.WebUtils;
import utils.WebWait;

public class LogoutPage {
    private WebDriver driver;
    public WebUtils wUtil;
    public WebWait wWait;
    public WebAssert webAssert;

    private static final String logoutJira_URL = "http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa";

    private static final String xpath_headersUserFullName = "//*[@id='header-details-user-fullname']";
    private static final String xpath_buttonLogOut = "//*[@id='log_out']";

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(this.driver);
        this.wWait = new WebWait(this.driver);
        this.webAssert = new WebAssert(wUtil);
    }

    public void logout() {
        driver.get(logoutJira_URL);
        //driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");
        WebElement logoutList = wWait.waitWebElement(xpath_headersUserFullName, 5);
        logoutList.click();
        WebElement LogoutButton = wWait.waitWebElement(xpath_buttonLogOut, 10);
        LogoutButton.click();
        //driver.findElement(By.xpath("//*[@id='log_out']")).click();

        // ожидание появления на странице текста "You are now logged out" отображаемого при успешном логауте
        wWait.waitForTextPresent("You are now logged out");
        //        // ожидание после выполнения
        //        try {
        //            Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

}
