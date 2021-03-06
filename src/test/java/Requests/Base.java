package Requests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    private WebDriver driver;

    public Base(WebDriver driver) {

    }

    public void base (WebDriver driver){
        this.driver=driver;
    }
    public void setupdriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver_win32 (4)/chromedriver.exe");
            driver=new ChromeDriver();
        }else if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/java/resources/geckodriver-v0.30.0-win64/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else{
            System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver_win32 (4)/chromedriver.exe");
            driver=new ChromeDriver();
        }
    }

    public WebDriver ChromeDriverConnection(){
        System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver_win32 (4)/chromedriver.exe");
        driver =new ChromeDriver();
        return driver;
    }
    public WebElement findElement(By Locator){
        return driver.findElement(Locator);
    }
    public String getText(WebElement element){
        return element.getText();
    }
    public String getText(By Locator){
        return driver.findElement(Locator).getText();
    }
    public void type(String InputText, By Locator){
        driver.findElement(Locator).sendKeys(InputText);
    }
    public void click(By Locator){
        driver.findElement(Locator).click();
    }
    public void getPage(String url){
        driver.get(url);
    }



}
