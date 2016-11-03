package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateIssuePage {

    public String issueKey = "";
    private WebDriver driver;
    private WebUtils wUtil;


    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(driver);
    }

    public void createIssue() {
        // 1. create issue
        WebElement createButton = wUtil.waitWebElement("//*[@id='create_link']", 5);
        createButton.click();

        WebElement issueType = wUtil.waitWebElement("//*[@id='issuetype-field']", 5);
        issueType.clear();
        issueType.sendKeys("Bug");
        issueType.sendKeys(Keys.ENTER);

        // 2. add summary to new issue
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement summary = wUtil.waitWebElement("//*[@id='summary']", 10);
        summary.clear();
        summary.sendKeys("UI_test_Jira_lr11 " + getCurrenDateTimeString());

        // 3. add assignee to new issue
        WebElement assignee = wUtil.findByXpath("//*[@id='assignee-field']");
        assignee.clear();
        assignee.sendKeys("studinskyi", Keys.ENTER);

        // 4. get IsueKey of new issue
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        issueKey = wUtil.findByXpath("//*[@id='aui-flag-container']/div/div/a").getAttribute("data-issue-key");
        System.out.println(issueKey);

        // 5. deleteting new test issue
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();
    }

    public String getCurrenDateTimeString() {
        // для возможности последующего просмотра командой history
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //FileManager.executedOperations.put(formatDate.format(d), FileManager.currentCommand);
        return formatDate.format(d);
    }


}
