package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.WebAssert;
import utils.WebUtils;
import utils.WebWait;

public class LoginPage {

    private WebDriver driver;
    public WebUtils wUtil;
    public WebWait wWait;
    public WebAssert webAssert;

    private static final String loginJira_URL = "http://soft.it-hillel.com.ua:8080/login.jsp";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(this.driver);
        this.wWait = new WebWait(this.driver);
        this.webAssert = new WebAssert(wUtil);
    }

    public void login(String loginUser, String passwordUser) {
        // запустить броузер и перейти по адресу
        //this.driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        this.driver.get(loginJira_URL);
        //full-open browser window
        this.driver.manage().window().maximize();
        //LoginPage loginPage = new LoginPage(this.driver);
        enterLogin(loginUser);
        enterPassword(passwordUser);
        clickSubmit();
        // ожидание появления на странице текста "System Dashboard" отображаемого при успешном логине
        wWait.waitForTextPresent("System Dashboard");
        // ожидание после выполнения
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        // закрываем окно браузера
        //        driver.close();
    }


    public void enterLogin(String loginUser) {
        wUtil.findByXpath("//input[@id='login-form-username']").sendKeys(loginUser);
        //driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys(loginUser);
        //$x("//input[@id='login-form-username']")
        //driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("studinskyi");
    }

    public void enterPassword(String passwordUser) {
        wUtil.findByXpath("//input[@id='login-form-password']").sendKeys(passwordUser);
        //driver.findElement(By.xpath("//input[@id='login-form-password']")).sendKeys(passwordUser);
        //driver.findElement(By.xpath("//input[@id='login-form-password']")).sendKeys("dima_st");
    }

    public void clickSubmit() {
        wUtil.findByXpath("//input[@id='login-form-submit']").click();
        //driver.findElement(By.xpath("//input[@id='login-form-submit']")).click();
    }
}
