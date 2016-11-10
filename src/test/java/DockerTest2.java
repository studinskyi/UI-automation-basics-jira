import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by n1ck on 11/8/16.
 */
public class DockerTest2 {
    URL hostURL = null;

    @BeforeTest
    public void setUp(){
        try {
            hostURL = new URL("http://172.17.64.20:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }



    @Test(invocationCount = 6, threadPoolSize = 2)
    public void docTest(){

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        //  capability.setVersion("49.0.1");
        capability.setPlatform(Platform.LINUX);
        WebDriver driver = new RemoteWebDriver(hostURL, capability);
        driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
        driver.get("http://tavriav.org");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Random rn = new Random();
        int randInt = rn.nextInt();
        try {
            FileUtils.copyFile(screen, new File("/home/n1ck/tmp/scr"+randInt+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("firefox");

        driver.quit();
    }

    @Test(invocationCount = 6, threadPoolSize = 2)
    public void docTestChrome(){
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        //  capability.setVersion("49.0.1");
        capability.setPlatform(Platform.LINUX);
        WebDriver driver = new RemoteWebDriver(hostURL, capability);
        driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
        driver.get("http://tavriav.ua");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Random rn = new Random();
        int randInt = rn.nextInt();
        try {
            FileUtils.copyFile(screen, new File("/home/n1ck/tmp/scr"+randInt+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("chrome");
        driver.quit();
    }


}