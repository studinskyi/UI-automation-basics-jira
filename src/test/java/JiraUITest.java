import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.IssuePage;
import pages.LoginPage;
import pages.LogoutPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class JiraUITest {

    protected WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private IssuePage issuePage;

    private final static String loginUser = "studinskyi";
    private final static String passwordUser = "dima_st";

    @BeforeTest
    public void beforeStartTests() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        issuePage = new IssuePage(driver);

        //        // запустить броузер и перейти по адресу
        //        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        //        //full-open browser window
        //        driver.manage().window().maximize();
        //        LoginPage loginPage = new LoginPage(driver);
        //        loginPage.enterLogin(loginUser);
        //        loginPage.enterPassword(passwordUser);
        //        loginPage.clickSubmit();
    }

    @Test
    public void loginTest() {
        String eTitle = "Log in - JIRA";
        String aTitle = "";
        loginPage.login(loginUser, passwordUser);
        //        // получить значение у тайтла страницы
        //        aTitle = driver.getTitle();
        //        // выполняем проверку
        //        assertEquals(aTitle, eTitle);
        //
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        // закрываем окно браузера
        //        driver.close();
    }

    @Test(dependsOnMethods = "loginTest")
    public void logoutTest() {

        logoutPage.logout();
        //        // получить значение у тайтла страницы
        //        aTitle = driver.getTitle();
        //        // выполняем проверку
        //        assertEquals(aTitle, eTitle);
        //
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        // закрываем окно браузера
        //        driver.close();
    }

    @Test(dependsOnMethods = "loginTest")
    public void createIssue() {
       issuePage.createIssue();
        //        // получить значение у тайтла страницы
        //        aTitle = driver.getTitle();
        //        // выполняем проверку
        //        assertEquals(aTitle, eTitle);
        //
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleTest() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String eTitle = "Meet Guru99";
        String aTitle = "";

        // запустить firefox и перейти по адресу
        driver.get("http://www.guru99.com/");
        // разворачивает окно браузера
        driver.manage().window().maximize();
        // получить значение у тайтла страницы
        aTitle = driver.getTitle();

        // выполняем проверку
        assertEquals(aTitle, eTitle);

        // закрываем окно браузера
        driver.close();
    }

    @Test
    public void loginSuccessFull() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String eTitle = "Log in - JIRA";
        String aTitle = "";

        // запустить броузер и перейти по адресу
        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        // разворачивает окно браузера
        driver.manage().window().maximize();
        // получить значение у тайтла страницы
        aTitle = driver.getTitle();
        // выполняем проверку
        assertEquals(aTitle, eTitle);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin("studinskyi");
        loginPage.enterPassword("dima_st");
        loginPage.clickSubmit();

        //        driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("studinskyi");
        //        driver.findElement(By.xpath("//input[@id='login-form-password']")).sendKeys("dima_st");
        //        driver.findElement(By.xpath("//input[@id='login-form-submit']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // закрываем окно браузера
        driver.close();

        //driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("login");
    }


    @Test
    public void createIssueInJiraTest() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String eTitle = "Log in - JIRA";
        String aTitle = "";

        // запустить броузер и перейти по адресу
        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");

        //full-open browser window
        driver.manage().window().maximize();


        //Search element
        driver.findElement(By.xpath("//*[@id='create_link']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        //выбираем правильную группу
////        Select select =
////                new Select(driver.findElement(By.xpath("//*[@id='project-field']")));
////        select.selectByVisibleText("QAAutomation2 (QAAUT)");
////        select.selectByValue("QAAutomation2 (QAAUT)");
//
////        new Select(driver.findElement(By.id("project-field"))).selectByVisibleText("QAAutomation2 (QAAUT)");
//
//        driver.manage().timeouts().implicitlyWait(1000L, TimeUnit.MILLISECONDS);
//
//        WebElement webElement2 = driver.findElement(By.xpath("//*[@id='project-field']"));
//        webElement2.click();
//        webElement2.sendKeys("QAAutomation2 (QAAUT)");
//        webElement2.submit();
////        driver.findElement(By.id("//*[@id='qaautomation2-(qaaut)-1538']")).click();
//
//
//        String issueName = "ISSUE at: " + dateFormat.format(date);
//        //Search element
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.findElement(By.xpath("//*[@id='summary']")).sendKeys(issueName);
//        driver.findElement(By.xpath("//*[@id='reporter-field']")).sendKeys(myLogin);
//
//        driver.findElement(By.xpath("//*[@id='description']")).sendKeys(issueName);
//        driver.findElement(By.xpath("//*[@id='create-issue-submit']")).click();
//
//        //все команды поиска автоматически становятся ожидаемыми
//        driver.manage().timeouts().implicitlyWait(1000L, TimeUnit.MILLISECONDS);
//
//        if (driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div")).isEnabled()) {
//
//
//            WebElement webElement = driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div"));
//            System.out.println(" webElement.getText() = " + webElement.getText());
//            System.out.println("webElement.getTagName() = " + webElement.getTagName());
//        }
//
//        System.out.println("You successfully have created new ISSUE by NAME: " + issueName);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // закрываем окно браузера
        driver.close();

        //driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("login");
    }


    @AfterTest
    public void afterEndTests() {

        driver.close();
        driver.quit();

    }

}

