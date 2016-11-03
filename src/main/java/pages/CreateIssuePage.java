package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateIssuePage {

    public String issueKey = "";
    private WebDriver driver;


    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createIssue() {
        // 1. create issue
        WebElement createButton = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='create_link']")));
        createButton.click();

        WebElement issueType = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='issuetype-field']")));

        issueType.clear();
        issueType.sendKeys("Bug");

        issueType.sendKeys(Keys.ENTER);

        // 2. add summary to new issue
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement summary = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='summary']")));
        summary.clear();
        summary.sendKeys("UI_test_Jira_lr11 " + getCurrenDateTimeString());

        // 3. add assignee to new issue
        WebElement assignee = driver.findElement(By.xpath("//*[@id='assignee-field']"));
        assignee.clear();
        assignee.sendKeys("studinskyi", Keys.ENTER);

        // 4. get IsueKey of new issue
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        issueKey = driver
                .findElement(By.xpath("//*[@id='aui-flag-container']/div/div/a"))
                .getAttribute("data-issue-key");
        System.out.println(issueKey);

        // 5. deleteting new test issue
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
