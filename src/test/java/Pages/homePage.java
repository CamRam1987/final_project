package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class homePage extends Requests.Base {
    By loginLocator=By.xpath("//a[@href='/login']");

    public homePage(WebDriver driver) {
        super(driver);
    }
    public void clickLogin(){
        click(loginLocator);
    }

}
