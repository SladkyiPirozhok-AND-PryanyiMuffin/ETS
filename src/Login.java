import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 14.05.2018.
 */

public class Login {
    private ChromeDriver driver;

    public void User(ChromeDriver driver, String login, String password) throws Exception {


        Common waitALittleBit = new Common(driver);
        Common textShouldDisappear = new Common(driver);
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        driver.get("http://dev2-ets.gost-group.com/#/login");
//        // Идет загрузка приложения...
//        if (driver.findElement(By.partialLinkText("Идет загрузка приложения...")).isDisplayed()) {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Система мониторинга")));
//        }

        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("login")).sendKeys(login);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        Common PresenceOfID = new Common(driver);
        PresenceOfID.waitingForID("submit").click();
    }
}
