import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AppTest {
	
	protected WebDriver driver;

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
    public void InputSuccessFull() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String eTitle = "Log in - JIRA";
        String aTitle = "";

        // запустить firefox и перейти по адресу
        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        // разворачивает окно браузера
        driver.manage().window().maximize();
        // получить значение у тайтла страницы
        aTitle = driver.getTitle();

        // выполняем проверку
        assertEquals(aTitle, eTitle);

        driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("login");


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // закрываем окно браузера
        driver.close();

        //driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("login");



    }
}

