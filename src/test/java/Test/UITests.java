package Test;

import Requests.Base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.SetProperties;

import java.util.concurrent.TimeUnit;

public class UITests extends SetProperties {
    public static WebDriver driver;
    Base base=new Base(driver);
    @BeforeTest
    public void BeforeTestMethod(){
    }

    @BeforeMethod
    @Parameters(value ={"browsername"})
    public void BeforeMethodMethod(String browsername){
        base.setupdriver(browsername);
        driver.manage().window().maximize();
        driver.get(getUrl_host());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterTest
    public void AfterMethodMethod(){

    }
    @AfterMethod
    public void AfterTestMethod(){
        driver.quit();
    }
}
