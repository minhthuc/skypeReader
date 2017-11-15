import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\minht\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://web.skype.com/en/");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("Your Username");
        driver.findElement(By.id("signIn")).click();
        WebElement password = driver.findElement(By.name("passwd"));
        password.sendKeys("YourPassWord");
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(10000);
        List<WebElement> navMenu = driver.findElements(By.tagName("swx-recent-item"));
        for (WebElement m : navMenu){
//            System.out.println(m.findElement(By.className("topic")).getText());
            if (m.findElement(By.className("topic")).getText().equals("ABTPAY - VQS")){
                WebElement element = m.findElement(By.className("topic"));
                element.click();
            System.out.println("clicked");}
        }
        Thread.sleep(2000);
        List<WebElement> contents = driver.findElements(By.className("content"));
        Iterator<WebElement> iterator = contents.iterator();
        List<String> list = new ArrayList<String>();
        while (iterator.hasNext()){
            try{
            String s = iterator.next().findElement(By.tagName("p")).getText();
                try{
                    if (s.length()==0){
                        iterator.remove();
                    }else list.add(s);
                }catch (Exception e){}
            }catch (Exception e){
                iterator.remove();
            }
        }
        List<String> serial = new ArrayList<String>();
        for (String s : list){
            String[] ar = s.split("\n");
            for (String ele : ar){
                try {
                    if(Long.parseLong(ele)>0){
                    serial.add(ele);}
                }catch (Exception e){
                }
            }
        }
        System.out.println("Serials count:" + serial.size());
        for (String s : serial){
            System.out.println(s + " :"+ s.length());
        }
        System.out.println("===============================================");

        for (String s : markFilter(serial)){
            System.out.println(s);
        }
        System.out.println("===============================================");
        for (String s : Filterduplicate(markFilter(serial))){
            System.out.println(s);
        }


    }
    public static String[] markFilter(List<String> sample){
        String[] m = new String[sample.size()];
        for (int i = 0; i <sample.size(); i++){
            m[i] = sample.get(i);
        }
        for (int i = 0 ; i <m.length; i++){
            String tmp = m[i];
            for (int j = i+1; j < m.length; j++){
                if (tmp.equals(m[j])){
                    m[i] = "-";
                    m[j] = "-";
                }
            }
        }return m;
    }
    public static List<String> Filterduplicate(String[] ar){
        List<String> list = new ArrayList<String>();
        for (String a : ar){
            if (!a.equals("-")){
                list.add(a);
            }
        }
        return list;
    }
}
