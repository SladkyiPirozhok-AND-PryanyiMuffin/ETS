import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by User on 14.05.2018.
 */

public class Login {
    private ChromeDriver driver;

    public void User(ChromeDriver driver, String login, String password) throws Exception {
        driver.get("https://ets.mos.ru/ets-stage2/#/login");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("login")).sendKeys(login);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        Common PresenceOfID = new Common(driver);
        PresenceOfID.waitingForID("submit").click();
    }
}