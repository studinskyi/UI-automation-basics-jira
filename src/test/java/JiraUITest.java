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
    private final static String newReporterName = "Artur Pilyuk";

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
        // 1. log in to jira
        loginPage.login(loginUser, passwordUser);

        // 2.  проверка Assert-тов
        System.out.println("wUtil.elementIsEnabled(\"//*[@id='header-details-user-fullname']\", 10) = " + loginPage.wWait.isElementEnabled("//*[@id='header-details-user-fullname']", 10));
        loginPage.webAssert.assertIsElementEnabled("//*[@id='header-details-user-fullname']", 10);
        loginPage.webAssert.assertTitleContainsText("System Dashboard"); //проверка наличия в заголовке подстроки
        loginPage.webAssert.assertPageContainsText("Welcome to JIRA");

        System.out.println("loginTest " + getCurrenDateTimeString());
        System.out.println("loginTest - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "login", dependsOnMethods = "loginTest")
    public void logoutTest() {
        // 1. log in to jira
        logoutPage.logout();

        // 2.  проверка Assert-тов
        loginPage.webAssert.assertTitleContainsText("Logout - Jira"); //проверка наличия в заголовке подстроки

        System.out.println("logoutTest " + getCurrenDateTimeString());
        System.out.println("logoutTest - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "issue", dependsOnMethods = "loginTest")
    //@Test(dependsOnMethods = "loginTest")
    public void createIssue() {
        // 1. create new testing issue
        createIssuePage.createIssue();

        // 2.  проверка Assert-тов
        System.out.println("wUtil.elementIsEnabled(\"//*[@id='key-val']\", 10) = " + loginPage.wWait.isElementEnabled("//*[@id='key-val']", 10));
        createIssuePage.webAssert.assertIsElementEnabled("//*[@id='key-val']", 10);
        createIssuePage.webAssert.assertIsElementEnabled("//*[@data-issue-key='" + createIssuePage.issueKey + "']", 10);
        createIssuePage.webAssert.assertTitleContainsText(updateIssuePage.issueKey); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        createIssuePage.webAssert.assertTextPresent("//*[@id='summary-val']",10, createIssuePage.textSummaryIssue); // наличие на странице строки

        // 3. deleteting new test issue
        createIssuePage.deleteIssue();

        System.out.println("createIssue " + getCurrenDateTimeString());
        System.out.println("createIssue - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "update", dependsOnMethods = "loginTest")
    public void updateReporterInIssue() {
        // 1. обновление поля Reporter в Issue
        updateIssuePage.updateReporterInIssue(newReporterLogin, newReporterName);

        // 2. проверка Assert-тов
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        System.out.println("driver.getTitle() = " + driver.getTitle());
        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        //Assert.assertEquals(driver.getTitle().contains(updateIssuePage.issueKey), true); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        updateIssuePage.webAssert.assertTitleContainsText(updateIssuePage.issueKey); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        updateIssuePage.webAssert.assertIsElementEnabled("//*[@id='issue_summary_reporter_a.a.piluck']", 10);
        //Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);

        // 3. deleteting new test issue
        updateIssuePage.deleteIssue();

        System.out.println("updateReporterInIssue " + getCurrenDateTimeString());
        System.out.println("updateReporterInIssue - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "update", dependsOnMethods = "loginTest")
    public void addCommentToIssue() {
        // 1. добавление комментария в Issue
        String textNewComment = "add comment UI for lr11 " + getCurrenDateTimeString();
        updateIssuePage.addCommentToIssue(textNewComment);

        // 2. проверка Assert-тов
        String xPath_newComment = "//div[@id='issue_actions_container']/div[1]/div[1]/div[2]";
        //String textToCampare = updateIssuePage.wWait.waitWebElement(assert_xPath, 10).getText();
        //System.out.println("driver.getTitle() = " + driver.getTitle());
        //System.out.println(assert_xPath + ".getText() = " + textToCampare);
        //Assert.assertEquals(driver.getTitle().contains(updateIssuePage.issueKey), true); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        updateIssuePage.webAssert.assertTitleContainsText(updateIssuePage.issueKey); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        updateIssuePage.webAssert.assertIsElementEnabled(xPath_newComment, 10); // наличие элемента контейнера комментариев
        //Assert.assertEquals(textToCampare, textNewComment); // равенство текста добавленного коментария, исходной формулировке
        updateIssuePage.webAssert.assertTextPresent(xPath_newComment, 10, textNewComment); // равенство текста добавленного коментария, исходной формулировке

        // 3. deleteting new test issue
        updateIssuePage.deleteIssue();

        System.out.println("addCommentToIssue " + getCurrenDateTimeString());
        System.out.println("addCommentToIssue - thread id: " + Thread.currentThread().getId());
    }

    @Test(groups = "update", dependsOnMethods = "loginTest")
    public void updatePriorityInIssue() {
        // 1. добавление комментария в Issue
        updateIssuePage.updatePriorityInIssue();

        //        // 2. проверка Assert-тов
        //        String assert_xPath = "//div[@id='issue_actions_container']/div[1]/div[1]/div[2]";
        //        String textToCampare = updateIssuePage.wWait.waitWebElement(assert_xPath, 10).getText();
        //        System.out.println("driver.getTitle() = " + driver.getTitle());
        //        System.out.println(assert_xPath + ".getText() = " + textToCampare);
        //        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        //        Assert.assertEquals(driver.getTitle().contains(updateIssuePage.issueKey), true); //проверка наличия в заголовке подстроки ключа issueKey "QAAUT-1676"
        //        updateIssuePage.webAssert.assertIsElementEnabled(assert_xPath, 10); // наличие элемента контейнера комментариев
        //        Assert.assertEquals(textToCampare, textNewComment); // равенство текста добавленного коментария, исходной формулировке
        //        //Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);
        //        //        try {
        //        //            Thread.sleep(5000);
        //        //        } catch (InterruptedException e) {
        //        //            e.printStackTrace();
        //        //        }

//        // 3. deleteting new test issue
//        updateIssuePage.deleteIssue();

        System.out.println("updatePriorityInIssue " + getCurrenDateTimeString());
        System.out.println("updatePriorityInIssue - thread id: " + Thread.currentThread().getId());
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
        return formatDate.format(d);
    }

}

