package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebAssert;
import utils.WebUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateIssuePage {

    public String issueKey = "";
    private WebDriver driver;
    private WebUtils wUtil;
    public WebAssert webAssert;
    private static final String issueForm_URL = "http://soft.it-hillel.com.ua:8080/browse/";

    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(driver);
        this.webAssert = new WebAssert(wUtil);
    }

    public void createIssue() {
        // 1. create issue
        createNewBug();
        //        WebElement createButton = wUtil.waitWebElement("//*[@id='create_link']", 5);
        //        createButton.click();
        //
        //        WebElement issueType = wUtil.waitWebElement("//*[@id='issuetype-field']", 5);
        //        issueType.clear();
        //        issueType.sendKeys("Bug");
        //        issueType.sendKeys(Keys.ENTER);
        //
        //
        //        try {
        //            Thread.sleep(3000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }


        // 2. add summary to new issue
        addSummaryToIssue();
        //        WebElement summary = wUtil.waitWebElement("//*[@id='summary']", 10);
        //        summary.clear();
        //        summary.sendKeys("UI_test_Jira_lr11 " + getCurrenDateTimeString());

        // 3. add assignee to new issue
        addAssigneeToIssue();
        //        WebElement assignee = wUtil.findByXpath("//*[@id='assignee-field']");
        //        assignee.clear();
        //        assignee.sendKeys("studinskyi", Keys.ENTER);

        // 4. get IsueKey of new issue
        getIssueKeyOfNewIssue();
        //        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //        issueKey = wUtil.findByXpath("//*[@id='aui-flag-container']/div/div/a").getAttribute("data-issue-key");
        //        System.out.println("IsueKey of new issue = " + issueKey);

        // 5. открытие формы созданного Issue
        openFormCreatedIssue();

        // ожидание после выполнения
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        // 5. deleteting new test issue
        //        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        //        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        //        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        //        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();
    }


    public void createNewBug() {
        // create issue
        WebElement createButton = wUtil.waitWebElement("//*[@id='create_link']", 5);
        createButton.click();

        WebElement issueType = wUtil.waitWebElement("//*[@id='issuetype-field']", 5);
        issueType.clear();
        issueType.sendKeys("Bug");
        issueType.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addSummaryToIssue() {
        WebElement summary = wUtil.waitWebElement("//*[@id='summary']", 10);
        summary.clear();
        summary.sendKeys("UI_test_Jira_lr11 " + getCurrenDateTimeString());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addAssigneeToIssue() {
        WebElement assignee = wUtil.findByXpath("//*[@id='assignee-field']");
        assignee.clear();
        assignee.sendKeys("studinskyi", Keys.ENTER);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getIssueKeyOfNewIssue() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        issueKey = wUtil.findByXpath("//*[@id='aui-flag-container']/div/div/a").getAttribute("data-issue-key");
        System.out.println("IsueKey of new issue = " + issueKey);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openFormCreatedIssue() {
        // 5. обновление поля Reporter в Issue
        driver.get(issueForm_URL + issueKey);
        //driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //        issueKey = wUtil.findByXpath("//*[@id='aui-flag-container']/div/div/a").getAttribute("data-issue-key");
        //        System.out.println("IsueKey of new issue = " + issueKey);
        //        try {
        //            Thread.sleep(500);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    public void deleteIssue() {
        // deleteting new test issue
        driver.get(issueForm_URL + issueKey);
        //driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String getCurrenDateTimeString() {
        // для возможности последующего просмотра командой history
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //FileManager.executedOperations.put(formatDate.format(d), FileManager.currentCommand);
        return formatDate.format(d);
    }


}
