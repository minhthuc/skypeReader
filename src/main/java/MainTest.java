import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\minht\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://web.skype.com/en/");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("minhthuc229@gmail.com");
        driver.findElement(By.id("signIn")).click();
        WebElement password = driver.findElement(By.name("passwd"));
        password.sendKeys("Minh5595845");
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(10000);
        List<WebElement> navMenu = driver.findElements(By.tagName("swx-recent-item"));
        for (WebElement m : navMenu){
//            System.out.println(m.findElement(By.className("topic")).getText());
            if (m.findElement(By.className("topic")).getText().equals("Ho tro ABTPAY - VQS")){
                WebElement element = m.findElement(By.className("topic"));
                element.click();
            System.out.println("clicked");}
        }
        Thread.sleep(2000);
        List<WebElement> contents = driver.findElements(By.className("content"));
        System.out.println(contents.size());
        for (WebElement m : contents){
            System.out.println("--------------------------------");
            System.out.println(m.findElement(By.tagName("p")).getText());
        }
    }
}
