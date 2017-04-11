import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DockerTest {

    URL hostURL = null;

    @BeforeTest
    public void setUp() {
        try {
            hostURL = new URL("http://localhost:4444/wd/hub");
            //hostURL = new URL("http://172.17.0.1:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //@Test(invocationCount = 6, threadPoolSize = 2)
    public void docTestChrome() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        //  capability.setVersion("54.0.2840.90");
        capability.setPlatform(Platform.LINUX);
        WebDriver driver = new RemoteWebDriver(hostURL, capability);
        //driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://google.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Random rn = new Random();
        int randInt = rn.nextInt();
        String pathToFile = "d:\\QA_Hillel\\DockerTest\\" + "chromeScreenshot" + randInt + "_" + getCurrentDateTimeToNameFile() + ".png";
        //String pathToFile2 = "/home/ds/tmp/" + "chromeScreenshot" + randInt + "_" + getCurrentDateTimeToNameFile() + ".png";
        try {
            FileUtils.copyFile(screen, new File(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // closing and quit RemoteWebDriver
        driver.close();
        driver.quit();

        System.out.println("created screenshot Chrome " + getCurrentDateTimeString());
        System.out.println("Chrome screenshot - " + pathToFile);
    }

    @Test
    //@Test(invocationCount = 6, threadPoolSize = 2)
    public void docTestFirefox() {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        //capability.setVersion("49.0.2");
        capability.setPlatform(Platform.LINUX);
        WebDriver driver = new RemoteWebDriver(hostURL, capability);
        //driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        driver.get("https://google.com");
        //driver.get("https://www.mozilla.org");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Random rn = new Random();
        int randInt = rn.nextInt();
        String pathToFile = "d:\\QA_Hillel\\DockerTest\\" + "firefoxScreenshot" + randInt + "_" + getCurrentDateTimeToNameFile() + ".png";
        //String pathToFile2 = "/home/ds/tmp/" + "firefoxScreenshot" + randInt + "_" + getCurrentDateTimeToNameFile() + ".png";
        try {
            FileUtils.copyFile(screen, new File(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // closing and quit RemoteWebDriver
        driver.close();
        driver.quit();

        System.out.println("created screenshot Firefox " + getCurrentDateTimeString());
        System.out.println("Firefox screenshot - " + pathToFile);
    }

    public String getCurrentDateTimeToNameFile() {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        return formatDate.format(d);
    }

    public String getCurrentDateTimeString() {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return formatDate.format(d);
    }
}