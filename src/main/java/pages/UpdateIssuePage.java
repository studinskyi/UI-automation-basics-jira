package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UpdateIssuePage {

    String issueKey = "";
    private WebDriver driver;
    private WebUtils wUtil;


    public UpdateIssuePage(WebDriver driver) {
        this.driver = driver;
        this.wUtil = new WebUtils(driver);
    }

    public void updateReporterInIssue() {
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

        // 5. обновление поля Reporter в Issue
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id='issue_summary_reporter_studinskyi']")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@id='reporter-field']")).sendKeys(Keys.DELETE);
        //driver.findElement(By.xpath("//*[@id='reporter-field']")).sendKeys("a.a.piluck2 ", Keys.ENTER);
        driver.findElement(By.xpath("//*[@id='reporter-field']")).sendKeys("a.a.piluck", Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 6. проверка Assert-тов
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).isEnabled() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled());
        System.out.println("driver.findElement(By.xpath(\"//*[@id='issue_summary_reporter_a.a.piluck']\")).getText() = " + driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).getText());
        System.out.println("driver.getTitle() = " + driver.getTitle());
        //<span class="user-hover" id="issue_summary_reporter_a.a.piluck" rel="a.a.piluck">
        //driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled();
        Assert.assertEquals(driver.getTitle().contains(issueKey), true); //проверка наличия в заголовке подстроки "QAAUT-1676"
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='issue_summary_reporter_a.a.piluck']")).isEnabled(), true);

        // 7. deleteting new test issue
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        wUtil.findByXpath("//*[@id='opsbar-operations_more']/span[1]").click();
        wUtil.findByXpath("//*[@id='delete-issue']/span").click();
        wUtil.findByXpath("//*[@id='delete-issue-submit']").click();

    }

    public void addCommentToIssue() {
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

        // 5/ add comment to issue
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        WebElement comment = wUtil.findByXpath("//*[@id='comment-issue']/span[1]");
        //WebElement comment = driver.findElement(By.xpath("//*[@id='comment-issue']/span[1]"));
        comment.click();
        WebElement addComment = wUtil.waitWebElement("//*[@id='comment']", 10);
        //        WebElement addComment = (new WebDriverWait(driver, 10))
        //                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='comment']")));
        addComment.sendKeys("add comment UI for lr11 " + getCurrenDateTimeString(), Keys.CONTROL, Keys.ENTER);

        //        // 6. проверка Assert-тов
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

    }

    public void updatePriority() {
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        WebElement priority = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='priority-val']")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        priority.click();
        WebElement priorityChange = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='priority-field']")));

        priorityChange.clear();

        priorityChange.sendKeys("Low", Keys.ENTER, Keys.ENTER);

        // TODO assert priority low

    }

    public void getIssueKey() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        issueKey = driver
                .findElement(By.xpath("//*[@id='aui-flag-container']/div/div/a"))
                .getAttribute("data-issue-key");
        System.out.println(issueKey);
    }


    public void updateIssueTitle() {
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        WebElement changeIssueTitle = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='summary-val']")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        changeIssueTitle.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO add assert text bar
        WebElement changeIssueSummary = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='summary']")));
        changeIssueSummary.clear();

        changeIssueSummary.sendKeys(" This title was changed via WebDriver", Keys.ENTER);

    }

    public void deleteIssue() {
        driver.get("http://soft.it-hillel.com.ua:8080/browse/" + issueKey);
        driver.findElement(By.xpath("//*[@id='opsbar-operations_more']/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='delete-issue']/span")).click();
        driver.findElement(By.xpath("//*[@id='delete-issue-submit']")).click();
    }

    public String getCurrenDateTimeString() {
        // для возможности последующего просмотра командой history
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //FileManager.executedOperations.put(formatDate.format(d), FileManager.currentCommand);
        return formatDate.format(d);
    }


}
