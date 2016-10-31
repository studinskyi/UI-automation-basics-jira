package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

    private WebDriver driver;
    private static final String logoutJiraURL = "http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa";

    private static final String webElem_headersUserFullName = "//*[@id='header-details-user-fullname']";
    private static final String webElem_buttonLogOut = "//*[@id='log_out']";


    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        driver.get(logoutJiraURL);
        //driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");
        WebElement logout = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(webElem_headersUserFullName)));

        logout.click();
        WebElement LogoutButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(webElem_buttonLogOut)));
        LogoutButton.click();
        //driver.findElement(By.xpath("//*[@id='log_out']")).click();

    }

}
