package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebAssert;
import utils.WebUtils;

public class LogoutPage {

    private WebDriver driver;
    public WebUtils wUtil;
    public WebAssert webAssert;

    private static final String logoutJira_URL = "http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa";

    private static final String xpath_headersUserFullName = "//*[@id='header-details-user-fullname']";
    private static final String xpath_buttonLogOut = "//*[@id='log_out']";

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(driver);
        this.webAssert = new WebAssert(wUtil);
    }

    public void logout() {
        driver.get(logoutJira_URL);
        //driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");
        WebElement logoutList = wUtil.waitWebElement(xpath_headersUserFullName, 5);
        //        WebElement logout = (new WebDriverWait(driver, 5))
        //                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath_headersUserFullName)));
        logoutList.click();
        WebElement LogoutButton = wUtil.waitWebElement(xpath_buttonLogOut, 10);
        //        WebElement LogoutButton = (new WebDriverWait(driver, 10))
        //                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath_buttonLogOut)));
        LogoutButton.click();
        //driver.findElement(By.xpath("//*[@id='log_out']")).click();

    }

}
