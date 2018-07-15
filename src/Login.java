
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {

    private static WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void CheckPrice(){
        driver.get("http://sant-market.com.ua/grohe-smesitel-dlya-rakoviny-standartniy-36498");
        String text = driver.findElement(By.xpath("//div[@class=\"right-side\"]//div[@class=\"price\"]")).getText();
        String b = text.replaceAll("[\\$|а-я||A-Z|;|'| ]", "");
        Assert.assertEquals(b, "1599");


    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}




