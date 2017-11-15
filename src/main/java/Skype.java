import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Skype {
    private String username;
    private String password;

    private WebDriver driver;
    private final String path = "C:\\Users\\minht\\Desktop\\chromedriver.exe";

    public Skype(String username, String password) throws InterruptedException {
        this.username = username;
        this.password = password;
        System.setProperty("webdriver.chrome.driver","C:\\Users\\minht\\Desktop\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.get("https://web.skype.com/en/");
        WebElement username1 = this.driver.findElement(By.id("username"));
        username1.sendKeys(this.username);
        this.driver.findElement(By.id("signIn")).click();
        WebElement passwd = this.driver.findElement(By.name("passwd"));
        passwd.sendKeys(this.password);
        this.driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(15000);
        }

    public void login(long ping) throws InterruptedException {
        WebElement username = this.driver.findElement(By.id("username"));
        username.sendKeys(this.username);
        this.driver.findElement(By.id("signIn")).click();
        WebElement password = this.driver.findElement(By.name("passwd"));
        password.sendKeys(this.password);
        this.driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(ping);
    }

    public String[] getListNameChanel(){
        List<WebElement> navMenu = driver.findElements(By.tagName("swx-recent-item"));
        String[] rs = new String[navMenu.size()];
        int i = 0;
        for (WebElement webElement : navMenu){
            rs[i] = webElement.findElement(By.className("topic")).getText();
            i++;
        }
        return rs;
    }

    public List<WebElement> getChanels(){
        return driver.findElements(By.tagName("swx-recent-item"));
    }

    public List<WebElement> getChatbox(){
        return driver.findElements(By.className("content"));
    }

    public List<String> getChatboxText(){
        Iterator<WebElement> iterator = this.getChatbox().iterator();
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
        return list;
    }

    public List<String> getSerials(){
        List<String> serial = new ArrayList<String>();
        for(String s : this.getChatboxText()){
            String[] ar = s.split("\n");
            for (String ele : ar){
                try {
                    if(Long.parseLong(ele)>0){
                        serial.add(ele);}
                }catch (Exception e){
                }
            }
        }
        return serial;
    }
    public  String[] markFilter(List<String> sample){
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

    public void pickChanel(String chanel) throws InterruptedException {
        for (WebElement m : getChanels()){
//            System.out.println(m.findElement(By.className("topic")).getText());
            if (m.findElement(By.className("topic")).getText().equals(chanel)){
                WebElement element = m.findElement(By.className("topic"));
                element.click();
                System.out.println(m.findElement(By.className("topic")).getText().equals(chanel));
                break;
            }
        }
        Thread.sleep(2000);
    }
    public  List<String> Filterduplicate(String[] ar){
        List<String> list = new ArrayList<String>();
        for (String a : ar){
            if (!a.equals("-")){
                list.add(a);
            }
        }
        return list;
    }

    public List<String> rawUnQuerySerrials(){
        return Filterduplicate(markFilter(this.getSerials()));
    }

}
