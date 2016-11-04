import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CreateIssuePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.UpdateIssuePage;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class JiraUITest {

    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private CreateIssuePage createIssuePage;
    private UpdateIssuePage updateIssuePage;


    private final static String loginUser = "studinskyi";
    private final static String passwordUser = "dima_st";
    private final static String newReporterLogin = "a.a.piluck";
    //private final static String newReporterName = "Artur Pilyuk";

    @BeforeTest(groups = {"login", "issue", "update"})
    public void beforeStartTests() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        createIssuePage = new CreateIssuePage(driver);
        updateIssuePage = new UpdateIssuePage(driver);

        //        // запустить броузер и перейти по адресу
        //        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        //        //full-open browser window
        //        driver.manage().window().maximize();
        //        LoginPage loginPage = new LoginPage(driver);
        //        loginPage.enterLogin(loginUser);
        //        loginPage.enterPassword(passwordUser);
        //        loginPage.clickSubmit();
    }

    @Test(groups = "login")
    public void loginTest() {
        //        String eTitle = "Log in - JIRA";
        //        String aTitle = "";
        loginPage.login(loginUser, passwordUser);
        // ожидание после выполнения
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("wUtil.elementIsEnabled(\"//*[@id='header-details-user-fullname']\", 10) = " + loginPage.wUtil.isElementEnabled("//*[@id='header-details-user-fullname']", 10));
        loginPage.webAssert.assertIsElementEnabled("//*[@id='header-details-user-fullname']", 10);
        //Assert.assertEquals(wUtil.isElementEnabled("//*[@id='header-details-user-fullname']", 10), true);
        //        // получить значение у тайтла страницы
        //        aTitle = driver.getTitle();
        //        assertEquals(aTitle, eTitle);

        //проверяем, какой поток
        System.out.println("loginTest " + getCurrenDateTimeString());
        System.out.println("loginTest - thread id: " + Thread.currentThread().getId());
        //        // закрываем окно браузера
        //        driver.close();
    }

    @Test(groups = "login", dependsOnMethods = "loginTest")
    public void logoutTest() {
        logoutPage.logout();
        //        // получить значение у тайтла страницы
        //        aTitle = driver.getTitle();
        //        // выполняем проверку
        //        assertEquals(aTitle, eTitle);
        //
        // ожидание после выполнения
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //проверяем, какой поток
        System.out.println("logoutTest " + getCurrenDateTimeString());
        System.out.println("logoutTest - thread id: " + Thread.currentThread().getId());
        //        // закрываем окно браузера
        //        driver.close();
    }

    @Test(groups = "issue", dependsOnMethods = "loginTest")
    //@Test(dependsOnMethods = "loginTest")
    public void createIssue() {
        // 1. create new testing issue
        createIssuePage.createIssue();

        // 2.  проверка Assert-тов
        //<a class="issue-link" data-issue-key="QAAUT-1737" href="/browse/QAAUT-1737" id="key-val" rel="15168">QAAUT-1737</a>
        System.out.println("wUtil.elementIsEnabled(\"//*[@id='key-val']\", 10) = " + loginPage.wUtil.isElementEnabled("//*[@id='key-val']", 10));
        createIssuePage.webAssert.assertIsElementEnabled("//*[@id='key-val']", 10);
        Assert.assertEquals(driver.getTitle().contains(createIssuePage.issueKey), true); //проверка наличия в заголовке подстроки "QAAUT-1676"
        createIssuePage.webAssert.assertIsElementEnabled("//*[@data-issue-key='" + createIssuePage.issueKey + "']", 10);

        //Assert.assertEquals(wUtil.isElementEnabled("//*[@id='header-details-user-fullname']", 10), true);


        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        //        System.out.println("driver.getTitle() = " + driver.getTitle());
        //        //<span class="user-hover" id="issue_summary_reporter_a.a.piluck" rel="a.a.piluck">
        //        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        //        Assert.assertEquals(driver.getTitle().contains(issueKey), true); //проверка наличия в заголовке подстроки "QAAUT-1676"
        //        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);

        // 3. deleteting new test issue
        createIssuePage.deleteIssue();
        //        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        //        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        //        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        //        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();

        //проверяем, какой поток
        System.out.println("createIssue " + getCurrenDateTimeString());
        System.out.println("createIssue - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "update", dependsOnMethods = "loginTest")
    public void updateReporterInIssue() {
        // 1. обновление поля Reporter в Issue
        updateIssuePage.updateReporterInIssue(newReporterLogin);

        // 2. проверка Assert-тов
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        System.out.println("driver.getTitle() = " + driver.getTitle());
        //<span class="user-hover" id="issue_summary_reporter_a.a.piluck" rel="a.a.piluck">
        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        Assert.assertEquals(driver.getTitle().contains(updateIssuePage.issueKey), true); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        updateIssuePage.webAssert.assertIsElementEnabled("//*[@id='issue_summary_reporter_a.a.piluck']", 10);
        //Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);

        //        // 3. deleteting new test issue
        updateIssuePage.deleteIssue();
        //        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        //        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        //        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        //        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();

        //проверяем, какой поток
        System.out.println("updateReporterInIssue " + getCurrenDateTimeString());
        System.out.println("updateReporterInIssue - thread id: " + Thread.currentThread().getId());
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        //        System.out.println("driver.getTitle() = " + driver.getTitle());
        //
        //        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);
    }

    @Test(groups = "update", dependsOnMethods = "loginTest")
    public void addCommentToIssue() {
        // 1. добавление комментария в Issue
        updateIssuePage.addCommentToIssue();

        //        // 6. проверка Assert-тов
        //        String activityModuleValue = waitForWebElementUntilPresenceOfElementLocated("//*[@id='activitymodule']/div[2]" ).getText();
        //        String sample = "added a comment - Now\n" + newComment;
        //        if (activityModuleValue.contains(sample)) {
        //            returnValue = true;
        //        }
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        //        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        //        System.out.println("driver.getTitle() = " + driver.getTitle());
        //        //<span class="user-hover" id="issue_summary_reporter_a.a.piluck" rel="a.a.piluck">
        //        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        //        Assert.assertEquals(driver.getTitle().contains(issueKey), true); //проверка наличия в заголовке подстроки "QAAUT-1676"
        //        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);

        //        // 7. deleteting new test issue
        //        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        //        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        //        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        //        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();

        //проверяем, какой поток
        System.out.println("addCommentToIssue " + getCurrenDateTimeString());
        System.out.println("addCommentToIssue - thread id: " + Thread.currentThread().getId());
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

        //        driver.close();
        //        driver.quit();

    }

    public String getCurrenDateTimeString() {
        // для возможности последующего просмотра командой history
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //FileManager.executedOperations.put(formatDate.format(d), FileManager.currentCommand);
        return formatDate.format(d);
    }

}

