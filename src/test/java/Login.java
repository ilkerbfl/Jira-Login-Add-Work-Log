import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Login {

    private static WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Login() {
        driver.get("http://jira.hillel.it:8080/login.jsp");
        WebElement loginTextBox = driver.findElement(By.id("login-form-username"));
        loginTextBox.click();
        loginTextBox.sendKeys("webinar5");
        WebElement passwordTextBox = driver.findElement(By.id("login-form-password"));
        passwordTextBox.click();
        passwordTextBox.sendKeys("webinar5");
        WebElement submitButton = driver.findElement(By.id("login-form-submit"));
        submitButton.click();
        assertEquals(driver.getTitle(), "System Dashboard - Hillel IT School JIRA");

    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}


